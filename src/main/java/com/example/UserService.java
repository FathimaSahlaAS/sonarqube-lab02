package com.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;
import java.util.logging.Level;

public class UserService {

    private static final Logger LOGGER = Logger.getLogger(UserService.class.getName());
    private static final String DB_URL = "jdbc:mysql://localhost/db";
    private static final String DB_USER = "root";
    private final String password;

    public UserService() {
        this.password = "secret";
    }

    public void findUser(String username) {
        // Fix Issue 1: Don't use SELECT *, specify columns instead
        String query = "SELECT id, name, email FROM users WHERE name = ?";

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, password);
                PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, username);

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    // Fix Issue 2: Use Logger instead of System.out
                    LOGGER.info(rs.getString("name"));
                }
            }
        } catch (SQLException e) {
            // Fix Issue 3: Already using specific SQLException
            LOGGER.log(Level.SEVERE, "Database error while finding user", e);
        }
    }

    public void deleteUser(String username) {
        String query = "DELETE FROM users WHERE name = ?";

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, password);
                PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, username);
            pstmt.execute();
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Database error while deleting user", e);
        }
    }
}