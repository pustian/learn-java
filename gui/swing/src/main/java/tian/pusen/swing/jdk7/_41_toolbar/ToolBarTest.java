package tian.pusen.swing.jdk7._41_toolbar;

import javax.swing.*;
import java.awt.*;

public class ToolBarTest extends JFrame {
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame frame = new ToolBarTest();
                frame.setVisible(true);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//                frame.setSize(300, 200);
            }
        });
    }

    private static final int DEFAULT_WIDTH = 300;
    private static final int DEFAULT_HEIGHT = 200;

    public ToolBarTest() {
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);

    }
}
