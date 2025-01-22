package com.bank;

import java.sql.Connection;
import java.sql.SQLException;

public class DBUtilitiesTest {
    public static void main(String[] args) {
        // Test the database connection
        try (Connection connection = DBUtilities.getConnection()) {
            if (connection != null) {
                System.out.println("Database connected successfully!");
            } else {
                System.out.println("Failed to make connection!");
            }
        } catch (SQLException e) {
            System.err.println("SQL Exception: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Unexpected Exception: " + e.getMessage());
        }
    }
}
