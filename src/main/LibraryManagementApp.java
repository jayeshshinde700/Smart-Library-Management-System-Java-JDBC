package main;

import java.util.List;
import java.util.Scanner;

import factory.UserFactory;
import model.User;
import service.AuthenticationService;
import model.Book;
import service.BookService;
import service.BorrowService;
import exception.BookNotAvailableException;

import java.time.LocalDate;

public class LibraryManagementApp {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        AuthenticationService authService =
                new AuthenticationService();
        BookService bookService =
                new BookService();
        BorrowService borrowService =
                new BorrowService();

        while(true) {

        	System.out.println("\n===== SMART LIBRARY =====");

        	System.out.println("1. Register");
        	System.out.println("2. Login");
        	System.out.println("3. Add Book");
        	System.out.println("4. View Books");
        	System.out.println("5. Search Book");
        	System.out.println("6. Update Book");
        	System.out.println("7. Delete Book");
        	System.out.println("8. Borrow Book");
        	System.out.println("9. Return Book");
        	System.out.println("10. Exit");

            int choice = sc.nextInt();
            sc.nextLine();

            switch(choice) {

                case 1:

                    System.out.print("Enter Name: ");
                    String name = sc.nextLine();

                    System.out.print("Enter Email: ");
                    String email = sc.nextLine();

                    System.out.print("Enter Password: ");
                    String password = sc.nextLine();

                    User user =
                            UserFactory.createUser(
                                    name,
                                    email,
                                    password,
                                    "USER"
                            );

                    boolean result =
                            authService.register(user);

                    if(result) {
                        System.out.println("Registration Successful");
                    } else {
                        System.out.println("Registration Failed");
                    }

                    break;

                case 2:

                    System.out.print("Enter Email: ");
                    String loginEmail = sc.nextLine();

                    System.out.print("Enter Password: ");
                    String loginPassword = sc.nextLine();

                    User loggedIn =
                            authService.login(
                                    loginEmail,
                                    loginPassword
                            );

                    if(loggedIn != null) {
                        System.out.println(
                                "Welcome "
                                        + loggedIn.getName()
                        );
                    } else {
                        System.out.println("Invalid Credentials");
                    }

                    break;

                case 3:

                    System.out.print("Enter Title: ");
                    String title = sc.nextLine();

                    System.out.print("Enter Author: ");
                    String author = sc.nextLine();

                    System.out.print("Enter Category: ");
                    String category = sc.nextLine();

                    System.out.print("Enter Quantity: ");
                    int quantity = sc.nextInt();
                    sc.nextLine();

                    Book book = new Book(
                            title,
                            author,
                            category,
                            quantity
                    );

                    boolean added =
                            bookService.addBook(book);

                    if(added) {
                        System.out.println("Book Added Successfully");
                    } else {
                        System.out.println("Failed to Add Book");
                    }

                    break;
                    
                case 4:

                    List<Book> books =
                            bookService.getAllBooks();

                    books.forEach(System.out::println);

                    break;
                    
                case 5:

                    System.out.print("Enter Book ID: ");
                    int searchId = sc.nextInt();
                    sc.nextLine();

                    Book foundBook =
                            bookService.searchBook(searchId);

                    if(foundBook != null) {
                        System.out.println(foundBook);
                    } else {
                        System.out.println("Book Not Found");
                    }

                    break;  
                    
                case 6:

                    System.out.print("Enter Book ID: ");
                    int updateId = sc.nextInt();
                    sc.nextLine();

                    Book existingBook =
                            bookService.searchBook(updateId);

                    if(existingBook != null) {

                        System.out.print("New Title: ");
                        existingBook.setTitle(sc.nextLine());

                        System.out.print("New Author: ");
                        existingBook.setAuthor(sc.nextLine());

                        System.out.print("New Category: ");
                        existingBook.setCategory(sc.nextLine());

                        System.out.print("New Quantity: ");
                        existingBook.setQuantity(sc.nextInt());

                        existingBook.setAvailable(
                                existingBook.getQuantity());

                        sc.nextLine();

                        boolean updated =
                                bookService.updateBook(existingBook);

                        if(updated) {
                            System.out.println("Book Updated");
                        } else {
                            System.out.println("Update Failed");
                        }

                    } else {
                        System.out.println("Book Not Found");
                    }

                    break;
                    
                case 7:

                    System.out.print("Enter Book ID: ");
                    int deleteId = sc.nextInt();
                    sc.nextLine();

                    boolean deleted =
                            bookService.deleteBook(deleteId);

                    if(deleted) {
                        System.out.println("Book Deleted");
                    } else {
                        System.out.println("Delete Failed");
                    }

                    break;
                    
                case 8:

                    try {

                        System.out.print("Enter User ID: ");
                        int userId = sc.nextInt();

                        System.out.print("Enter Book ID: ");
                        int bookId = sc.nextInt();

                        sc.nextLine();

                        boolean borrowed =
                                borrowService.borrowBook(
                                        userId,
                                        bookId
                                );

                        if(borrowed) {
                            System.out.println(
                                    "Borrow Successful"
                            );
                        }

                    } catch(BookNotAvailableException e) {

                        System.out.println(e.getMessage());
                    }

                    break;
                    
                case 9:

                    System.out.print("Enter Record ID: ");
                    int recordId = sc.nextInt();

                    System.out.print("Enter Book ID: ");
                    int returnBookId = sc.nextInt();

                    sc.nextLine();

                    System.out.print(
                            "Enter Borrow Date (yyyy-mm-dd): "
                    );

                    LocalDate borrowDate =
                            LocalDate.parse(sc.nextLine());

                    boolean returned =
                            borrowService.returnBook(
                                    recordId,
                                    returnBookId,
                                    borrowDate
                            );

                    if(returned) {

                        System.out.println(
                                "Book Returned"
                        );
                    } else {

                        System.out.println(
                                "Return Failed"
                        );
                    }

                    break;
                    
                case 10:
                    System.exit(0);

                default:
                    System.out.println("Invalid Choice");
            }
        }
    }
}