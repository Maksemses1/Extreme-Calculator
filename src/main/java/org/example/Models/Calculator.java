package org.example.Models;

import org.example.Logging;

import java.util.ArrayList;
import java.util.List;

public class Calculator {
    private final Logging logging = new Logging();
    // main calculation method
    public double calculate(String expression) {
        expression += " ";
        ArrayList<String> array = new ArrayList<>(parseToArray(expression));
        ArrayList<String> ONP = new ArrayList<>(toONP(array));
        double result = calculateResult(ONP);
        logging.log(ONP);
        logging.log(result);
        return result;
    }
    //parsing from string to array of elements
    List<String> parseToArray(String expression) {
        ArrayList<String> array = new ArrayList<>();
        StringBuilder temp = new StringBuilder();
        for (int i = 0; i < expression.length(); i++) {
            char currentChar = expression.charAt(i);
            if (Character.isWhitespace(currentChar)) {
                continue;
            }
            if (Character.isDigit(currentChar)) {
                temp.append(currentChar);
            } else {
                if (!temp.isEmpty()) {
                    array.add(temp.toString());
                    temp = new StringBuilder();
                }
                array.add(String.valueOf(currentChar));
            }
        }
        if (!temp.isEmpty()) {
            array.add(temp.toString());
        }
        return array;
    }

    // Transformation into RNP
    ArrayList<String> toONP(ArrayList<String> array) {
        ArrayList<String> ONP = new ArrayList<>();
        ArrayList<String> stos = new ArrayList<>();

        for (String s : array) {
            if (isInteger(s)) {
                ONP.add(s);
            } else if (s.equals("(")) {
                stos.add(s);
            } else if (s.equals(")")) {
                while (!stos.isEmpty() && !stos.getLast().equals("(")) {
                    ONP.add(stos.removeLast());
                }
                stos.removeLast();
            } else if (isOperator(s)) {
                while (!stos.isEmpty() && priority(s) <= priority(stos.getLast())) {
                    ONP.add(stos.removeLast());
                }
                stos.add(s);
            }
        }

        while (!stos.isEmpty()) {
            ONP.add(stos.removeLast());
        }

        return ONP;
    }
    // calculate from RNP to normal form
    double calculateResult(ArrayList<String> ONP) {
        ArrayList<Double> stos = new ArrayList<>();
        double a;
        double b;
        for (String s : ONP) {
            if (isInteger(s)) {
                stos.add(Double.parseDouble(s));
            } else {
                b = stos.removeLast();
                a = stos.removeLast();
                stos.add(calc(a, b, s));
            }
        }
        return stos.getLast();
    }
    // utils for calculating
    double calc(double a, double b, String operator){
        return switch (operator) {
            case "+" -> a + b;
            case "-" -> a - b;
            case "*" -> a * b;
            case "/" -> a / b;
            default -> -1;
        };
    }
    // check if it's a number or not
    boolean isInteger(String string) {
        return string.matches("-?\\d+");
    }
    //operator priority determination
    int priority(String expression) {
        return switch (expression) {
            case "(" -> 0;
            case "+", "-" -> 1;
            case "*", "/" -> 2;
            case "^" -> 3;
            default -> -1;
        };
    }
    //check if it's an operator or not
    boolean isOperator(String string) {
        return string.equals("+") || string.equals("-") || string.equals("*") || string.equals("/");
    }
}
