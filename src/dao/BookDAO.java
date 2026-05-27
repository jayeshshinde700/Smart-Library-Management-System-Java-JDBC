package dao;

import java.util.List;

import model.Book;

public interface BookDAO {

    boolean addBook(Book book);

    List<Book> getAllBooks();

    Book searchBook(int id);

    boolean updateBook(Book book);

    boolean deleteBook(int id);
}