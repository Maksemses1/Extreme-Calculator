package org.example.Controllers;

import org.example.Models.Calculator;

public class CalculatorController {
    private final Calculator calculator = new Calculator();

    public String calculate(String expression) {
        double result = calculator.calculate(expression);
        return String.format("%.4f", result);
    }
}
