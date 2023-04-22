package sk.extremeprog.database;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Date;
import java.time.LocalDate;

public class DatabaseTest {
    private Book book1 = new Book("t1", "a1", Date.valueOf("2000-1-1"), Date.valueOf("2007-8-11"));
    private Book book2 = new Book("t2", "a2", Date.valueOf("2000-1-1"), Date.valueOf("2010-10-10"));
    private Book book3 = new Book("t3", "a3", Date.valueOf("2000-1-1"), Date.valueOf(LocalDate.EPOCH));
    private Database database = Database.getInstance();

    @Before
    public void emptyDatabase(){
        database.reset();
    }

    @Test
    public void consistentSaveLoad() {
        var path = "data/testDatabase.test";

        database.insert(book1);

        database.save(path);
        Assert.assertEquals(book1, database.findById(book1.hashCode()));

        database.reset();
        Assert.assertEquals(Book.nullObject, database.findById(book1.hashCode()));

        database.load(path);
        Assert.assertEquals(book1, database.findById(book1.hashCode()));

        try {
            Files.delete(Path.of(path));
        } catch (IOException e) {
            System.err.println("Failed to clean up test database file");
        }
    }

    @Test
    public void insert() {
        Assert.assertEquals(Book.nullObject, database.findById(book1.hashCode()));
        Assert.assertEquals(Book.nullObject, database.findById(book2.hashCode()));

        database.insert(book1);
        Assert.assertEquals(book1, database.findById(book1.hashCode()));

        database.insert(book2);
        Assert.assertEquals(book2, database.findById(book2.hashCode()));
    }

}