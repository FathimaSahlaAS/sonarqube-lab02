package com.example;

import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.junit.jupiter.api.Assertions.*;

class AppTest {

    @Test
    void testMainMethodDoesNotThrowException() {
        // Test that main method can be called
        assertDoesNotThrow(() -> {
            App.main(new String[] {});
        });
    }

    @Test
    void testMainMethodExecutesSuccessfully() {
        // Capture output to verify main method runs
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));

        try {
            App.main(new String[] {});
            String output = outContent.toString();
            assertNotNull(output);
        } finally {
            System.setOut(originalOut);
        }
    }

    @Test
    void testAppCanBeInstantiated() {
        // Test that App class can be instantiated
        assertDoesNotThrow(App::new);
    }
}