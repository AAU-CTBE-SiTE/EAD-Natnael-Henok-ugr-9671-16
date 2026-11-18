package com.itsc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Task1Connection {

    // Database connection details
    private static final String SERVER_URL = "jdbc:mysql://localhost:3306/";
    private static final String DB_NAME = "StudentsDB";
    private static final String USERNAME = "Mekedelawit";
    private static final String PASSWORD = "0000";

    public static void main(String[] args) {
        Connection connection = null;
        Statement statement = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            System.out.println("Connecting to MySQL server...");
            connection = DriverManager.getConnection(SERVER_URL, USERNAME, PASSWORD);
            System.out.println("Connected to MySQL server successfully!");

            statement = connection.createStatement();

            statement.executeUpdate("CREATE DATABASE IF NOT EXISTS " + DB_NAME);
            System.out.println("Database '" + DB_NAME + "' created or verified.");

            statement.execute("USE " + DB_NAME);

            statement.execute("DROP TABLE IF EXISTS students");

            // 'students table with my attributes
            String createTableSQL = "CREATE TABLE students ("
                    + "id INT PRIMARY KEY, "
                    + "firstname VARCHAR(50) NOT NULL, "
                    + "lastname VARCHAR(50) NOT NULL, "
                    + "student_email VARCHAR(100) UNIQUE, "
                    + "enrollment_year SMALLINT DEFAULT 2026, "
                    + "grade DECIMAL(4, 2) NOT NULL)";
            statement.execute(createTableSQL);
            System.out.println("Table 'students' created successfully with customized use-case schema.");

            // added university course records
            String[] initialRecords = {
                    "INSERT INTO students (id, firstname, lastname, student_email, enrollment_year, grade) " +
                            "VALUES (1001, 'Kidus', 'Tadesse', 'kidus.t@aau.edu.et', 2024, 88.50)",

                    "INSERT INTO students (id, firstname, lastname, student_email, enrollment_year, grade) " +
                            "VALUES (1002, 'Blen', 'Mulugeta', 'blen.m@aau.edu.et', 2025, 93.75)",

                    "INSERT INTO students (id, firstname, lastname, student_email, enrollment_year, grade) " +
                            "VALUES (1003, 'Yonas', 'Girma', 'yonas.g@aau.edu.et', 2024, 76.20)",

                    "INSERT INTO students (id, firstname, lastname, student_email, enrollment_year, grade) " +
                            "VALUES (1004, 'Selam', 'Bekele', 'selam.b@aau.edu.et', 2026, 91.00)"
            };

            for (String sql : initialRecords) {
                statement.executeUpdate(sql);
            }
            System.out.println("Custom student records inserted successfully.");

            // displays table in the console for verification
            System.out.println("\n--- Table Schema (DESCRIBE students) ---");
            ResultSet rs = statement.executeQuery("DESCRIBE students");
            System.out.printf("%-18s %-18s %-8s %-6s%n", "Field", "Type", "Null", "Key");
            System.out.println("-----------------------------------------------------");
            while (rs.next()) {
                System.out.printf("%-18s %-18s %-8s %-6s%n",
                        rs.getString("Field"),
                        rs.getString("Type"),
                        rs.getString("Null"),
                        rs.getString("Key"));
            }
            rs.close();

        } catch (ClassNotFoundException e) {
            System.err.println("Driver not found. Please ensure the connector JAR is in your classpath.");
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (statement != null) statement.close();
                if (connection != null) connection.close();
                System.out.println("\nDatabase connection closed.");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
