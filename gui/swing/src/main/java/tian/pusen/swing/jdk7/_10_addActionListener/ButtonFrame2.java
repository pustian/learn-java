package tian.pusen.swing.jdk7._10_addActionListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Random;
import java.util.Scanner;

public class ButtonFrame2 extends JFrame {
    public static void main(String[] args) {
        final JFrame frame = new ButtonFrame2();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    private JPanel buttonPanel;
    private static final int WIDTH = 300;
    private static final int HEIGHT = 200;

    public ButtonFrame2() {
        setSize(WIDTH, HEIGHT);

        JButton colorButton = new JButton("color");
        JButton exitButton = new JButton("exit");

        buttonPanel = new JPanel();
        buttonPanel.add(colorButton);
        buttonPanel.add(exitButton);

        add(buttonPanel);

        colorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buttonPanel.setBackground(
                        new Color(new Random().nextInt(255),
                                new Random().nextInt(255),
                                new Random().nextInt(255) ) );
            }
        });

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }

}
