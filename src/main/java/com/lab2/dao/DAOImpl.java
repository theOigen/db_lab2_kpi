package com.lab2.dao;

import com.lab2.utils.HibernateSessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.*;
import java.util.List;

public class DAOImpl<T> implements IDAOImpl<T> {

    private Class<T> clazz;

    public DAOImpl(Class<T> clazz) {
        this.clazz = clazz;
    }

    public List<T> resultSetToList(ResultSet resultSet) throws SQLException {
        return null;
    }

    @Override
    public T getEntity(Long id) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(clazz, id);
    }

    @Override
        public List<T> getEntityList() {
        return  (List<T>)HibernateSessionFactoryUtil.getSessionFactory().openSession().createCriteria(clazz).list();
    }

    @Override
    public List<T> insertEntityList(List<T> entities) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        for (T entity: entities) {
            session.save(entity);
        }
        tx1.commit();
        session.close();
        return entities;
    }

    @Override
    public T deleteEntity(Long id) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        T deletedEntity = (T)session.load(clazz, id);
        session.delete(deletedEntity);
        tx1.commit();
        session.close();
        return deletedEntity;
    }

    @Override
    public T updateEntity(T entity) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(entity);
        tx1.commit();
        session.close();
        return entity;
    }

    @Override
    public T insertEntity(T entity) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(entity);
        tx1.commit();
        session.close();
        return entity;
    }
}
