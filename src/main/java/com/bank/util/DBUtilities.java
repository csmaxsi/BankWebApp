package com.bank.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtilities {
    private static final String URL = "jdbc:mysql://localhost:3306/bank_management";
    private static final String USER = "programmer";
    private static final String PASSWORD = "CSmaxsi@96$";

    // Static block to load the MySQL driver
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println("Failed to load MySQL driver: " + e.getMessage());
            throw new RuntimeException("Failed to initialize database connection", e);
        }
    }

    // Method to get a connection to the database
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    // Private constructor to prevent instantiation
    private DBUtilities() {
        // Utility class should not be instantiated
    }
}
