package com.enn3developer;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        ReversePolishNotation.setupOperations();
        JFrame frame = new JFrame("Calculator");
        frame.setContentPane(new App().contentPane);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}