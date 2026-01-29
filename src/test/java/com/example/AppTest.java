package com.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

 class AppTest {
    
    @Test
     void testMainMethodDoesNotThrowException() {
        // Test that main method can be called
        assertDoesNotThrow(() -> {
            App.main(new String[]{});
        });
    }
}