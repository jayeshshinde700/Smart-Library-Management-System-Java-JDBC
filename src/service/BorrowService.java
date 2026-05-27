package service;

import dao.BookDAO;
import dao.BorrowDAO;

import dao.impl.BookDAOImpl;
import dao.impl.BorrowDAOImpl;

import exception.BookNotAvailableException;

import model.Book;
import model.BorrowRecord;

import util.NotificationThread;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class BorrowService {

    private BorrowDAO borrowDAO =
            new BorrowDAOImpl();

    private BookDAO bookDAO =
            new BookDAOImpl();

    // BORROW BOOK
    public boolean borrowBook(int userId,
                              int bookId)
            throws BookNotAvailableException {

        Book book =
                bookDAO.searchBook(bookId);

        if(book == null) {
            throw new BookNotAvailableException(
                    "Book does not exist"
            );
        }

        if(book.getAvailable() <= 0) {

            throw new BookNotAvailableException(
                    "Book not available"
            );
        }

        // Reduce availability
        book.setAvailable(
                book.getAvailable() - 1
        );

        bookDAO.updateBook(book);

        BorrowRecord record =
                new BorrowRecord(
                        userId,
                        bookId,
                        LocalDate.now(),
                        "BORROWED"
                );

        boolean result =
                borrowDAO.borrowBook(record);

        // Multithreading Notification
        if(result) {

            NotificationThread thread =
                    new NotificationThread(
                            "Book Borrowed Successfully"
                    );

            thread.start();
        }

        return result;
    }

    // RETURN BOOK
    public boolean returnBook(int recordId,
                              int bookId,
                              LocalDate borrowDate) {

        LocalDate today = LocalDate.now();

        long days =
                ChronoUnit.DAYS.between(
                        borrowDate,
                        today
                );

        double fine = 0;

        // Fine after 7 days
        if(days > 7) {

            fine = (days - 7) * 10;
        }

        Book book =
                bookDAO.searchBook(bookId);

        // Increase availability
        book.setAvailable(
                book.getAvailable() + 1
        );

        bookDAO.updateBook(book);

        boolean result =
                borrowDAO.returnBook(
                        recordId,
                        fine
                );

        if(result) {

            NotificationThread thread =
                    new NotificationThread(
                            "Book Returned Successfully"
                                    + " | Fine: ₹"
                                    + fine
                    );

            thread.start();
        }

        return result;
    }
}