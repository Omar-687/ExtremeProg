package sk.extremeprog.borrowing;

import sk.extremeprog.database.Book;
import sk.extremeprog.database.Database;

import java.sql.Date;
import java.time.LocalDate;

public final class Borrowing {
    private static final Database d = Database.getInstance();

    public static Book borrowBook(Book book, String user){
        return borrowBook(book, user, Date.valueOf(LocalDate.now()));
    }

    public static Book borrowBook(Book book, String user, Date date){
        book.setBorrower(user);
        book.setBorrowedDate(date);
        return book;
    }

    public static Book borrowBook(int bookID, String user){
        return borrowBook(bookID, user, Date.valueOf(LocalDate.now()));
    }

    public static Book borrowBook(int bookID, String user, Date date){
        Book book = d.findById(bookID);
        book.setBorrower(user);
        book.setBorrowedDate(date);
        d.modify(book);
        return book;
    }
}
