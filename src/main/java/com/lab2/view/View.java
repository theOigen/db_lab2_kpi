package com.lab2.view;

import com.lab2.model.*;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.List;

public class View {
    public View() {}

    public void printMenu() {
        System.out.println("Welcome to lab2! Choose on of options below:");
        System.out.println("0. Randomize");
        System.out.println("1. Get list of entities from table");
        System.out.println("2. Get entity by id from table");
        System.out.println("3. Insert into");
        System.out.println("4. Update in");
        System.out.println("5. Delete from");
        System.out.println("6. FTS");
        System.out.println("7. Search by some input");
        System.out.println("8. Exit");
    }

    public void askId() {
        System.out.println("Enter id: ");
    }

    public void printTables() {
        System.out.println("1. Author");
        System.out.println("2. Book");
        System.out.println("3. Reader");
        System.out.println("4. Subscription");
    }

    public void showDeleted() {
        System.out.println("Deleted entity");
    }

    public void clearConsole() {
        try {
            Runtime.getRuntime().exec("clear");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public int getOption() {
        Scanner in = new Scanner(System.in);
        return in.nextInt();
    }

    public String getStringOption(String name) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter " + name + ": ");
        return sc.nextLine();
    }

    public Long getLongOption(String name) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter " + name + ": ");
        return sc.nextLong();
    }

    public int getIntOption(String name) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter " + name + ": ");
        return sc.nextInt();
    }

    public boolean getBooleanOption(String name) {
        Scanner n = new Scanner(System.in);
        System.out.println("Enter " + name + ": ");
        return n.nextBoolean();
    }

    private void printResultSet(ResultSet resultSet) throws SQLException {
        ResultSetMetaData rsmd = resultSet.getMetaData();
        int columnsNumber = rsmd.getColumnCount();
        while (resultSet.next()) {
            for (int i = 1; i <= columnsNumber; i++) {
                String columnValue = resultSet.getString(i);
                System.out.println(rsmd.getColumnName(i) + ": " + columnValue);
            }
            System.out.println("----------");
        }
    }

    public void joinedSearchResult(ResultSet resultSet) throws SQLException {
        System.out.println("Joinded Search result: ");
        printResultSet(resultSet);
    }

    private void printHR() {
        System.out.println("--------------");
    }

    public void printAuthorInfo(Author a) {
        printHR();
        System.out.println("ID: " + a.getAid());
        System.out.println("Name: " + a.getName());
        System.out.println("Nationality: " + a.getNationality());
        System.out.println("Birth date: " + a.getBirth_date().toString());
    }

    public void printBookInfo(Book b) {
        printHR();
        System.out.println("ID: " + b.getBid());
        System.out.println("Title: " + b.getTitle());
        System.out.println("Author: " + b.getAuthor());
        System.out.println("Genre: " + b.getGenre());
        System.out.println("Original language: " + b.getOriginal_language());
        System.out.println("Pages count: " + b.getPages_count());
    }

    public void printReaderInfo(Reader r) {
        printHR();
        System.out.println("ID: " + r.getRid());
        System.out.println("Name: " + r.getName());
        System.out.println("Age: " + r.getAge());
        System.out.println("Favourite genre: " + r.getFavourite_genre());
        System.out.println("Read books count: " + r.getFinished_books());
    }

    public void printSubscriptionInfo(Subscription s) {
        printHR();
        System.out.println("ID: " + s.getSid());
        System.out.println("Type: " + s.getType());
        System.out.println("Validity: " + s.getValidity());
        System.out.println("Owner: " + s.getOwner());
        System.out.println("Price: " + s.getPrice());
    }

    public void printAuthors(List<Author> authors) {
        for (Author author : authors) {
            printAuthorInfo(author);
        }
    }

    public void printBooks(List<Book> books) {
        for (Book book : books) {
            printBookInfo(book);
        }
    }

    public void printReaders(List<Reader> readers) {
        for (Reader reader: readers) {
            printReaderInfo(reader);
        }
    }

    public void printSubscriptions(List<Subscription> subscriptions) {
        for (Subscription subscription : subscriptions) {
            printSubscriptionInfo(subscription);
        }
    }

}
