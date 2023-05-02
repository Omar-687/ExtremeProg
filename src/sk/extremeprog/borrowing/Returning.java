package sk.extremeprog.borrowing;

import sk.extremeprog.database.Book;
import sk.extremeprog.database.Database;

public class Returning {
    private static final Database database = Database.getInstance();

    public static Book returnBook(Book book) {
        book.setBorrowedDate(null);
        book.setBorrower(null);
        return book;
    }

    public static Book returnBook(int bookID) {
        Book book = database.findById(bookID);
        book.setBorrower(null);
        book.setBorrowedDate(null);
        database.modify(book);
        return book;
    }
}
