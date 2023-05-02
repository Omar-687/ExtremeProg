package sk.extremeprog.database;

import org.junit.Assert;
import org.junit.Test;
import sk.extremeprog.borrowing.Borrowing;
import sk.extremeprog.borrowing.Returning;

import java.sql.Date;

public class ReturningBookTest {

    private final Database database = Database.getInstance();
    Book book1 = new Book("t1", "a1", Date.valueOf("2000-1-1"));
    Book book2 = new Book("t1", "a1", Date.valueOf("2000-1-1"), Date.valueOf("2015-4-10"), "Ignac Novak");
    Book book3 = new Book("t1", "a1", Date.valueOf("2000-1-1"), null, null);

    @Test
    public void returnBook() {
        Book modBook1 = Borrowing.borrowBook(book1, "Ignac Novak", Date.valueOf("2015-4-10"));
        Assert.assertEquals(book2, modBook1);

        Book retBook1 = Returning.returnBook(modBook1);
        Assert.assertEquals(retBook1, book3);

        int id = database.insertBook(book3);
        Borrowing.borrowBook(id, "Ignac Novak", Date.valueOf("2015-4-10"));
        Book modBook2 = database.findById(id);
        book2.setId(id);
        Assert.assertEquals(book2, modBook2);

        Returning.returnBook(id);
        Book modBook3 = database.findById(id);
        Assert.assertEquals(book3, modBook3);
    }
}
