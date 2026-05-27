package dao.impl;

import dao.BorrowDAO;
import database.DBConnection;
import model.BorrowRecord;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class BorrowDAOImpl implements BorrowDAO {

    Connection con =
            DBConnection.getInstance().getConnection();

    @Override
    public boolean borrowBook(BorrowRecord record) {

        String sql =
                "INSERT INTO borrow_records(user_id,book_id,borrow_date,status) VALUES(?,?,?,?)";

        try(PreparedStatement ps =
                    con.prepareStatement(sql)) {

            ps.setInt(1, record.getUserId());
            ps.setInt(2, record.getBookId());

            ps.setDate(
                    3,
                    java.sql.Date.valueOf(
                            record.getBorrowDate()
                    )
            );

            ps.setString(4, record.getStatus());

            return ps.executeUpdate() > 0;

        } catch(Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean returnBook(int recordId,
                              double fine) {

        String sql =
                "UPDATE borrow_records SET return_date=?, fine=?, status=? WHERE record_id=?";

        try(PreparedStatement ps =
                    con.prepareStatement(sql)) {

            ps.setDate(
                    1,
                    java.sql.Date.valueOf(
                            java.time.LocalDate.now()
                    )
            );

            ps.setDouble(2, fine);

            ps.setString(3, "RETURNED");

            ps.setInt(4, recordId);

            return ps.executeUpdate() > 0;

        } catch(Exception e) {
            e.printStackTrace();
        }

        return false;
    }
}