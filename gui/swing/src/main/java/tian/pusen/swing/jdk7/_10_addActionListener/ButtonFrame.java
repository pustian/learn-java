package tian.pusen.swing.jdk7._10_addActionListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonFrame extends JFrame {
    public static void main(String[] args) {
        JFrame frame = new ButtonFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    private JPanel buttonPanel;
    private static final int WIDTH = 300;
    private static final int HEIGHT = 200;

    public ButtonFrame() {
        setSize(WIDTH, HEIGHT);

        JButton yelloButton = new JButton("yello");
        JButton blueButton = new JButton("blue");
        JButton redButton = new JButton("red");

        buttonPanel = new JPanel();
        buttonPanel.add(yelloButton);
        buttonPanel.add(blueButton);
        buttonPanel.add(redButton);

        add(buttonPanel);

        // 创建事件
        ColorAction yellowAction = new ColorAction(Color.YELLOW);
        ColorAction blueAction = new ColorAction(Color.BLUE);
        ColorAction redAction = new ColorAction(Color.RED);
        // 注册
        yelloButton.addActionListener(yellowAction);
        blueButton.addActionListener(blueAction);
        redButton.addActionListener(redAction);
    }

    private class ColorAction implements ActionListener {
        private Color backgroundColor;

        public ColorAction(Color color) {
            backgroundColor = color;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            buttonPanel.setBackground(backgroundColor);
        }
    }
}
