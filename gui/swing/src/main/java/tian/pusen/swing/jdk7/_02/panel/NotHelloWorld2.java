package tian.pusen.swing.jdk7._02.panel;

import javax.swing.*;
import java.awt.*;

public class NotHelloWorld2 {
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                JFrame frame = new NotHelloWorldFrame();
                frame.setTitle("NotHelloWorld");
                frame.setVisible(true);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            }
        });
    }
}

class NotHelloWorldFrame extends JFrame {
    public NotHelloWorldFrame() {
        add(new NotHelloWorldPanel());
        pack();
    }
}

class NotHelloWorldPanel extends JPanel {
    private static final int MESSAGE_X = 75;
    private static final int MESSAGE_Y = 100;

    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        graphics.drawString("Not a hello world program!", MESSAGE_X, MESSAGE_Y);
    }

    private static final int DEFAULT_WIDTH = 800;
    private static final int DEFAULT_HEIGHT = 600;

    public Dimension getPreferredSize() {
        return new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT);
    }
}