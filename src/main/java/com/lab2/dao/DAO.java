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

    @Override
    public Student getStudent(Long id) {
        return studentsDAOImpl.getEntity(id);
    }

    @Override
    public List<Student> getStudentList() {
        return studentsDAOImpl.getEntityList();
    }

    @Override
    public Teacher getTeacher(Long id) {
        return teachersDAOImpl.getEntity(id);
    }

    @Override
    public List<Teacher> getTeacherList() {
        return teachersDAOImpl.getEntityList();
    }

    @Override
    public Mark getMark(Long id) {
        return marksDAOImpl.getEntity(id);
    }

    @Override
    public List<Mark> getMarkList() {
        return marksDAOImpl.getEntityList();
    }
}
