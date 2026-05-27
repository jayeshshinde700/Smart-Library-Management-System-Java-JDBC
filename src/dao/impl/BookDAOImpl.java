package dao.impl;

import dao.BookDAO;
import database.DBConnection;
import model.Book;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookDAOImpl implements BookDAO {

    Connection con =
            DBConnection.getInstance().getConnection();

    @Override
    public boolean addBook(Book book) {

        String sql =
                "INSERT INTO books(title,author,category,quantity,available) VALUES(?,?,?,?,?)";

        try(PreparedStatement ps =
                    con.prepareStatement(sql)) {

            ps.setString(1, book.getTitle());
            ps.setString(2, book.getAuthor());
            ps.setString(3, book.getCategory());
            ps.setInt(4, book.getQuantity());
            ps.setInt(5, book.getAvailable());

            return ps.executeUpdate() > 0;

        } catch(Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public List<Book> getAllBooks() {

        List<Book> books = new ArrayList<>();

        String sql = "SELECT * FROM books";

        try(PreparedStatement ps =
                    con.prepareStatement(sql)) {

            ResultSet rs = ps.executeQuery();

            while(rs.next()) {

                Book book = new Book();

                book.setBookId(rs.getInt("book_id"));
                book.setTitle(rs.getString("title"));
                book.setAuthor(rs.getString("author"));
                book.setCategory(rs.getString("category"));
                book.setQuantity(rs.getInt("quantity"));
                book.setAvailable(rs.getInt("available"));

                books.add(book);
            }

        } catch(Exception e) {
            e.printStackTrace();
        }

        return books;
    }

    @Override
    public Book searchBook(int id) {

        String sql =
                "SELECT * FROM books WHERE book_id=?";

        try(PreparedStatement ps =
                    con.prepareStatement(sql)) {

            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if(rs.next()) {

                Book book = new Book();

                book.setBookId(rs.getInt("book_id"));
                book.setTitle(rs.getString("title"));
                book.setAuthor(rs.getString("author"));
                book.setCategory(rs.getString("category"));
                book.setQuantity(rs.getInt("quantity"));
                book.setAvailable(rs.getInt("available"));

                return book;
            }

        } catch(Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public boolean updateBook(Book book) {

        String sql =
                "UPDATE books SET title=?, author=?, category=?, quantity=?, available=? WHERE book_id=?";

        try(PreparedStatement ps =
                    con.prepareStatement(sql)) {

            ps.setString(1, book.getTitle());
            ps.setString(2, book.getAuthor());
            ps.setString(3, book.getCategory());
            ps.setInt(4, book.getQuantity());
            ps.setInt(5, book.getAvailable());
            ps.setInt(6, book.getBookId());

            return ps.executeUpdate() > 0;

        } catch(Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean deleteBook(int id) {

        String sql =
                "DELETE FROM books WHERE book_id=?";

        try(PreparedStatement ps =
                    con.prepareStatement(sql)) {

            ps.setInt(1, id);

            return ps.executeUpdate() > 0;

        } catch(Exception e) {
            e.printStackTrace();
        }

        return false;
    }
}