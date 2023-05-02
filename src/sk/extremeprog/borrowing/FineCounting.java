package sk.extremeprog.borrowing;

import sk.extremeprog.auxiliary.Pair;
import sk.extremeprog.database.BorrowingDateController;
import sk.extremeprog.database.Book;
import sk.extremeprog.database.Database;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class FineCounting {
    private static final double perDay = 0.5;
    private static final long freePeriod = 2592000000L;     // 30 days

    private final static Database database = Database.getInstance();

    public static List<Pair<Book, BigDecimal>> calculateFinesOfAll() {
        BorrowingDateController controller = new BorrowingDateController();
        List<Book> booksToReturn = controller.getBooksToBeReturned(database.getAllBooks());
        List<Pair<Book, BigDecimal>> fines = new ArrayList<>();
        for (Book book : booksToReturn) {
            fines.add(new Pair<>(book, calculateFineOfOne(book)));
        }
        return fines;
    }

    public static BigDecimal calculateFineOfOne(int bookID) {
        return calculateFineOfOne(database.findById(bookID));
    }

    public static BigDecimal calculateFineOfOne(Book book) {
        Date borrowDate = book.getBorrowedDate();
        Date returnDate = new Date(borrowDate.getTime() + freePeriod);
        long now = System.currentTimeMillis();

        long timeDiff = now - returnDate.getTime();
        if (timeDiff > 0 ){
            long daysDiff = TimeUnit.DAYS.convert(timeDiff, TimeUnit.MILLISECONDS);
            return BigDecimal.valueOf(perDay * daysDiff);
        } else {
            return BigDecimal.valueOf(0.0);
        }
    }
}
