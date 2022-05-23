package com.enn3developer;

import javax.swing.*;

public class App {
    JPanel contentPane;
    private JTextField textField1;
    private JTextField textField2;
    private JButton resultButton;
    private JLabel resultLabel;
    private JTextField textField3;
    private JButton resultButton1;
    private JLabel rpnResult;

    public App() {
        resultButton.addActionListener(e -> {
            if (!textField1.getText().isEmpty() && !textField2.getText().isEmpty()) {
                try {
                    double addend_1 = Double.parseDouble(textField1.getText());
                    double addend_2 = Double.parseDouble(textField2.getText());
                    resultLabel.setText(String.valueOf(addend_1 + addend_2));
                } catch (NumberFormatException exception) {}
            }
        });
        resultButton1.addActionListener(e -> {
            if (!textField3.getText().isEmpty()) {
                String text = textField3.getText();
                if (ReversePolishNotation.isValid(text)) {
                    rpnResult.setText(String.valueOf(ReversePolishNotation.evaluate(text)));
                }
            }
        });
    }
}
