package org.example.Views;

import org.example.Controllers.CalculatorController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalculatorGUI extends JFrame {
    JTextField textField;
    JLabel resultLabel;
    private CalculatorController calculatorController = new CalculatorController();


    public CalculatorGUI() {
        setTitle("Calculator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setBounds(800, 300, 450, 300);
        setLayout(new GridBagLayout());
        initMenu();
        setVisible(true);
    }
    private void initMenu() {
        resultLabel = new JLabel("Result");
        textField = new JTextField();
        JButton Button = new JButton("submit");

        GridBagConstraints constraints = new GridBagConstraints();

        textField.setPreferredSize(new Dimension(300, 40));
        textField.setFont(new Font("", Font.PLAIN, 30));
        add(textField, constraints);

        constraints.insets = new Insets(100, -300, 5, 5);
        Button.addActionListener(new ButtonClickListener());
        add(Button, constraints);

        constraints.insets = new Insets(-200, -300, 0, 0);
        resultLabel.setFont(new Font("", Font.PLAIN, 25));
        add(resultLabel, constraints);

    }
    private class ButtonClickListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String expression = textField.getText();
            String result = calculatorController.calculate(expression);
            System.out.println(result);
            resultLabel.setText(result);
        }
    }
}
