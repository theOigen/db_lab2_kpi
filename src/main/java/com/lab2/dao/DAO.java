package com.lab2.dao;

import com.lab2.model.Author;
import com.lab2.model.Book;
import com.lab2.model.Reader;
import com.lab2.model.Subscription;

import java.sql.*;
import java.util.List;

public class DAO implements IDAO {

    private IDAOImpl<Author> authorsDAOImpl;
    private IDAOImpl<Book> booksDAOImpl;
    private IDAOImpl<Reader> readersDAOImpl;
    private IDAOImpl<Subscription> subscriptionsDAOImpl;
    private Connection connection;

    public void connect(String url, String user, String password) throws SQLException {
        connection = DriverManager.getConnection(url, user, password);
        authorsDAOImpl = new DAOImpl<>(Author.class);
        booksDAOImpl = new DAOImpl<>(Book.class);
        readersDAOImpl = new DAOImpl<>(Reader.class);
        subscriptionsDAOImpl = new DAOImpl<>(Subscription.class);
    }

    @Override
    public Author getAuthor(Long id) throws SQLException {
        return authorsDAOImpl.getEntity(id);
    }

    @Override
    public Author deleteAuthor(Long id) throws SQLException {
        return authorsDAOImpl.deleteEntity(id);
    }

    @Override
    public Author updateAuthor(Author a) throws SQLException, IllegalAccessException {
        return authorsDAOImpl.updateEntity(a);
    }

    @Override
    public Author insertAuthor(Author a) throws SQLException, IllegalAccessException {
        return authorsDAOImpl.insertEntity(a);
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
    public Book deleteBook(Long id) throws SQLException {
        return booksDAOImpl.deleteEntity(id);
    }

    @Override
    public Book updateBook(Book b) throws SQLException, IllegalAccessException {
        return booksDAOImpl.updateEntity(b);
    }

    @Override
    public Book insertBook(Book b) throws SQLException, IllegalAccessException {
        return booksDAOImpl.insertEntity(b);
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
    public Reader deleteReader(Long id) throws SQLException {
        return readersDAOImpl.deleteEntity(id);
    }

    @Override
    public Reader updateReader(Reader r) throws SQLException, IllegalAccessException {
        return readersDAOImpl.updateEntity(r);
    }

    @Override
    public Reader insertReader(Reader r) throws SQLException, IllegalAccessException {
        return readersDAOImpl.insertEntity(r);
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
    public Subscription deleteSubscription(Long id) throws SQLException {
        return subscriptionsDAOImpl.deleteEntity(id);
    }

    @Override
    public Subscription updateSubscription(Subscription s) throws SQLException, IllegalAccessException {
        return subscriptionsDAOImpl.updateEntity(s);
    }

    @Override
    public Subscription insertSubscription(Subscription s) throws SQLException, IllegalAccessException {
        return subscriptionsDAOImpl.insertEntity(s);
    }

    @Override
    public List<Subscription> getSubscriptionList() throws SQLException {
        return subscriptionsDAOImpl.getEntityList();
    }

    @Override
    public List<Book> searchWord(String word, boolean including) throws SQLException {
        String sql = "SELECT bid, pages_count, title, genre, original_language, author,"
                + " ts_headline(title, q, 'StartSel=<!>, StopSel=<!>') as title,"
                + " ts_headline(genre, q, 'StartSel=<!>, StopSel=<!>') as genre,"
                + " ts_headline(original_language, q, 'StartSel=<!>, StopSel=<!>') as original_language"
                + " FROM public.book , plainto_tsquery(?) as q"
                + " WHERE " + (including ? "" : "not")
                + " to_tsvector(title) || to_tsvector(genre) || to_tsvector(original_language) @@ q";

        PreparedStatement preparedStatement = connection.prepareStatement(
                sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY
        );
        preparedStatement.setString(1, word);
        ResultSet resultSet = preparedStatement.executeQuery();

        return booksDAOImpl.resultSetToList(resultSet);
    }

    @Override
    public ResultSet joinedBookSearch(int pagesStart, int pagesEnd, boolean isAuthorAlive) throws SQLException {
        String sql = "SELECT * FROM public.book INNER JOIN public.author ON author.aid = book.author "
                + "WHERE (isalive = ? AND pages_count BETWEEN ? AND ?)";

        PreparedStatement preparedStatement = connection.prepareStatement(
                sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY
        );
        preparedStatement.setBoolean(1, isAuthorAlive);
        preparedStatement.setInt(2, pagesStart);
        preparedStatement.setInt(3, pagesEnd);

        return preparedStatement.executeQuery();
    }
}
