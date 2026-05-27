package dao;

import model.BorrowRecord;

public interface BorrowDAO {

    boolean borrowBook(BorrowRecord record);

    boolean returnBook(int recordId,
                       double fine);
}