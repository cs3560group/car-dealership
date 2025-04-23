package com.dealership.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static final String URL = System.getenv("DB_URL") != null ? System.getenv("DB_URL") : "jdbc:mysql://localhost:3306/your_database_name";
    private static final String USER = System.getenv("DB_USER") != null ? System.getenv("DB_USER") : "YOUR_USERNAME";
    // Replace YOUR_USERNAME with your database credentials
    private static final String PASSWORD = System.getenv("DB_PASSWORD") != null ? System.getenv("DB_PASSWORD") : "YOUR_PASSWORD";
    // Replace DB_PASSWORD with your database credentials

    /**
     * Establishes a connection to the database.
     *
     * @return a Connection object
     * @throws SQLException if a database access error occurs
     */
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    /**
     * Closes the database connection.
     *
     * @param connection the connection to close
     */
    public static void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}