package com.lab2.dao;

import java.sql.SQLException;
import java.util.List;

public interface IDAOImpl<T> {
    T getEntity(Long id) throws SQLException;
    boolean deleteEntity(T entity) throws SQLException;
    List<T> getEntityList()throws SQLException;
}
