package com.itsc;

import java.io.FileInputStream;
import java.sql.*;
import java.util.Properties;

public class StudentOps {
    public static void main(String[] args) throws Exception {
        Properties p = new Properties();
        try (FileInputStream in = new FileInputStream("db.properties")) { p.load(in); }

        try (Connection conn = DriverManager.getConnection(
                p.getProperty("url"), p.getProperty("user"), p.getProperty("password"))) {

            // Task 1: create table
            try (Statement st = conn.createStatement()) {
                st.execute("CREATE TABLE IF NOT EXISTS students ("
                        + "id INT PRIMARY KEY, firstname VARCHAR(255), "
                        + "lastname VARCHAR(255), grade INT)");
            }

            // Task 2: insert (INSERT IGNORE so re-running doesn't crash on duplicate ids)
            String ins = "INSERT IGNORE INTO students (id, firstname, lastname, grade) VALUES (?,?,?,?)";
            try (PreparedStatement ps = conn.prepareStatement(ins)) {
                Object[][] rows = {
                        {1, "John", "Doe", 90},     {2, "Aster", "Nega", 85},
                        {3, "Jemal", "Edris", 78},  {4, "Haile", "Anaol", 92},
                        {5, "Teddy", "Habtu", 66},  {6, "Teklay", "Michael", 88},
                        {7, "Memar", "Alebachew", 71}, {8, "Sara", "Tadesse", 95},
                        {9, "Abel", "Kebede", 60},  {10, "Liya", "Bekele", 82},
                        {11, "Dawit", "Girma", 74}
                };
                for (Object[] r : rows) {
                    ps.setInt(1, (int) r[0]);
                    ps.setString(2, (String) r[1]);
                    ps.setString(3, (String) r[2]);
                    ps.setInt(4, (int) r[3]);
                    ps.executeUpdate();
                }
            }

            // Task 3: retrieve 5 rows
            try (Statement st = conn.createStatement();
                 ResultSet rs = st.executeQuery("SELECT * FROM students LIMIT 5")) {
                while (rs.next()) {
                    System.out.println(rs.getInt("id") + " " + rs.getString("firstname")
                            + " " + rs.getString("lastname") + " - " + rs.getInt("grade"));
                }
            }

            // Task 4: update
            try (PreparedStatement ps = conn.prepareStatement(
                    "UPDATE students SET firstname = ? WHERE id = ?")) {
                ps.setString(1, "UpdatedName");
                ps.setInt(2, 1);
                System.out.println("Updated rows: " + ps.executeUpdate());
            }

            // Task 5: delete
            try (PreparedStatement ps = conn.prepareStatement(
                    "DELETE FROM students WHERE id = ?")) {
                ps.setInt(1, 2);
                System.out.println("Deleted rows: " + ps.executeUpdate());
            }

            // Task 6: average grade
            try (Statement st = conn.createStatement();
                 ResultSet rs = st.executeQuery("SELECT AVG(grade) AS avg_grade FROM students")) {
                if (rs.next()) System.out.println("Average Grade: " + rs.getDouble("avg_grade"));
            }
        }
    }
}