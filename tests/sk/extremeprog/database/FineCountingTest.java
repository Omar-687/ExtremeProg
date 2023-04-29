package sk.extremeprog.database;

import org.junit.Assert;
import org.junit.Test;
import sk.extremeprog.auxiliary.Pair;
import sk.extremeprog.borrowing.FineCounting;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class FineCountingTest {

    private final Database database = Database.getInstance();

    Book book1 = new Book("t1", "a1", Date.valueOf("2000-1-1"), Date.valueOf(LocalDate.ofInstant(Instant.now().minus(31, ChronoUnit.DAYS), ZoneId.systemDefault())), "Ignac Novak");
    Book book2 = new Book("t2", "a2", Date.valueOf("2011-1-1"), Date.valueOf(LocalDate.ofInstant(Instant.now().minus(91, ChronoUnit.DAYS), ZoneId.systemDefault())), "Ivan Novak");
    Book book3 = new Book("t3", "a3", Date.valueOf("2015-1-1"), Date.valueOf(LocalDate.ofInstant(Instant.now().minus(188, ChronoUnit.DAYS), ZoneId.systemDefault())), "Jonatan Bukva");
    Book book4 = new Book("t4", "a4", Date.valueOf("2012-1-1"), Date.valueOf(LocalDate.ofInstant(Instant.now().minus(29, ChronoUnit.DAYS), ZoneId.systemDefault())), "Napoleon Bonaparte");
    Book book5 = new Book("t5", "a5", Date.valueOf("2014-4-4"), Date.valueOf(LocalDate.ofInstant(Instant.now().minus(200, ChronoUnit.DAYS), ZoneId.systemDefault())), "Alexander the Great");

    @Test
    public void countOneBook() {
        BigDecimal fine1 = FineCounting.calculateFineOfOne(book1);
        Assert.assertEquals(BigDecimal.valueOf(0.5), fine1);

        BigDecimal fine2 = FineCounting.calculateFineOfOne(book2);
        Assert.assertEquals(BigDecimal.valueOf(30.5), fine2);
    }

    @Test
    public void countAllBooks() {
        database.reset();
        database.insertBook(book3);
        int id = database.insertBook(book4);
        database.insertBook(book5);

        List<Pair<Book, BigDecimal>> fines = FineCounting.calculateFinesOfAll();
        for (Pair<Book, BigDecimal> pair : fines) {
            if (pair.getKey().equals(book3)) {
                Assert.assertEquals(BigDecimal.valueOf(79.0), pair.getValue());
            } else {
                Assert.assertEquals(BigDecimal.valueOf(85.0), pair.getValue());
                Assert.assertEquals(book5, pair.getKey());
            }
        }

        BigDecimal fine = FineCounting.calculateFineOfOne(id);
        Assert.assertEquals(BigDecimal.valueOf(0.0), fine);
    }
}
