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

    private Book book5 = new Book("t2", "a1", Date.valueOf("2001-1-1"));

    private Database database = Database.getInstance();
//    private ArrayList<Book>

    int sucessfulRemovalOfBook = 1;
    int unSuccessfulRemovalOfBook = 0;

    @Before
    public void emptyDatabase() {
        database.reset();
    }

    @Test
    public void consistentSaveLoad() {
        var path = "data/testDatabase.test";

        database.insertBook(book1);
        database.insertBook(book4);

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
    public void insertBooks() {
        Assert.assertEquals(Book.nullObject, database.findById(1));
        Assert.assertEquals(Book.nullObject, database.findById(2));

        database.insertBook(book1);
        Assert.assertEquals(book1, database.findById(book1.getId()));

        database.insertBook(book2);
        Assert.assertEquals(book2, database.findById(book2.getId()));

        database.insertBook(book3);
        Assert.assertEquals(book3, database.findById(book3.getId()));

        database.insertBook(book4);
        Assert.assertEquals(book4, database.findById(book4.getId()));
    }
    @Test
    public void insertAndDeleteBooks() {
        Assert.assertEquals(Book.nullObject, database.findById(-1));
        int id1 = database.insertBook(book1);
        Assert.assertEquals(book1, database.findById(book1.getId()));
        Assert.assertEquals(database.removeBook(id1), sucessfulRemovalOfBook);

        Assert.assertEquals(Book.nullObject, database.findById(id1));
        Assert.assertEquals(database.removeBook(id1), unSuccessfulRemovalOfBook);
        Assert.assertEquals(database.removeBook(-1), unSuccessfulRemovalOfBook);

        id1 = database.insertBook(book1);
        int id2 = database.insertBook(book2);
        int id3 = database.insertBook(book3);
        int id4 = database.insertBook(book4);
        Assert.assertEquals(4, database.getAllBooks().size());
        Assert.assertEquals(3, database.findByPublicationDate(Date.valueOf("2000-1-1")).size());
        int id5 = database.insertBook(book5);
        Assert.assertEquals(2, database.findByAuthor("a1").size());
        Assert.assertEquals(2, database.findByTitle("t2").size());
        Assert.assertEquals(5, database.getAllBooks().size());
        Assert.assertEquals(sucessfulRemovalOfBook, database.removeBook(id4));
        Assert.assertEquals(sucessfulRemovalOfBook, database.removeBook(id5));
        Assert.assertEquals(unSuccessfulRemovalOfBook, database.removeBook(id5));
        Assert.assertEquals(3, database.getAllBooks().size());
        Assert.assertEquals(0, database.findByPublicationDate(Date.valueOf("1990-1-1")).size());
        Assert.assertEquals(0, database.findByBorrower("").size());
        Assert.assertEquals(0, database.findByPublicationDate(Date.valueOf("1990-1-1")).size());




    }


}