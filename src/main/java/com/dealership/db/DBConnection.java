package com.dealership.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import io.github.cdimascio.dotenv.Dotenv;

public class DBConnection {
    // Set the database URL, user, and password from environment variables
    private static final Dotenv dotenv = Dotenv.load();
    private static final String USER = dotenv.get("DB_USER");
    private static final String PASSWORD = dotenv.get("DB_PASSWORD");
    private static final String URL = dotenv.get("DB_URL");

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
    @SuppressWarnings("CallToPrintStackTrace")
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