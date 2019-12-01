package com.lab2.dao;

import com.lab2.model.Author;
import com.lab2.model.Book;
import com.lab2.model.Reader;
import com.lab2.model.Subscription;

import java.sql.SQLException;
import java.util.List;

public interface IDAO {
    Author getAuthor(Long id) throws SQLException;
    boolean deleteAuthor(Author a) throws SQLException;
    List<Author> getAuthorList() throws SQLException;

    Book getBook(Long id) throws SQLException;
    boolean deleteBook(Book b) throws SQLException;
    List<Book> getBookList() throws SQLException;

    Reader getReader(Long id) throws SQLException;
    boolean deleteReader(Reader r) throws SQLException;
    List<Reader> getReaderList() throws SQLException;

    Subscription getSubscription(Long id) throws SQLException;
    boolean deleteSubscription(Subscription s) throws SQLException;
    List<Subscription> getSubscriptionList() throws SQLException;
}
