package com.lab2.dao;

import com.lab2.model.Mark;
import com.lab2.model.Student;
import com.lab2.model.Teacher;

import java.sql.SQLException;
import java.util.List;

public interface IDAO {
    Student getStudent(Long id) throws SQLException;
    List<Student> getStudentList() throws SQLException;

    Teacher getTeacher(Long id) throws SQLException;
    List<Teacher> getTeacherList() throws SQLException;

    Mark getMark(Long id) throws SQLException;
    List<Mark> getMarkList() throws SQLException;
}
