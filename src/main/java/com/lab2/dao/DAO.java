package com.lab2.dao;

import com.lab2.model.Mark;
import com.lab2.model.Student;
import com.lab2.model.Teacher;

import java.sql.*;
import java.util.List;

public class DAO implements IDAO {

    IDAOImpl<Student> studentsDAOImpl;
    IDAOImpl<Teacher> teachersDAOImpl;
    IDAOImpl<Mark> marksDAOImpl;
    Connection connection;

    public void connect(String url, String user, String password) throws SQLException {
        connection = DriverManager.getConnection(url, user, password);
        studentsDAOImpl = new DAOImpl<Student>(Student.class, connection);
        teachersDAOImpl = new DAOImpl<Teacher>(Teacher.class, connection);
        marksDAOImpl = new DAOImpl<Mark>(Mark.class, connection);
    }

    @Override
    public Student getStudent(Long id) throws SQLException {
        return studentsDAOImpl.getEntity(id);
    }

    @Override
    public boolean deleteStudent(Student s) throws SQLException {
        return studentsDAOImpl.deleteEntity(s);
    }

    @Override
    public List<Student> getStudentList() throws SQLException {
        return studentsDAOImpl.getEntityList();
    }

    @Override
    public Teacher getTeacher(Long id) throws SQLException {
        return teachersDAOImpl.getEntity(id);
    }

    @Override
    public boolean deleteTeacher(Teacher t) throws SQLException {
        return teachersDAOImpl.deleteEntity(t);
    }

    @Override
    public List<Teacher> getTeacherList() throws SQLException {
        return teachersDAOImpl.getEntityList();
    }

    @Override
    public Mark getMark(Long id) throws SQLException {
        return marksDAOImpl.getEntity(id);
    }

    @Override
    public boolean deleteMark(Mark m) throws SQLException {
        return marksDAOImpl.deleteEntity(m);
    }

    @Override
    public List<Mark> getMarkList() throws SQLException {
        return marksDAOImpl.getEntityList();
    }
}
