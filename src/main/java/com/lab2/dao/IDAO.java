package com.lab2.dao;

import com.lab2.model.Mark;
import com.lab2.model.Student;
import com.lab2.model.Teacher;

import java.util.List;

public interface IDAO {
    Student getStudent(Long id);
    List<Student> getStudentList();

    Teacher getTeacher(Long id);
    List<Teacher> getTeacherList();

    Mark getMark(Long id);
    List<Mark> getMarkList();
}
