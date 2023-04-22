package sk.extremeprog.database;

import java.sql.Date;
import java.time.LocalDate;

public record Book(String title, String author, Date publicationDate, Date borrowedDate) {
    public static Book nullObject = new Book(
            "untitled",
            "anonymous",
            Date.valueOf(LocalDate.EPOCH),
            Date.valueOf(LocalDate.EPOCH));
    @Override
    public String toString() {
        return title + " by " + author + " at " + publicationDate + " borrowed " + borrowedDate;
    }
}
