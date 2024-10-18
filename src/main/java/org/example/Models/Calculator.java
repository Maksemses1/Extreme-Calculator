package org.example.Models;

import java.util.ArrayList;
import java.util.List;

public class Calculator {
    // main calculation method
    public double calculate(String expression) {
        expression += " ";
        ArrayList<String> array = new ArrayList<>(parseToArray(expression));
        ArrayList<String> ONP = new ArrayList<>(toONP(array));
        String log = "";
        for (String s : ONP) {
            log += s + " ";
        }
        System.out.println(log);
        double result = calculateResult(ONP);
        return result;
    }
    //parsing from string to array of elements
    List<String> parseToArray(String expression) {
        ArrayList<String> array = new ArrayList<>();
        String temp = "";
        for (int i = 0; i < expression.length(); i++) {
            char currentChar = expression.charAt(i);
            if (Character.isWhitespace(currentChar)) {
                continue;
            }

            if (Character.isDigit(currentChar)) {
                temp += currentChar;
            } else {
                if (!temp.isEmpty()) {
                    array.add(temp);
                    temp = "";
                }
                array.add(String.valueOf(currentChar));
            }
        }
        if (!temp.isEmpty()) {
            array.add(temp);
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
                while (!stos.isEmpty() && !stos.get(stos.size() - 1).equals("(")) {
                    ONP.add(stos.remove(stos.size() - 1));
                }
                stos.remove(stos.size() - 1);
            } else if (isOperator(s)) {
                while (!stos.isEmpty() && prioryty(s) <= prioryty(stos.get(stos.size() - 1))) {
                    ONP.add(stos.remove(stos.size() - 1));
                }
                stos.add(s);
            }
        }

        while (!stos.isEmpty()) {
            ONP.add(stos.remove(stos.size() - 1));
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
                b = stos.remove(stos.size() - 1);
                a = stos.remove(stos.size() - 1);
                stos.add(calc(a, b, s));
            }
        }
        return stos.getLast();
    }
    // utils for calculating
    double calc(double a, double b, String operator){
        switch (operator) {
            case "+":
                return a + b;
            case "-":
                return a - b;
            case "*":
                return a * b;
            case "/":
                return a / b;
        }
        return -1;
    }
    // check if it's a number or not
    boolean isInteger(String string) {
        return string.matches("-?\\d+");
    }
    //operator priority determination
    int prioryty(String expression) {
        switch (expression) {
            case "(":
                return 0;
            case "+":
            case "-":
                return 1;
            case "*":
            case "/":
                return 2;
            case "^":
                return 3;
            default:
                return -1;
        }
    }
    //check if it's a operator or not
    boolean isOperator(String string) {
        return string.equals("+") || string.equals("-") || string.equals("*") || string.equals("/");
    }
}
