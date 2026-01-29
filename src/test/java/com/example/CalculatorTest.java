package com.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CalculatorTest {
    
    @Test
    public void testAdd() {
        Calculator calc = new Calculator();
        assertEquals(15, calc.calculate(10, 5, "add"));
    }
    
    @Test
    public void testSubtract() {
        Calculator calc = new Calculator();
        assertEquals(5, calc.calculate(10, 5, "sub"));
    }
    
    @Test
    public void testMultiply() {
        Calculator calc = new Calculator();
        assertEquals(50, calc.calculate(10, 5, "mul"));
    }
    
    @Test
    public void testDivide() {
        Calculator calc = new Calculator();
        assertEquals(2, calc.calculate(10, 5, "div"));
    }
    
    @Test
    public void testDivideByZero() {
        Calculator calc = new Calculator();
        assertThrows(ArithmeticException.class, () -> {
            calc.calculate(10, 0, "div");
        });
    }
    
    @Test
    public void testModulo() {
        Calculator calc = new Calculator();
        assertEquals(0, calc.calculate(10, 5, "mod"));
    }
    
    @Test
    public void testPower() {
        Calculator calc = new Calculator();
        assertEquals(100000, calc.calculate(10, 5, "pow"));
    }
    
    @Test
    public void testInvalidOperation() {
        Calculator calc = new Calculator();
        assertThrows(IllegalArgumentException.class, () -> {
            calc.calculate(10, 5, "invalid");
        });
    }
    
    @Test
    public void testNullOperation() {
        Calculator calc = new Calculator();
        assertThrows(IllegalArgumentException.class, () -> {
            calc.calculate(10, 5, null);
        });
    }
}