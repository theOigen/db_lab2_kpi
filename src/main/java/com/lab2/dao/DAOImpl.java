package com.lab2.dao;

import com.lab2.annotations.PrimaryKey;
import com.lab2.annotations.TableName;

import java.lang.reflect.Field;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.util.Date;

public class DAOImpl<T> implements IDAOImpl<T> {

    private Class<T> clazz;
    private Connection connection;

    public DAOImpl(Class<T> clazz, Connection connection) {
        this.clazz = clazz;
        this.connection = connection;
    }

    private void getAllFields(List<Field> fields, Class<?> type) {
        fields.addAll(Arrays.asList(type.getDeclaredFields()));

        if (type.getSuperclass() != null) {
            getAllFields(fields, type.getSuperclass());
        }

    }

    private T createEntity(ResultSet resultSet, List<Field> fields) {
        T entity;
        try {
            entity = clazz.getConstructor().newInstance();
            for (Field field: fields) {
                String name = field.getName();
                String value = resultSet.getString(name);
                Class type = field.getType();
                if (value != null) {
                    if (type == boolean.class) {
                        field.set(entity, resultSet.getBoolean(name));
                    } else if (type == java.util.Date.class) {
                        field.set(entity, resultSet.getDate(name));
                    } else {
                        field.set(entity, type.getConstructor(String.class).newInstance(value));
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            entity = null;
        }
        return entity;
    }

    public List<T> resultSetToList(ResultSet resultSet) throws SQLException {
        List<Field> fields = new ArrayList<>();
        getAllFields(fields, clazz);

        fields.forEach(field -> field.setAccessible(true));

        List<T> list = new ArrayList<>();
        resultSet.beforeFirst();
        while (resultSet.next()) {
            T entity = createEntity(resultSet, fields);
            list.add(entity);
        }
        return list;
    }

    @Override
    public T getEntity(Long id) throws SQLException {
        T entity;
        TableName tableAnnotation = clazz.getAnnotation(TableName.class);
        Field primary = getPrimaryField();

        String sql = String.format("SELECT * FROM public.%s WHERE %s = ?", tableAnnotation.name(), primary.getName());
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
    public T deleteEntity(Long id) throws SQLException {
        TableName tableAnnotation = clazz.getAnnotation(TableName.class);
        Field primary = getPrimaryField();

        String sql = String.format("DELETE FROM public.%s WHERE %s = ? RETURNING *;",
                tableAnnotation.name(), primary.getName());

        PreparedStatement preparedStatement = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
        preparedStatement.setLong(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();

        T deletedEntity;
        try {
            deletedEntity = resultSetToList(resultSet).get(0);
        } catch (IndexOutOfBoundsException ex) {
            deletedEntity = null;
        }

        return deletedEntity;
    }

    @Override
    public T updateEntity(T entity) throws SQLException, IllegalAccessException {
        TableName tableAnnotation = clazz.getAnnotation(TableName.class);
        Field primary = getPrimaryField();

        String sql = String.format("UPDATE public.%s SET %s WHERE %s = ? RETURNING *;",
                tableAnnotation.name(), getFieldSqlString(entity), primary.getName());

        PreparedStatement preparedStatement = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
        preparedStatement.setLong(1, (Long) primary.get(entity));
        ResultSet resultSet = preparedStatement.executeQuery();

        T updatedEntity;
        try {
            updatedEntity = resultSetToList(resultSet).get(0);
        } catch (IndexOutOfBoundsException ex) {
            updatedEntity = null;
        }

        return updatedEntity;
    }

    private Field getPrimaryField() {
        List<Field> fields = new ArrayList<>();
        getAllFields(fields, clazz);

        Field primaryField = null;
        for(Field field: fields) {
            field.setAccessible(true);
            if (field.isAnnotationPresent(PrimaryKey.class)) {
                primaryField = field;
            }
        }
        return primaryField;
    }

    private String getRowsString(T entity) throws IllegalAccessException {
        List<Field> fields = new ArrayList<>();
        getAllFields(fields, clazz);

        List<String> rows = new ArrayList<>();
        for (Field field : fields) {
            field.setAccessible(true);
            Object value = field.get(entity);
            if (value != null) {
                rows.add(field.getName());
            }
        }

        return String.join(", ", rows);
    }

    private String getValuesPrepareString(T entity) throws IllegalAccessException {
        List<Field> fields = new ArrayList<>();
        getAllFields(fields, clazz);

        List<String> values = new ArrayList<>();
        for (Field field : fields) {
            field.setAccessible(true);
            Object value = field.get(entity);
            if (value != null) {
                values.add("?");
            }
        }

        return String.join(", ", values);
    }


    @Override
    public T insertEntity(T entity) throws SQLException, IllegalAccessException {
        TableName tableAnnotation = clazz.getAnnotation(TableName.class);
        List<Field> fields = new ArrayList<>();
        getAllFields(fields, clazz);

        String sql = String.format("INSERT INTO public.%s (%s) VALUES (%s) RETURNING *;",
                tableAnnotation.name(), getRowsString(entity), getValuesPrepareString(entity));

        PreparedStatement preparedStatement = connection.prepareStatement(
                sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY
        );


        int parameterIndex = 1;
        for (Field field : fields) {
            field.setAccessible(true);
            Class type = field.getType();
            Object value = field.get(entity);

            if (value != null) {
                if (type == Long.class) {
                    preparedStatement.setLong(parameterIndex, (Long) field.get(entity));
                } else if (type == boolean.class) {
                    preparedStatement.setBoolean(parameterIndex, (boolean) field.get(entity));
                } else if (type == java.util.Date.class) {
                    java.sql.Date date = new java.sql.Date(((java.util.Date) field.get(entity)).getTime());
                    preparedStatement.setDate(parameterIndex, date);
                } else if (type == Integer.class) {
                    preparedStatement.setInt(parameterIndex, (Integer) field.get(entity));
                } else {
                    preparedStatement.setString(parameterIndex, String.valueOf(field.get(entity)));
                }
                parameterIndex += 1;
            }
        }

        ResultSet resultSet = preparedStatement.executeQuery();
        T insertedEntity;
        try {
            insertedEntity = resultSetToList(resultSet).get(0);
        } catch (IndexOutOfBoundsException ex) {
            insertedEntity = null;
        }

        return insertedEntity;
    }

    private String getFieldSqlString(T entity) throws IllegalAccessException {
        List<Field> fields = new ArrayList<>();
        getAllFields(fields, clazz);

        StringBuilder sql = new StringBuilder();
        for(int fieldId = 0; fieldId < fields.size(); fieldId++) {
            Field field = fields.get(fieldId);
            field.setAccessible(true);
            sql.append(String.format("%s = '%s'", field.getName(), field.get(entity)));
            if(fieldId != fields.size() - 1) {
                sql.append(", ");
            }
        }

        return sql.toString();
    }

}
