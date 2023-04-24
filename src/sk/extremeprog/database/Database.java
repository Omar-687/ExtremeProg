package sk.extremeprog.database;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Stream;

import java.util.Objects;
public class Database {
    private final HashMap<Integer, Book> books;

    private int idCounter;

    private static final Database INSTANCE = new Database();


    private Database() {
        this.books = new HashMap<>();
        idCounter = 1;
    }

    public static Database getInstance() {
        return INSTANCE;
    }

    public int insertBook(Book book) {
        books.put(idCounter, book);
        book.setId(idCounter);
        idCounter++;
        return idCounter - 1;
    }
    public int removeBook(int bookNum){
        if (books.containsKey(bookNum)) {
            books.remove(bookNum);
            return 1; // book was deleted
        }
        return 0; // book was not deleted
        }


    public void modify(Book book) {
        books.put(book.getId(), book);
    }

    public Book findById(int id) {
        return books.getOrDefault(id, Book.nullObject);
    }

    public List<Book> findByTitle(String title) {
        return books.values().stream()
                .filter(book -> Objects.equals(book.getTitle(), title))
                .toList();
    }

    public List<Book> findByAuthor(String author) {
        return books.values().stream()
                .filter(book -> Objects.equals(book.getAuthor(), author))
                .toList();
    }
    public List<Book> findByPublicationDate(Date publicationDate) {
        return books.values().stream()
                .filter(book -> Objects.equals(book.getPublicationDate(), publicationDate))
                .toList();
    }

    public List<Book> findByBorrowedDate(Date borrowedDate) {
        return books.values().stream()
                .filter(book -> Objects.equals(book.getBorrowedDate(), borrowedDate))
                .toList();
    }
    public List<Book> findByBorrower(String borrower) {
            return books.values().stream()
                    .filter(book -> Objects.equals(book.getBorrower(), borrower))
                    .toList();

    }




    public void save(String path) {
        try (FileOutputStream fw = new FileOutputStream(path);
             PrintStream ps = new PrintStream(fw)
        ) {
            books.values()
                    .forEach(book ->
                            ps.print(book.getId()
                                    + ";" + book.getTitle()
                                    + ";" + book.getAuthor()
                                    + ";" + book.getPublicationDate()
                                    + ";" + book.getBorrowedDate()
                                    + ";" + book.getBorrower()
                                    + "\n"));
        } catch (IOException e) {
            System.err.println("Failed to save to file ");
        }
    }


    public void load(String path) {
        save("data/old.csv");

        try (Stream<String> fr = Files.lines(Path.of(path))) {
            fr.forEach(line -> {
                var attrs = line.split(";");
                var id = attrs[0];
                var title = attrs[1];
                var author = attrs[2];
                var published = Date.valueOf(attrs[3]);
                var borrowed = attrs[4].equals("null") ? null : Date.valueOf(attrs[4]);
                var borrower = attrs[5].equals("null") ? null : attrs[5];
                books.put(Integer.valueOf(id), new Book(Integer.valueOf(id), title, author, published, borrowed, borrower));
            });
        } catch (IOException e) {
            System.err.println("Failed to load from file " + path);
        }
    }

    public List<Book> getAllBooks(){
        return new ArrayList<>(books.values());

    }

    public void reset() {
        books.clear();
    }
}
