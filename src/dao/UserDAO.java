package dao;

import model.User;

public interface UserDAO {

    boolean registerUser(User user);

    User login(String email, String password);
}