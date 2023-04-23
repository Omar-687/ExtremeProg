package sk.extremeprog.database;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Objects;

public class Book{
    private Integer id;
    private String title;
    private String author;
    private Date publicationDate;
    private Date borrowedDate;

    public static Book nullObject = new Book(
            "untitled",
            "anonymous",
            Date.valueOf(LocalDate.EPOCH),
            Date.valueOf(LocalDate.EPOCH));

    public Book(String title, String author, Date publicationDate, Date borrowedDate) {
        this.id = null;
        this.title = title;
        this.author = author;
        this.publicationDate = publicationDate;
        this.borrowedDate = borrowedDate;
    }

    public Book(Integer id, String title, String author, Date publicationDate, Date borrowedDate) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.publicationDate = publicationDate;
        this.borrowedDate = borrowedDate;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Objects.equals(id, book.id) && Objects.equals(title, book.title) && Objects.equals(author, book.author) && Objects.equals(publicationDate, book.publicationDate) && Objects.equals(borrowedDate, book.borrowedDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, author, publicationDate, borrowedDate);
    }

    @Override
    public String toString() {
        return title + " by " + author + " at " + publicationDate + " borrowed " + borrowedDate;
    }
}
