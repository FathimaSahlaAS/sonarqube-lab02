package com.example;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {

    private Calculator calc;

    @BeforeEach
    void setUp() {
        calc = new Calculator();
    }

    @Test
    void testAdd() {
        assertEquals(15, calc.calculate(10, 5, "add"));
    }

    @Test
    void testAddWithNegativeNumbers() {
        assertEquals(-15, calc.calculate(-10, -5, "add"));
    }

    @Test
    void testAddWithZero() {
        assertEquals(10, calc.calculate(10, 0, "add"));
    }

    @Test
    void testSubtract() {
        assertEquals(5, calc.calculate(10, 5, "sub"));
    }

    @Test
    void testSubtractNegativeResult() {
        assertEquals(-5, calc.calculate(5, 10, "sub"));
    }

    @Test
    void testSubtractWithZero() {
        assertEquals(10, calc.calculate(10, 0, "sub"));
    }

    @Test
    void testMultiply() {
        assertEquals(50, calc.calculate(10, 5, "mul"));
    }

    @Test
    void testMultiplyByZero() {
        assertEquals(0, calc.calculate(10, 0, "mul"));
    }

    @Test
    void testMultiplyNegativeNumbers() {
        assertEquals(50, calc.calculate(-10, -5, "mul"));
    }

    @Test
    void testDivide() {
        assertEquals(2, calc.calculate(10, 5, "div"));
    }

    @Test
    void testDivideByZero() {
        assertThrows(ArithmeticException.class, () -> {
            calc.calculate(10, 0, "div");
        });
    }

    @Test
    void testDivideNegativeNumbers() {
        assertEquals(2, calc.calculate(-10, -5, "div"));
    }

    @Test
    void testDivideResultsInDecimal() {
        assertEquals(3, calc.calculate(10, 3, "div")); // Integer division
    }

    @Test
    void testModulo() {
        assertEquals(0, calc.calculate(10, 5, "mod"));
    }

    @Test
    void testModuloWithRemainder() {
        assertEquals(1, calc.calculate(10, 3, "mod"));
    }

    @Test
    void testModuloWithZero() {
        assertThrows(ArithmeticException.class, () -> {
            calc.calculate(10, 0, "mod");
        });
    }

    @Test
    void testPower() {
        assertEquals(100000, calc.calculate(10, 5, "pow"));
    }

    @Test
    void testPowerWithZeroExponent() {
        assertEquals(1, calc.calculate(10, 0, "pow"));
    }

    @Test
    void testPowerWithNegativeBase() {
        assertEquals(-8, calc.calculate(-2, 3, "pow"));
    }

    @Test
    void testPowerOfOne() {
        assertEquals(10, calc.calculate(10, 1, "pow"));
    }

    @Test
    void testInvalidOperation() {
        assertThrows(IllegalArgumentException.class, () -> {
            calc.calculate(10, 5, "invalid");
        });
    }

    @Test
    void testNullOperation() {
        assertThrows(IllegalArgumentException.class, () -> {
            calc.calculate(10, 5, null);
        });
    }

    @Test
    void testEmptyStringOperation() {
        assertThrows(IllegalArgumentException.class, () -> {
            calc.calculate(10, 5, "");
        });
    }

    @Test
    void testCasesensitivity() {
        // Test if operations are case-sensitive
        assertThrows(IllegalArgumentException.class, () -> {
            calc.calculate(10, 5, "ADD");
        });
    }

    @Test
    void testLargeNumbers() {
        assertEquals(2000000, calc.calculate(1000000, 2, "mul"));
    }

    @Test
    void testCalculatorInstantiation() {
        assertNotNull(calc);
    }
}