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
        var book = new Book(title, author, publicationDate, borrowedDate);

        Assert.assertEquals(book.getTitle(), title);
        Assert.assertEquals(book.getAuthor(), author);
        Assert.assertEquals(book.getPublicationDate(), publicationDate);
        Assert.assertEquals(book.getBorrowedDate(), borrowedDate);
    }
}