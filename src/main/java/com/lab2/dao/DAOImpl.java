package com.lab2.dao;

import com.lab2.annotations.DiscriminationColumn;
import com.lab2.annotations.DiscriminatorValue;
import com.lab2.annotations.PrimaryKey;
import com.lab2.annotations.TableName;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

public class DAOImpl<T> implements IDAOImpl<T> {

    private Class<T> clazz;
    private Connection connection;

    public DAOImpl(Class<T> clazz, Connection connection) {
        this.clazz = clazz;
        this.connection = connection;
    }

    private List<Field> getAllFields(List<Field> fields, Class<?> type) {
        fields.addAll(Arrays.asList(type.getDeclaredFields()));

        if (type.getSuperclass() != null) {
            getAllFields(fields, type.getSuperclass());
        }

        return fields;
    }

    private T createEntity(ResultSet resultSet, List<Field> fields) {
        T entity;
        try {
            entity = clazz.getConstructor().newInstance();
            for (Field field: fields) {
                String name = field.getName();
                String value = resultSet.getString(name);
                Class type = field.getType();
                field.set(entity, type.isEnum()
                        ? type.getDeclaredMethod("valueOf", String.class).invoke(null, value)
                        : type.getConstructor(String.class).newInstance(value));
            }
        } catch (Exception e) {
            e.printStackTrace();
            entity = null;
        }
        return entity;
    }

    private List<T> resultSetToList(ResultSet resultSet) throws SQLException {
        DiscriminationColumn columnAnnotation = clazz.getAnnotation(DiscriminationColumn.class);
        String discriminatorColumn = columnAnnotation == null ? null : columnAnnotation.name();
        DiscriminatorValue discriminatorAnnotation = clazz.getAnnotation(DiscriminatorValue.class);
        String discriminator = discriminatorAnnotation == null ? null : discriminatorAnnotation.value();

        List<Field> fields = new ArrayList<>();
        getAllFields(fields, clazz);

        fields.forEach(field -> field.setAccessible(true));

        List<T> list = new ArrayList<>();
        resultSet.beforeFirst();
        while (resultSet.next()) {
            if(discriminatorColumn == null || resultSet.getString(discriminatorColumn).equals(discriminator)) {
                T entity = createEntity(resultSet, fields);
                list.add(entity);
            }
        }
        return list;
    }

    @Override
    public T getEntity(Long id) throws SQLException {
        T entity;
        TableName tableAnnotation = clazz.getAnnotation(TableName.class);

        String sql = String.format("SELECT * FROM public.%s WHERE id = ?", tableAnnotation.name());
        PreparedStatement preparedStatement = connection.prepareStatement(
                sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY
        );
        preparedStatement.setLong(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();

        try {
            entity = resultSetToList(resultSet).get(0);
        } catch (IndexOutOfBoundsException ex) {
            entity = null;
        }

        return entity;
    }

    @Override
    public List<T> getEntityList() throws SQLException {
        TableName tableAnnotation = clazz.getAnnotation(TableName.class);

        String sql = String.format("SELECT * FROM public.%s", tableAnnotation.name());
        PreparedStatement preparedStatement = connection.prepareStatement(
                sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet resultSet = preparedStatement.executeQuery();

        return resultSetToList(resultSet);
    }

    @Override
    public boolean deleteEntity(T entity) throws SQLException {
        TableName tableAnnotation = clazz.getAnnotation(TableName.class);
        List<Field> fields = new ArrayList<>();
        getAllFields(fields, clazz);

        Field primary = null;
        for (Field field : fields) {
            field.setAccessible(true);
            if (field.isAnnotationPresent(PrimaryKey.class)) {
                primary = field;
            }
        }

        String sql = String.format("DELETE FROM public.%s WHERE %s = ?",
                tableAnnotation.name(), primary.getName());

        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        try {
            preparedStatement.setLong(1, (Long) primary.get(entity));
            preparedStatement.executeQuery();
        } catch (Exception e) {
            return false;
        }
        return true;
    }

}
