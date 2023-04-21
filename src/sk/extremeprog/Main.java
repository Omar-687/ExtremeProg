package sk.extremeprog;

import sk.extremeprog.database.Book;
import sk.extremeprog.database.Database;

import java.sql.Date;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {

        var d = Database.getInstance();

        d.insert(new Book("t1", "a1", Date.valueOf(LocalDate.MIN)));
        d.insert(new Book("t2", "a1", Date.valueOf(LocalDate.MIN)));
        d.insert(new Book("t3", "a1", Date.valueOf(LocalDate.MIN)));

        d.save("data/d1.csv");

        System.out.println(d.findByAuthor("a1"));

    }
}