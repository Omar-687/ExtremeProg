package sk.extremeprog.database;

import org.junit.Assert;
import org.junit.Test;

import java.sql.Date;

public class BookTest {
    @Test
    public void construction() {
        var t = "title";
        var a = "author";
        var d = Date.valueOf("2000-2-2");
        var b = new Book(t, a, d);

        Assert.assertEquals(b.title(), t);
        Assert.assertEquals(b.author(), a);
        Assert.assertEquals(b.publicationDate(), d);
    }
}