package sk.extremeprog.database;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Stream;

public class Database {
    private final HashMap<Integer, Book> books;

    private static final Database INSTANCE = new Database();


    private Database() {
        this.books = new HashMap<>();
    }

    public static Database getInstance() {
        return INSTANCE;
    }

    public void insert(Book book) {
        books.put(book.hashCode(), book);
    }

    public Book findById(int id) {
        return books.getOrDefault(id, Book.nullObject);
    }

    public List<Book> findByTitle(String title) {
        return books.values().stream()
                .filter((b -> b.title().equals(title)))
                .toList();
    }

    public List<Book> findByAuthor(String author) {
        return books.values().stream()
                .filter((b -> b.author().equals(author)))
                .toList();
    }

    public void save(String path) {
        try (FileOutputStream fw = new FileOutputStream(path);
             PrintStream ps = new PrintStream(fw)
        ) {
            books.values()
                    .forEach(book ->
                            ps.print(book.title()
                                    + ";" + book.author()
                                    + ";" + book.publicationDate()
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
                var title = attrs[0];
                var author = attrs[1];
                var published = Date.valueOf(attrs[2]);

                this.insert(new Book(title, author, published));
            });
        } catch (IOException e) {
            System.err.println("Failed to load from file " + path);
        }
    }

    public void reset() {
        books.clear();
    }
}
