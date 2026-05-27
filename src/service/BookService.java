package service;

import dao.BookDAO;
import dao.impl.BookDAOImpl;
import model.Book;

import java.util.List;
import java.util.stream.Collectors;

public class BookService {

    private BookDAO bookDAO =
            new BookDAOImpl();

    public boolean addBook(Book book) {
        return bookDAO.addBook(book);
    }

    public List<Book> getAllBooks() {
        return bookDAO.getAllBooks();
    }

    public Book searchBook(int id) {
        return bookDAO.searchBook(id);
    }

    public boolean updateBook(Book book) {
        return bookDAO.updateBook(book);
    }

    public boolean deleteBook(int id) {
        return bookDAO.deleteBook(id);
    }

    // Streams + Lambda
    public List<Book> filterBooksByCategory(
            String category) {

        return getAllBooks()
                .stream()
                .filter(book ->
                        book.getCategory()
                                .equalsIgnoreCase(category))
                .collect(Collectors.toList());
    }
}