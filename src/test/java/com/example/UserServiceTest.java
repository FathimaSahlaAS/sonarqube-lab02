package com.example;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

class UserServiceTest {

    private UserService service;

    @BeforeEach
    void setUp() {
        service = new UserService();
    }

    // Test getters
    @Test
    void testGetPassword() {
        assertEquals("secret", service.getPassword());
    }

    @Test
    void testGetDbUrl() {
        assertEquals("jdbc:mysql://localhost/db", service.getDbUrl());
    }

    @Test
    void testGetDbUser() {
        assertEquals("root", service.getDbUser());
    }

    // Test isValidUsername
    @Test
    void testIsValidUsernameWithValidUsername() {
        assertTrue(service.isValidUsername("admin"));
    }

    @Test
    void testIsValidUsernameWithNull() {
        assertFalse(service.isValidUsername(null));
    }

    @Test
    void testIsValidUsernameWithEmpty() {
        assertFalse(service.isValidUsername(""));
    }

    @Test
    void testIsValidUsernameWithWhitespace() {
        assertFalse(service.isValidUsername("   "));
    }

    @Test
    void testIsValidUsernameWithTab() {
        assertFalse(service.isValidUsername("\t"));
    }

    @Test
    void testIsValidUsernameWithNewline() {
        assertFalse(service.isValidUsername("\n"));
    }

    @Test
    void testIsValidUsernameWithSingleChar() {
        assertTrue(service.isValidUsername("a"));
    }

    // Test isNullOrEmpty
    @Test
    void testIsNullOrEmptyWithNull() {
        assertTrue(service.isNullOrEmpty(null));
    }

    @Test
    void testIsNullOrEmptyWithEmpty() {
        assertTrue(service.isNullOrEmpty(""));
    }

    @Test
    void testIsNullOrEmptyWithWhitespace() {
        assertFalse(service.isNullOrEmpty("   "));
    }

    @Test
    void testIsNullOrEmptyWithText() {
        assertFalse(service.isNullOrEmpty("admin"));
    }

    // Test isWhitespace
    @Test
    void testIsWhitespaceWithNull() {
        assertFalse(service.isWhitespace(null));
    }

    @Test
    void testIsWhitespaceWithEmpty() {
        assertTrue(service.isWhitespace("")); // Empty string is considered whitespace
    }

    @Test
    void testIsWhitespaceWithSpaces() {
        assertTrue(service.isWhitespace("   "));
    }

    @Test
    void testIsWhitespaceWithText() {
        assertFalse(service.isWhitespace("admin"));
    }

    @Test
    void testIsWhitespaceWithTab() {
        assertTrue(service.isWhitespace("\t"));
    }

    // Test sanitizeUsername
    @Test
    void testSanitizeUsernameWithNull() {
        assertEquals("", service.sanitizeUsername(null));
    }

    @Test
    void testSanitizeUsernameWithSpaces() {
        assertEquals("admin", service.sanitizeUsername("  admin  "));
    }

    @Test
    void testSanitizeUsernameWithNoSpaces() {
        assertEquals("admin", service.sanitizeUsername("admin"));
    }

    @Test
    void testSanitizeUsernameWithEmpty() {
        assertEquals("", service.sanitizeUsername(""));
    }

    // Test getUsernameLength
    @Test
    void testGetUsernameLengthWithNull() {
        assertEquals(0, service.getUsernameLength(null));
    }

    @Test
    void testGetUsernameLengthWithEmpty() {
        assertEquals(0, service.getUsernameLength(""));
    }

    @Test
    void testGetUsernameLengthWithText() {
        assertEquals(5, service.getUsernameLength("admin"));
    }

    @Test
    void testGetUsernameLengthWithSpaces() {
        assertEquals(9, service.getUsernameLength("  admin  ")); // 2 + 5 + 2 = 9
    }

    // Test isUsernameTooLong
    @Test
    void testIsUsernameTooLongWithShortName() {
        assertFalse(service.isUsernameTooLong("admin"));
    }

    @Test
    void testIsUsernameTooLongWithLongName() {
        String longName = "a".repeat(256);
        assertTrue(service.isUsernameTooLong(longName));
    }

    @Test
    void testIsUsernameTooLongWithExactLimit() {
        String exactName = "a".repeat(255);
        assertFalse(service.isUsernameTooLong(exactName));
    }

    @Test
    void testIsUsernameTooLongWithNull() {
        assertFalse(service.isUsernameTooLong(null));
    }

    // Test isUsernameTooShort
    @Test
    void testIsUsernameTooShortWithEmpty() {
        assertTrue(service.isUsernameTooShort(""));
    }

    @Test
    void testIsUsernameTooShortWithNull() {
        assertTrue(service.isUsernameTooShort(null));
    }

    @Test
    void testIsUsernameTooShortWithValidName() {
        assertFalse(service.isUsernameTooShort("admin"));
    }

    @Test
    void testIsUsernameTooShortWithSingleChar() {
        assertFalse(service.isUsernameTooShort("a"));
    }

    // Test query building
    @Test
    void testBuildFindUserQuery() {
        assertEquals("SELECT id, name, email FROM users WHERE name = ?",
                service.buildFindUserQuery());
    }

    @Test
    void testBuildDeleteUserQuery() {
        assertEquals("DELETE FROM users WHERE name = ?",
                service.buildDeleteUserQuery());
    }

    @Test
    void testBuildQueryWithLimitZero() {
        String query = "SELECT * FROM users";
        assertEquals(query, service.buildQueryWithLimit(query, 0));
    }

    @Test
    void testBuildQueryWithLimitPositive() {
        String query = "SELECT * FROM users";
        assertEquals(query + " LIMIT 10", service.buildQueryWithLimit(query, 10));
    }

    @Test
    void testBuildQueryWithLimitNegative() {
        String query = "SELECT * FROM users";
        assertEquals(query, service.buildQueryWithLimit(query, -1));
    }

    // Test logging methods
    @Test
    void testLogInfo() {
        assertDoesNotThrow(() -> service.logInfo("Test info message"));
    }

    @Test
    void testLogWarning() {
        assertDoesNotThrow(() -> service.logWarning("Test warning message"));
    }

    @Test
    void testLogError() {
        assertDoesNotThrow(() -> service.logError("Test error message"));
    }

    // Test validateAndLogUsername
    @Test
    void testValidateAndLogUsernameWithValid() {
        assertTrue(service.validateAndLogUsername("admin"));
    }

    @Test
    void testValidateAndLogUsernameWithNull() {
        assertFalse(service.validateAndLogUsername(null));
    }

    @Test
    void testValidateAndLogUsernameWithEmpty() {
        assertFalse(service.validateAndLogUsername(""));
    }

    @Test
    void testValidateAndLogUsernameWithTooLong() {
        String longName = "a".repeat(256);
        assertFalse(service.validateAndLogUsername(longName));
    }

    @Test
    void testValidateAndLogUsernameWithWhitespace() {
        assertFalse(service.validateAndLogUsername("   "));
    }

    // Test user service creation
    @Test
    void testUserServiceCreation() {
        assertNotNull(service);
    }

    // Test findUser
    @Test
    void testFindUserWithValidUsername() {
        assertDoesNotThrow(() -> service.findUser("admin"));
    }

    @Test
    void testFindUserWithNull() {
        assertDoesNotThrow(() -> service.findUser(null));
    }

    @Test
    void testFindUserWithEmpty() {
        assertDoesNotThrow(() -> service.findUser(""));
    }

    // Test deleteUser
    @Test
    void testDeleteUserWithValidUsername() {
        assertDoesNotThrow(() -> service.deleteUser("admin"));
    }

    @Test
    void testDeleteUserWithNull() {
        assertDoesNotThrow(() -> service.deleteUser(null));
    }

    @Test
    void testDeleteUserWithEmpty() {
        assertDoesNotThrow(() -> service.deleteUser(""));
    }
}