package sk.extremeprog.database;

import org.junit.Assert;
import org.junit.Test;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

public class BorrowingDateControllerTest {

    @Test
    public void testController() {
        BorrowingDateController borrowingDateController = new BorrowingDateController();
        Book book1 = new Book("t1", "a1", Date.valueOf("2000-1-1"), Date.valueOf("2023-4-15"), "Jozef Mrkva");
        Book book2 = new Book("t2", "a2", Date.valueOf("2000-1-1"), Date.valueOf("2023-3-10"), "Ingnac Novak");
        Book book3 = new Book("t3", "a3", Date.valueOf("2000-1-1"), Date.valueOf(LocalDate.now()));
        Book book4 = new Book("t4", "a4", Date.valueOf("2000-1-1"), Date.valueOf("2023-2-15"));
        Book book5 = new Book("t5", "a5", Date.valueOf("2000-1-1"), Date.valueOf("2023-4-1"));
        Book book6 = new Book("t6", "a6", Date.valueOf("2000-1-1"), Date.valueOf(LocalDate.EPOCH));
        Book book7 = new Book("t7", "a7", Date.valueOf("2000-1-1"), null);

        List<Book> booksToBeReturned = borrowingDateController.getBooksToBeReturned(List.of(book1, book2, book3, book4, book5, book6, book7));

        Assert.assertEquals(3, booksToBeReturned.size());
        Assert.assertTrue(booksToBeReturned.contains(book2));
        Assert.assertTrue(booksToBeReturned.contains(book4));
        Assert.assertTrue(booksToBeReturned.contains(book6));
        Assert.assertFalse(booksToBeReturned.contains(book7));


    }
}
