package sk.extremeprog.database;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class BorrowingDateController {

    private final long DAYS_TO_RETURN_BOOK = 30L;

    private final long CONVERSION_RATE_FROM_MILLISECONDS_TO_DAY = 86400000L;

    public List<Book> getBooksToBeReturned(List<Book> books) {
        List<Book> booksToBeReturned = new ArrayList<>();
        for (Book book : books) {
            if (book.getBorrowedDate().getTime() + (DAYS_TO_RETURN_BOOK * CONVERSION_RATE_FROM_MILLISECONDS_TO_DAY) <= System.currentTimeMillis()) {
                booksToBeReturned.add(book);
            }
        }
        return booksToBeReturned;
    }


}
