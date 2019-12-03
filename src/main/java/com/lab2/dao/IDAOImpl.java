package com.lab2.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface IDAOImpl<T> {
    T getEntity(Long id) throws SQLException;
    T deleteEntity(Long id) throws SQLException;
    List<T> getEntityList()throws SQLException;
    T updateEntity(T entity) throws SQLException, IllegalAccessException;
    T insertEntity(T entity) throws SQLException, IllegalAccessException;

    List<T> resultSetToList(ResultSet resultSet) throws SQLException;
}
