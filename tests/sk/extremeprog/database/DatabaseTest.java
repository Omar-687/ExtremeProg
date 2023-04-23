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
    private Book book1 = new Book("t1", "a1", Date.valueOf("2000-1-1"));
    private Book book2 = new Book("t2", "a2", Date.valueOf("2000-1-1"), Date.valueOf("2010-10-10"));
    private Book book3 = new Book("t3", "a3", Date.valueOf("2000-1-1"), Date.valueOf(LocalDate.EPOCH));
    private Book book4 = new Book("t4", "a4", Date.valueOf("2011-8-1"), Date.valueOf("2010-10-10"),"Jan Novak");
    private Database database = Database.getInstance();

    @Before
    public void emptyDatabase() {
        database.reset();
    }

    @Test
    public void consistentSaveLoad() {
        var path = "data/testDatabase.test";

        int id1 = database.insert(book1);
        book1.setId(id1);

        int id2 = database.insert(book4);
        book4.setId(id2);

        database.save(path);
        Assert.assertEquals(book1, database.findById(book1.getId()));
        Assert.assertEquals(book4, database.findById(book4.getId()));

        database.reset();
        Assert.assertEquals(Book.nullObject, database.findById(book1.getId()));
        Assert.assertEquals(Book.nullObject, database.findById(book4.getId()));

        database.load(path);
        Assert.assertEquals(book1, database.findById(book1.getId()));
        Assert.assertEquals(book4, database.findById(book4.getId()));

        try {
            Files.delete(Path.of(path));
        } catch (IOException e) {
            System.err.println("Failed to clean up test database file");
        }
    }

    @Test
    public void insert() {
        Assert.assertEquals(Book.nullObject, database.findById(1));
        Assert.assertEquals(Book.nullObject, database.findById(2));

        int id1 = database.insert(book1);
        book1.setId(id1);
        Assert.assertEquals(book1, database.findById(book1.getId()));

        int id2 = database.insert(book2);
        book2.setId(id2);
        Assert.assertEquals(book2, database.findById(book2.getId()));

        int id3 = database.insert(book3);
        book3.setId(id3);
        Assert.assertEquals(book3, database.findById(book3.getId()));

        int id4 = database.insert(book4);
        book4.setId(id4);
        Assert.assertEquals(book4, database.findById(book4.getId()));
    }

}