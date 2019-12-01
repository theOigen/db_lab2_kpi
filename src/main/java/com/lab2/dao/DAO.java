package com.lab2.dao;

import com.lab2.model.Author;
import com.lab2.model.Book;
import com.lab2.model.Reader;
import com.lab2.model.Subscription;

import java.sql.*;
import java.util.List;

public class DAO implements IDAO {

    IDAOImpl<Author> authorsDAOImpl;
    IDAOImpl<Book> booksDAOImpl;
    IDAOImpl<Reader> readersDAOImpl;
    IDAOImpl<Subscription> subscriptionsDAOImpl;
    Connection connection;

    public void connect(String url, String user, String password) throws SQLException {
        connection = DriverManager.getConnection(url, user, password);
        authorsDAOImpl = new DAOImpl<>(Author.class, connection);
        booksDAOImpl = new DAOImpl<>(Book.class, connection);
        readersDAOImpl = new DAOImpl<>(Reader.class, connection);
        subscriptionsDAOImpl = new DAOImpl<>(Subscription.class, connection);
    }

    @Override
    public Author getAuthor(Long id) throws SQLException {
        return authorsDAOImpl.getEntity(id);
    }

    @Override
    public boolean deleteAuthor(Author a) throws SQLException {
        return authorsDAOImpl.deleteEntity(a);
    }

    @Override
    public List<Author> getAuthorList() throws SQLException {
        return authorsDAOImpl.getEntityList();
    }

    @Override
    public Book getBook(Long id) throws SQLException {
        return booksDAOImpl.getEntity(id);
    }

    @Override
    public boolean deleteBook(Book b) throws SQLException {
        return booksDAOImpl.deleteEntity(b);
    }

    @Override
    public List<Book> getBookList() throws SQLException {
        return booksDAOImpl.getEntityList();
    }

    @Override
    public Reader getReader(Long id) throws SQLException {
        return readersDAOImpl.getEntity(id);
    }

    @Override
    public boolean deleteReader(Reader r) throws SQLException {
        return readersDAOImpl.deleteEntity(r);
    }

    @Override
    public List<Reader> getReaderList() throws SQLException {
        return readersDAOImpl.getEntityList();
    }

    @Override
    public Subscription getSubscription(Long id) throws SQLException {
        return subscriptionsDAOImpl.getEntity(id);
    }

    @Override
    public boolean deleteSubscription(Subscription s) throws SQLException {
        return subscriptionsDAOImpl.deleteEntity(s);
    }

    @Override
    public List<Subscription> getSubscriptionList() throws SQLException {
        return subscriptionsDAOImpl.getEntityList();
    }
}
