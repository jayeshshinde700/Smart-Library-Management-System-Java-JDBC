package model;

import java.time.LocalDate;

public class BorrowRecord {

    private int recordId;
    private int userId;
    private int bookId;

    private LocalDate borrowDate;
    private LocalDate returnDate;

    private double fine;

    private String status;

    public BorrowRecord() {}

    public BorrowRecord(int userId,
                        int bookId,
                        LocalDate borrowDate,
                        String status) {

        this.userId = userId;
        this.bookId = bookId;
        this.borrowDate = borrowDate;
        this.status = status;
    }

    public int getRecordId() {
        return recordId;
    }

    public void setRecordId(int recordId) {
        this.recordId = recordId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public LocalDate getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(LocalDate borrowDate) {
        this.borrowDate = borrowDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    public double getFine() {
        return fine;
    }

    public void setFine(double fine) {
        this.fine = fine;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}