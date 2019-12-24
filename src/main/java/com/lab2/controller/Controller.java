package com.lab2.controller;

import com.lab2.dao.IDAO;
import com.lab2.model.*;
import com.lab2.view.View;

import java.security.SecureRandom;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class Controller {

    private View view;
    private IDAO dao;

    public Controller(View view, IDAO dao) {
        this.view = view;
        this.dao = dao;
    }

    public void mainMenu() throws SQLException, IllegalAccessException {
        boolean exit = false;
        while (!exit) {
            view.clearConsole();
            view.printMenu();
            int operation = view.getOption();
            int entity = 0;
            switch (operation) {
                case 0: {
                    view.clearConsole();
                    view.printTables();
                    entity = view.getOption();
                    view.clearConsole();
                    randomize(entity);
                    break;
                }
                case 1: {
                    view.clearConsole();
                    view.printTables();
                    entity = view.getOption();
                    view.clearConsole();
                    selectAll(entity);
                    break;
                }
                case 2: {
                    view.clearConsole();
                    view.printTables();
                    entity = view.getOption();
                    view.clearConsole();
                    view.askId();
                    long id = view.getOption();
                    selectById(id, entity);
                    break;
                }
                case 3: {
                    view.clearConsole();
                    view.printTables();
                    entity = view.getOption();
                    view.clearConsole();
                    insert(entity);
                    break;
                }
                case 4: {
                    view.clearConsole();
                    view.printTables();
                    entity = view.getOption();
                    view.clearConsole();
                    update(entity);
                    break;
                }
                case 5: {
                    view.clearConsole();
                    view.printTables();
                    entity = view.getOption();
                    view.clearConsole();
                    view.askId();
                    long id = view.getOption();
                    delete(id, entity);
                    break;
                }
                case 6: {
                    view.clearConsole();
                    List<Book> books = dao.searchWord(
                            view.getStringOption("word"),
                            view.getBooleanOption("including(true/false)"));
                    if (books != null)
                        view.printBooks(books);
                    break;
                }
                case 7: {
                    view.clearConsole();
                    ResultSet resultSet = dao.joinedBookSearch(
                            view.getIntOption("pages count start"),
                            view.getIntOption("pages count end"),
                            view.getBooleanOption("author alive(true/false)")
                    );
                    view.joinedSearchResult(resultSet);
                    break;
                }
                case 8: {
                    exit = true;
                    break;
                }
            }
        }
    }

    private void randomize(int entity) throws SQLException, IllegalAccessException {
        switch (entity) {
            case 1: {
                Author author = new Author(
                        null,
                        generateRandomString(15),
                        new Date(),
                        generateRandomString(10),
                        generateRandomBoolean()
                );
                Author insertedAuthor = dao.insertAuthor(author);
                view.clearConsole();
                view.printAuthorInfo(insertedAuthor);
                break;
            }
            case 2: {
                Book book = new Book(
                        null,
                        generateRandomString(15),
                        generateRandomInt(),
                        generateRandomString(10),
                        generateRandomString(5),
                        1L
                );
                Book insertedBook = dao.insertBook(book);
                view.clearConsole();
                view.printBookInfo(insertedBook);
                break;
            }
            case 3: {
                Reader reader = new Reader(
                        null,
                        generateRandomString(10),
                        generateRandomString(5),
                        generateRandomInt(),
                        generateRandomInt());
                Reader insertedReader = dao.insertReader(reader);
                view.clearConsole();
                view.printReaderInfo(insertedReader);
                break;
            }
            case 4: {
                Subscription subscription = new Subscription(
                        null,
                        generateRandomString(10),
                        generateRandomInt(),
                        generateRandomString(15),
                        1L);
                Subscription insertedSubscription = dao.insertSubscription(subscription);
                view.clearConsole();
                view.printSubscriptionInfo(insertedSubscription);
                break;
            }
        }
    }

    void selectAll(int entity) throws SQLException {
        switch (entity) {
            case 1: {
                List<Author> authors = dao.getAuthorList();
                view.clearConsole();
                view.printAuthors(authors);
                break;
            }
            case 2: {
                List<Book> books = dao.getBookList();
                view.clearConsole();
                view.printBooks(books);
                break;
            }
            case 3: {
                List<Reader> readers = dao.getReaderList();
                view.clearConsole();
                view.printReaders(readers);
                break;
            }
            case 4: {
                List<Subscription> subscriptions = dao.getSubscriptionList();
                view.clearConsole();
                view.printSubscriptions(subscriptions);
                break;
            }
        }
    }
    void selectById(Long id, int entity) throws SQLException {
        switch (entity) {
            case 1: {
                Author author = dao.getAuthor(id);
                view.clearConsole();
                view.printAuthorInfo(author);
                break;
            }
            case 2: {
                Book book = dao.getBook(id);
                view.clearConsole();
                view.printBookInfo(book);
                break;
            }
            case 3: {
                Reader reader = dao.getReader(id);
                view.clearConsole();
                view.printReaderInfo(reader);
                break;
            }
            case 4: {
                Subscription subscription = dao.getSubscription(id);
                view.clearConsole();
                view.printSubscriptionInfo(subscription);
                break;
            }
        }
    }

    void delete(Long id, int entity) throws SQLException {
        view.showDeleted();
        switch (entity) {
            case 1: {
                Author author = dao.deleteAuthor(id);
                view.clearConsole();
                view.printAuthorInfo(author);
                break;
            }
            case 2: {
                Book book = dao.deleteBook(id);
                view.clearConsole();
                view.printBookInfo(book);
                break;
            }
            case 3: {
                Reader reader = dao.deleteReader(id);
                view.clearConsole();
                view.printReaderInfo(reader);
                break;
            }
            case 4: {
                Subscription subscription = dao.deleteSubscription(id);
                view.clearConsole();
                view.printSubscriptionInfo(subscription);
                break;
            }
        }
    }
    void insert(int entity) throws SQLException, IllegalAccessException {
        switch (entity) {
            case 1: {
                Author author = new Author(
                        null,
                        view.getStringOption("author"),
                        new Date(),
                        view.getStringOption("nationality"),
                        view.getBooleanOption("is author alive")
                );

                Author insertedAuthor = dao.insertAuthor(author);
                view.clearConsole();
                view.printAuthorInfo(insertedAuthor);
                break;
            }
            case 2: {
                Book book = new Book(null, view.getStringOption("title"), view.getIntOption("pages count"),
                        view.getStringOption("genre"), view.getStringOption("original language"),
                        view.getLongOption("author"));
                Book insertedBook = dao.insertBook(book);
                view.clearConsole();
                view.printBookInfo(insertedBook);
                break;
            }
            case 3: {
                Reader reader = new Reader(null, view.getStringOption("name"), view.getStringOption("favourite genre"),
                        view.getIntOption("age"), view.getIntOption("finished books"));
                Reader insertReader = dao.insertReader(reader);
                view.clearConsole();
                view.printReaderInfo(insertReader);
                break;
            }
            case 4: {
                Subscription subscription = new Subscription(null, view.getStringOption("type"), view.getIntOption("price"),
                        view.getStringOption("validity"), view.getLongOption("owner"));
                Subscription insertedSubscription = dao.insertSubscription(subscription);
                view.clearConsole();
                view.printSubscriptionInfo(insertedSubscription);
                break;
            }
        }
    }
    void update(int entity) throws SQLException, IllegalAccessException {
        switch (entity) {
            case 1: {
                Author author = new Author(
                        view.getLongOption("id"),
                        view.getStringOption("username"),
                        new Date(),
                        view.getStringOption("nationality"),
                        view.getBooleanOption("is author alive")
                );

                Author updatedAuthor = dao.updateAuthor(author);
                view.clearConsole();
                view.printAuthorInfo(updatedAuthor);
                break;
            }
            case 2: {
                Book book = new Book(view.getLongOption("id"), view.getStringOption("title"), view.getIntOption("pages count"),
                        view.getStringOption("genre"), view.getStringOption("original language"),
                        view.getLongOption("author"));
                Book updatedBook = dao.updateBook(book);
                view.clearConsole();
                view.printBookInfo(updatedBook);
                break;
            }
            case 3: {
                Reader reader = new Reader(view.getLongOption("id"), view.getStringOption("name"), view.getStringOption("favourite genre"),
                        view.getIntOption("age"), view.getIntOption("finished books"));
                Reader updatedReader = dao.updateReader(reader);
                view.clearConsole();
                view.printReaderInfo(updatedReader);
                break;
            }
            case 4: {
                Subscription subscription = new Subscription(view.getLongOption("id"), view.getStringOption("type"), view.getIntOption("price"),
                        view.getStringOption("validity"), view.getLongOption("owner"));
                Subscription updatedSubscription = dao.updateSubscription(subscription);
                view.clearConsole();
                view.printSubscriptionInfo(updatedSubscription);
                break;
            }
        }
    }

    private static final String CHAR_LOWER = "abcdefghijklmnopqrstuvwxyz";
    private static final String CHAR_UPPER = CHAR_LOWER.toUpperCase();
    private static final String NUMBER = "0123456789";

    private static final String DATA_FOR_RANDOM_STRING = CHAR_LOWER + CHAR_UPPER + NUMBER;
    private static SecureRandom random = new SecureRandom();



    private static Boolean generateRandomBoolean() {
        return random.nextBoolean();
    }

    private static int generateRandomInt() {
        return random.nextInt();
    }

    private static String generateRandomString(int length) {
        if (length < 1) throw new IllegalArgumentException();

        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {

            // 0-62 (exclusive), random returns 0-61
            int rndCharAt = random.nextInt(DATA_FOR_RANDOM_STRING.length());
            char rndChar = DATA_FOR_RANDOM_STRING.charAt(rndCharAt);

            sb.append(rndChar);
        }
        return sb.toString();
    }


}