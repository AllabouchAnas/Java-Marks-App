package jma.app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private static final String JDBC_NAME = "jma";
    private static final String JDBC_URL = "jdbc:mysql://localhost/" + JDBC_NAME;
    private static final String USER = "root";
    private static final String PASSWORD = "";

    public Connection getConnection() {
        Connection connection = null;
        try {
            // Load the JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Create a connection
            connection = DriverManager.getConnection(JDBC_URL, USER, PASSWORD);

            if (connection != null) {
                System.out.println("Connected to the database!");
            }

        } catch (ClassNotFoundException e) {
            // Handle the case where the JDBC driver is not found
            e.printStackTrace();
        } catch (SQLException e) {
            // Handle any SQL-related errors
            e.printStackTrace();
        }
        return connection;
    }

    public void closeConnection(Connection connection) {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("Connection closed.");
            }
        } catch (SQLException e) {
            // Handle any SQL-related errors when closing the connection
            e.printStackTrace();
        }
    }

}
