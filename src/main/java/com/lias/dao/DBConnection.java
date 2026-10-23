package com.lias.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    // Database configuration constants
    // Update the database name, username, and password according to your MySQL setup
    private static final String URL = "jdbc:mysql://localhost:3306/lias_db?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true";
    private static final String USER = "root";
    private static final String PASSWORD = "has"; 

    private static Connection connection = null;

    // Private constructor to prevent direct instantiation
    private DBConnection() {}

    /**
     * Retrieves a single shared database connection instance.
     * 
     * @return Connection object
     */
    public static synchronized Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                // Load the MySQL JDBC Driver
                Class.forName("com.mysql.cj.jdbc.Driver");
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
                System.out.println("Database connection established successfully!");
            }
        } catch (ClassNotFoundException e) {
            System.err.println("MySQL JDBC Driver not found. Include it in your WEB-INF/lib directory.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("Failed to connect to the database!");
            e.printStackTrace();
        }
        return connection;
    }

    /**
     * Optional utility method to close connection manually if needed, 
     * though usually, individual DAO methods close statements and ResultSets 
     * while keeping the shared connection active or managing it per transaction.
     */
    public static void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("Database connection closed.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}