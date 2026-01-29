package main.java.com.example;

public class Calculator {

    public int calculate(int a, int b, String operation) {
        switch (operation) {
            case "add":
                return a + b;
            case "sub":
                return a - b;
            case "mul":
                return a * b;
            case "div":
                return (b != 0) ? a / b : 0;
            case "mod":
                return a % b;
            case "pow":
                return (int) Math.pow(a, b);
            default:
                return 0;
        }
    }

    // Code Duplication (students must remove)
    public int addNumbers(int x, int y) {
        return x + y;
    }

    public int sumValues(int a, int b) {
        return a + b;
    }

}
