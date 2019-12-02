package com.lab2.dao;

import java.sql.SQLException;
import java.util.List;

public interface IDAOImpl<T> {
    T getEntity(Long id) throws SQLException;
    boolean deleteEntity(T entity) throws SQLException;
    List<T> getEntityList()throws SQLException;
    T updateEntity(T entity) throws SQLException, IllegalAccessException;
    boolean insertEntity(T entity) throws SQLException, IllegalAccessException;
}
