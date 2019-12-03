package com.lab2.dao;

import com.lab2.model.Author;
import com.lab2.model.Book;
import com.lab2.model.Reader;
import com.lab2.model.Subscription;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface IDAO {
    Author getAuthor(Long id) throws SQLException;
    Author deleteAuthor(Long id) throws SQLException;
    Author updateAuthor(Author a) throws SQLException, IllegalAccessException;
    Author insertAuthor(Author a) throws SQLException, IllegalAccessException;
    List<Author> getAuthorList() throws SQLException;

    Book getBook(Long id) throws SQLException;
    Book deleteBook(Long id) throws SQLException;
    Book updateBook(Book b) throws SQLException, IllegalAccessException;
    Book insertBook(Book b) throws SQLException, IllegalAccessException;
    List<Book> getBookList() throws SQLException;

    Reader getReader(Long id) throws SQLException;
    Reader deleteReader(Long id) throws SQLException;
    Reader updateReader(Reader r) throws SQLException, IllegalAccessException;
    Reader insertReader(Reader r) throws SQLException, IllegalAccessException;
    List<Reader> getReaderList() throws SQLException;

    Subscription getSubscription(Long id) throws SQLException;
    Subscription deleteSubscription(Long id) throws SQLException;
    Subscription updateSubscription(Subscription s) throws SQLException, IllegalAccessException;
    Subscription insertSubscription(Subscription s) throws SQLException, IllegalAccessException;
    List<Subscription> getSubscriptionList() throws SQLException;

    List<Book> searchWord(String word, boolean including) throws SQLException;
    ResultSet joinedBookSearch(int pagesStart, int pagesEnd, boolean isAuthorAlive) throws SQLException;
}
