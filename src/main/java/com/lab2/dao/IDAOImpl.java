package com.lab2.dao;

import java.util.List;

public interface IDAOImpl<T> {
    T getEntity(Long id);
    List<T> getEntityList();
}
