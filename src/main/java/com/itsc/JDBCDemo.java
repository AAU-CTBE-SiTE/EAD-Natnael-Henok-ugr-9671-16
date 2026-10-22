package com.itsc;

import java.io.FileInputStream;
import java.sql.*;
import java.util.Properties;

public class JDBCDemo {
    public static void main(String[] args) {
        Properties props = new Properties();
        try (FileInputStream in = new FileInputStream("db.properties")) {
            props.load(in);
        } catch (Exception e) {
            System.out.println("Could not read db.properties");
            e.printStackTrace();
            return;
        }

        try (Connection conn = DriverManager.getConnection(
                props.getProperty("url"),
                props.getProperty("user"),
                props.getProperty("password"))) {
            System.out.println("Established Connection");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}