package com.lab2;

import com.lab2.controller.Controller;
import com.lab2.dao.DAO;
import com.lab2.view.View;

import java.io.FileInputStream;
import java.io.IOException;
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
        String username = props.getProperty("db.user");
        String password = props.getProperty("db.password");

        try {
            View view = new View();
            DAO dao = new DAO();
            dao.connect(url, username, password);

            Controller controller = new Controller(view, dao);
            controller.mainMenu();
        } catch (Exception ex) {
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
        }
    }
}
