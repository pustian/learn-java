package tian.pusen.swing.jdk7._01;

import javax.swing.*;
import java.awt.*;

public class SimpleFrameTest {
    public static void main(String[] args) {
        EventQueue.invokeLater(
                new Runnable() {
                    public void run() {
                        SimpleFrame frame = new SimpleFrame();
                        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                        frame.setVisible(true);
                    }
                }
        );
    }
}
class SimpleFrame extends JFrame {
    private static final int WIDTH = 600;
    private static final int HEIGHT = 400;
    SimpleFrame() {
        setSize(WIDTH, HEIGHT);
    }
}