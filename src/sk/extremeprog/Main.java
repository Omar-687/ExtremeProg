package sk.extremeprog;

import sk.extremeprog.database.Book;
import sk.extremeprog.database.Database;

import java.sql.Date;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {

        var database = Database.getInstance();

        database.insertBook(new Book("t1", "a1", Date.valueOf(LocalDate.MIN), Date.valueOf(LocalDate.EPOCH)));
        database.insertBook(new Book("t2", "a1", Date.valueOf(LocalDate.MIN), Date.valueOf(LocalDate.EPOCH)));
        database.insertBook(new Book("t3", "a1", Date.valueOf(LocalDate.MIN), Date.valueOf(LocalDate.EPOCH)));

        database.removeBook(2);
        database.save("data/d1.csv");

        System.out.println(database.findByAuthor("a1"));

    }
}