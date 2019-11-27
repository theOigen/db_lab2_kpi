package com.lab2;

import com.lab2.dao.DAO;
import com.lab2.model.Student;
import com.lab2.model.Teacher;
import com.lab2.model.Mark;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.List;
import java.util.Properties;

public class Main {

    public static void main(String[] args) {

        Properties props = new Properties();

        try {
            FileInputStream in = new FileInputStream(System.getProperty("user.dir") + "/db.properties");
            props.load(in);
            in.close();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
            return;
        }

        String url = props.getProperty("db.url");
        String user = props.getProperty("db.user");
        String password = props.getProperty("db.password");

        try {
            DAO dao = new DAO();
            dao.connect(url, user, password);

            System.out.println("====> BEFORE DELETE: ");
            System.out.println("====> Students: ");
            List<Student> students = dao.getStudentList();
            students.forEach(student -> System.out.println(student.getId().toString() + ") " + student.getName()));
            System.out.println("====> Teachers: ");
            List<Teacher> teachers = dao.getTeacherList();
            teachers.forEach(teacher ->  System.out.println(teacher.getId().toString() + ") " + teacher.getName()));

            System.out.println("====> Marks: ");
            List<Mark> marks = dao.getMarkList();
            marks.forEach(mark -> System.out.println(mark.getId().toString() + ") " + mark.getValue().toString()));

            System.out.println("====> AFTER DELETE: ");
            dao.deleteStudent(students.get(0));
            dao.deleteTeacher(teachers.get(0));
            dao.deleteMark(marks.get(0));
            System.out.println("====> Students: ");
            List<Student> new_students = dao.getStudentList();
            new_students.forEach(student -> System.out.println(student.getId().toString() + ") " + student.getName()));
            System.out.println("====> Teachers: ");
            List<Teacher> new_teachers = dao.getTeacherList();
            new_teachers.forEach(teacher ->  System.out.println(teacher.getId().toString() + ") " + teacher.getName()));

            System.out.println("====> Marks: ");
            List<Mark> new_marks = dao.getMarkList();
            new_marks.forEach(mark -> System.out.println(mark.getId().toString() + ") " + mark.getValue().toString()));
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
