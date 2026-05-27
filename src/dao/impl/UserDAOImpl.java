package dao.impl;

import dao.UserDAO;
import database.DBConnection;
import model.User;

import java.sql.*;

public class UserDAOImpl implements UserDAO {

    Connection con =
            DBConnection.getInstance().getConnection();

    @Override
    public boolean registerUser(User user) {

        String sql =
                "INSERT INTO users(name,email,password,role) VALUES(?,?,?,?)";

        try(PreparedStatement ps =
                    con.prepareStatement(sql)) {

            ps.setString(1, user.getName());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getPassword());
            ps.setString(4, user.getRole());

            return ps.executeUpdate() > 0;

        } catch(Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public User login(String email, String password) {

        String sql =
                "SELECT * FROM users WHERE email=? AND password=?";

        try(PreparedStatement ps =
                    con.prepareStatement(sql)) {

            ps.setString(1, email);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            if(rs.next()) {

                User user = new User();

                user.setUserId(rs.getInt("user_id"));
                user.setName(rs.getString("name"));
                user.setEmail(rs.getString("email"));
                user.setRole(rs.getString("role"));

                return user;
            }

        } catch(Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}