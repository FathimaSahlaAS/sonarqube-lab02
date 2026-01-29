package com.example;

public class Calculator {

    public int calculate(int a, int b, String operation) {
        if (operation == null) {
            throw new IllegalArgumentException("Operation cannot be null");
        }

        switch (operation) {
            case "add":
                return add(a, b);
            case "sub":
                return subtract(a, b);
            case "mul":
                return multiply(a, b);
            case "div":
                return divide(a, b);
            case "mod":
                return modulo(a, b);
            case "pow":
                return power(a, b);
            default:
                throw new IllegalArgumentException("Unknown operation: " + operation);
        }
    }

    private int add(int a, int b) {
        return a + b;
    }

    private int subtract(int a, int b) {
        return a - b;
    }

    private int multiply(int a, int b) {
        return a * b;
    }

    private int divide(int a, int b) {
        if (b == 0) {
            throw new ArithmeticException("Division by zero");
        }
        return a / b;
    }

    private int modulo(int a, int b) {
        if (b == 0) {
            throw new ArithmeticException("Modulo by zero");
        }
        return a % b;
    }

    private int power(int a, int b) {
        return (int) Math.pow(a, b);
    }
}