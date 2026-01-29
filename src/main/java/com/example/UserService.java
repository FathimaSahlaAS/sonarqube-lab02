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

    // Getter methods
    public String getPassword() {
        return password;
    }

    public String getDbUrl() {
        return DB_URL;
    }

    public String getDbUser() {
        return DB_USER;
    }

    // Validation methods
    public boolean isValidUsername(String username) {
        if (username == null) {
            return false;
        }
        if (username.trim().isEmpty()) {
            return false;
        }
        return true;
    }

    public boolean isNullOrEmpty(String str) {
        return str == null || str.isEmpty();
    }

    public boolean isWhitespace(String str) {
        return str != null && str.trim().isEmpty();
    }

    public String sanitizeUsername(String username) {
        if (username == null) {
            return "";
        }
        return username.trim();
    }

    public int getUsernameLength(String username) {
        if (username == null) {
            return 0;
        }
        return username.length();
    }

    public boolean isUsernameTooLong(String username) {
        return getUsernameLength(username) > 255;
    }

    public boolean isUsernameTooShort(String username) {
        return getUsernameLength(username) < 1;
    }

    // Query building methods
    public String buildFindUserQuery() {
        return "SELECT id, name, email FROM users WHERE name = ?";
    }

    public String buildDeleteUserQuery() {
        return "DELETE FROM users WHERE name = ?";
    }

    public String buildQueryWithLimit(String baseQuery, int limit) {
        if (limit <= 0) {
            return baseQuery;
        }
        return baseQuery + " LIMIT " + limit;
    }

    // Logging helper methods
    public void logInfo(String message) {
        LOGGER.info(message);
    }

    public void logWarning(String message) {
        LOGGER.warning(message);
    }

    public void logError(String message) {
        LOGGER.severe(message);
    }

    // Validation with logging
    public boolean validateAndLogUsername(String username) {
        if (!isValidUsername(username)) {
            logWarning("Invalid username: " + username);
            return false;
        }
        if (isUsernameTooLong(username)) {
            logWarning("Username too long: " + username);
            return false;
        }
        logInfo("Username validated: " + username);
        return true;
    }

    public void findUser(String username) {
        if (!isValidUsername(username)) {
            logWarning("Invalid username provided for findUser: " + username);
            return;
        }

        String query = buildFindUserQuery();

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, password);
                PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, username);

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    String name = rs.getString("name");
                    if (name != null) {
                        logInfo("Found user: " + name);
                    } else {
                        logWarning("User name was null in result set");
                    }
                }
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Database error while finding user: " + username, e);
        }
    }

    public void deleteUser(String username) {
        if (!isValidUsername(username)) {
            logWarning("Invalid username provided for deleteUser: " + username);
            return;
        }

        String query = buildDeleteUserQuery();

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, password);
                PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, username);
            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected > 0) {
                logInfo("Successfully deleted user: " + username);
            } else {
                logWarning("No rows affected when deleting user: " + username);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Database error while deleting user: " + username, e);
        }
    }
}