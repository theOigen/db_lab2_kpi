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
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {

    public static void main(String[] args) {

        Properties props = new Properties();
        Logger lgr = Logger.getLogger(Main.class.getName());

        try {
            FileInputStream in = new FileInputStream(System.getProperty("user.dir") + "/db.properties");
            props.load(in);
            in.close();
        } catch (IOException ex) {
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
            return;
        }

        String url = props.getProperty("db.url");
        String user = props.getProperty("db.user");
        String password = props.getProperty("db.password");

        try {
            DAO dao = new DAO();
            dao.connect(url, user, password);

            System.out.println("Students: ");
            List<Student> students = dao.getStudentList();
            students.forEach(student -> System.out.println(student.getName()));

            System.out.println("Teachers: ");
            List<Teacher> teachers = dao.getTeacherList();
            teachers.forEach(teacher ->  System.out.println(teacher.getName()));

            System.out.println("Marks: ");
            List<Mark> marks = dao.getMarkList();
            marks.forEach(mark -> System.out.println(mark.getValue()));
        } catch (SQLException ex) {
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
        }
    }
}
