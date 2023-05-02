package sk.extremeprog.database;

import org.junit.Assert;
import org.junit.Test;

import java.sql.Date;


public class BookTest {
    @Test
    public void construction() {
        var title = "title";
        var author = "author";
        var publicationDate = Date.valueOf("2000-2-2");
        var borrowedDate = Date.valueOf("2005-5-18");
        var borrower = "Jozef Mrkva";
        var book = new Book(title, author, publicationDate, borrowedDate, borrower);

        Assert.assertEquals(book.getTitle(), title);
        Assert.assertEquals(book.getAuthor(), author);
        Assert.assertEquals(book.getPublicationDate(), publicationDate);
        Assert.assertEquals(book.getBorrowedDate(), borrowedDate);
        Assert.assertEquals(book.getBorrower(), borrower);
    }

    @Test
    public void invalidConstruction() {
        var title = "title";
        var author = "author";
        var publicationDate = Date.valueOf("2000-2-2");
        var borrowedDate = Date.valueOf("2005-5-18");
        var borrower = "Jozef Mrkva";
        var book = new Book(title, author, publicationDate, borrowedDate, borrower);
        Assert.assertThrows(IllegalArgumentException.class,()->book.setId(-1));
        Assert.assertThrows(IllegalArgumentException.class,()->book.setId(0));
        Assert.assertThrows(IllegalArgumentException.class,()->book.setId(null));
        Assert.assertThrows(IllegalArgumentException.class,()->book.setAuthor(null));
        Assert.assertThrows(IllegalArgumentException.class,()->book.setAuthor(""));

    }
}