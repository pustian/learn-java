package tian.pusen.swing.jdk7._11_eventHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.EventHandler;
import java.util.Random;

public class EventHandlerTest extends JFrame {
    public static void main(String[] args) {
        JFrame frame = new EventHandlerTest();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    private static JPanel buttonPanel;
    private static final int WIDTH = 300;
    private static final int HEIGHT = 200;

    public EventHandlerTest() {
        setTitle("EventHandlerFrame");
        setSize(WIDTH, HEIGHT);

        JButton colorButton = new JButton("color");
        JButton exitButton = new JButton("exit");

        buttonPanel = new JPanel();
        buttonPanel.add(colorButton);
        buttonPanel.add(exitButton);

        add(buttonPanel);
//        colorButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                change();
//            }
//        });
        colorButton.addActionListener(EventHandler.create(ActionListener.class,
                this, "change"));
        colorButton.addActionListener(EventHandler.create(ActionListener.class,
                EventHandlerTest.this, "change"));

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
//        colorButton.addActionListener(EventHandler.create(ActionListener.class,
//                this, "exit", 7));
    }

    public static void change() {
        buttonPanel.setBackground(
                new Color(new Random().nextInt(255),
                        new Random().nextInt(255),
                        new Random().nextInt(255)));
    }

}
