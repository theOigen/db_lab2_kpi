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

            System.out.println("====> Students: ");
            List<Student> students = dao.getStudentList();
            students.forEach(student -> System.out.println(student.getId().toString() + ") " + student.getName()));

            System.out.println("====> Teachers: ");
            List<Teacher> teachers = dao.getTeacherList();
            teachers.forEach(teacher ->  System.out.println(teacher.getId().toString() + ") " + teacher.getName()));

            System.out.println("====> Marks: ");
            List<Mark> marks = dao.getMarkList();
            marks.forEach(mark -> System.out.println(mark.getId().toString() + ") " + mark.getValue().toString()));
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
