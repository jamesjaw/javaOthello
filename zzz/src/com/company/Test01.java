package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Test01 extends JFrame {
    private JTextField t1,t2;
    private JLabel label, label1, label2;
    public JButton b3;

    public Test01() {
        this.setVisible(true);
        //this.setBackground(Color.red);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(500, 500);
        super.setTitle("hello");
        super.setLayout(new FlowLayout());
        label = new JLabel("+");
        label1 = new JLabel("=");
        label2 = new JLabel("ans");
        t1 = new JTextField(4);
        t2 = new JTextField(4);
        b3 = new JButton("計算");

        add(t1);
        add(label);
        add(t2);
        add(label1);
        add(b3);
        add(label2);
        b3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                add_num();
            }
        });

    }

    private void add_num() {
        String s1 = t1.getText();
        String s2 = t2.getText();
        int a = Integer.parseInt(s1);
        int b = Integer.parseInt(s2);
        int ans = a + b;
        label2.setText("" + ans);
    }

    public static void main(String[] args) {
        new Test01();

    }
}
