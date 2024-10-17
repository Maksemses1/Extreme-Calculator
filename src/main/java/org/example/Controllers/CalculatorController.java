package org.example.Controllers;

import org.example.Models.Calculator;

public class CalculatorController {
    private final Calculator calculator = new Calculator();

    public double calculate(String expression) {
        return calculator.calculate(expression);
    }
}
