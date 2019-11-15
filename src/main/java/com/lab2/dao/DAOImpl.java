package com.lab2.dao;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

public class DAOImpl<T> implements IDAOImpl<T> {

    Class<T> clazz;
    Connection connection;

    PreparedStatement statement;

    public DAOImpl(Class<T> clazz, Connection connection) {
        this.clazz = clazz;
        this.connection = connection;
    }

    @Override
    public T getEntity(Long id) {

//        T obj = clazz.newInstance();
//
//        String name = "Student 1";
//        Integer age = 20;
//        clazz.getName(); // name of table
//        Field[] fields = clazz.getFields();
//
//        fields[0].getName(); // info about columns
//
//        String nameSetter = "setName";
//
//        Method m;
//        try {
//            m = clazz.getMethod(nameSetter, String.class);
//        } catch (NoSuchMethodException e) {
//            e.printStackTrace();
//        }
//
//        m.invoke(obj, name);
//
//        try {
//            T entity = clazz.newInstance();
//        } catch (InstantiationException e) {
//            e.printStackTrace();
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        }


        return null;
    }

    @Override
    public List<T> getEntityList() {
        return null;
    }

}
