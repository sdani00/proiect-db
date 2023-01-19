package org.example;

import java.sql.*;

public class Database {
    private String url = "jdbc:mysql://localhost:3306/testdb?serverTimezone=UTC";
    private Connection connection;

    public Connection getConnection() throws SQLException {
        try {
            connection = DriverManager.getConnection(url, "root", "");
            return connection;
        }
        catch (SQLException exception) {
            System.out.println("SQL exception was thrown!");
            exception.printStackTrace();
        }

        return connection;
    }

    public Statement createStatement() throws SQLException {
        return getConnection().createStatement();
    }

    public PreparedStatement createPreparedStatement(String query) throws SQLException {
        return getConnection().prepareStatement(query);
    }

    public CallableStatement createCallableStatement(String query) throws SQLException {
        return getConnection().prepareCall(query);
    }
}