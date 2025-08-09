package org.example.Controllers;

import org.example.Models.Calculator;

public class CalculatorController {
    private final Calculator calculator = new Calculator();

    public String calculate(String expression) {
        double result = calculator.calculate(expression);
        if (result % 1 == 0) {
            return  String.format("%.0f", result);
        }
        return String.format("%.2f", result);
    }
}
