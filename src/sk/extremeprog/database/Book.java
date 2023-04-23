package sk.extremeprog.database;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Objects;

public class Book {
    private Integer id;
    private String title;
    private String author;
    private Date publicationDate;
    private Date borrowedDate;
    private String borrower;
    public static Book nullObject = new Book(
            "untitled",
            "anonymous",
            Date.valueOf(LocalDate.EPOCH));

    public Book(String title, String author, Date publicationDate) {
        this(null, title, author, publicationDate, Date.valueOf(LocalDate.EPOCH), null);
    }

    public Book(String title, String author, Date publicationDate, Date borrowedDate) {
        this(null, title, author, publicationDate, borrowedDate, null);
    }

    public Book(Integer bookID, String title, String author, Date published, Date borrowed) {
        this(bookID, title, author, published, borrowed, null);
    }

    public Book(String title, String author, Date publicationDate, Date borrowedDate, String borrower) {
        this(null, title, author, publicationDate, borrowedDate, borrower);
    }

    public Book(Integer id, String title, String author, Date publicationDate, Date borrowedDate, String borrower) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.publicationDate = publicationDate;
        this.borrowedDate = borrowedDate;
        this.borrower = borrower;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Date getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(Date publicationDate) {
        this.publicationDate = publicationDate;
    }

    public Date getBorrowedDate() {
        return borrowedDate;
    }

    public void setBorrowedDate(Date borrowedDate) {
        this.borrowedDate = borrowedDate;
    }

    public String getBorrower() {
        return borrower;
    }

    public void setBorrower(String user) {
        this.borrower = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Objects.equals(id, book.id) && Objects.equals(title, book.title)
                && Objects.equals(author, book.author) && Objects.equals(publicationDate, book.publicationDate)
                && Objects.equals(borrowedDate, book.borrowedDate) && Objects.equals(borrower, book.getBorrower());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, author, publicationDate, borrowedDate, borrower);
    }

    @Override
    public String toString() {
        return "ID: " + id + " book " + title + " by " + author + " at " + publicationDate + " borrowed " + borrowedDate + " by " + borrower;
    }
}
