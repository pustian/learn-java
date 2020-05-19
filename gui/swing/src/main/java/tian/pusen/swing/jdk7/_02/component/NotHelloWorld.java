package tian.pusen.swing.jdk7._02.component;

import javax.swing.*;
import java.awt.*;

public class NotHelloWorld {
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
        init_v5();
//        setSize(600, 400);
    }

    private void init_v4() {
        Container contentPane = getContentPane();
        Component component = new NotHelloWorldComponent();
        contentPane.add(component);

        setSize(600, 400);
    }

    private void init_v5() {
        add(new NotHelloWorldComponent());
        pack();
    }
}

class NotHelloWorldComponent extends JComponent {
    private static final int MESSAGE_X = 75;
    private static final int MESSAGE_Y = 100;

    public void paintComponent(Graphics graphics) {
        graphics.drawString("Not a hello world program!", MESSAGE_X, MESSAGE_Y);
    }

    private static final int DEFAULT_WIDTH = 800;
    private static final int DEFAULT_HEIGHT = 600;
    public Dimension getPreferredSize() {
        return new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT);
    }
}


