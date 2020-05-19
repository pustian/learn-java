package tian.pusen.swing.jdk7._13_windowsEvent;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Random;

public class ButtonFrame extends JFrame {
    public static void main(String[] args) {
        final JFrame frame = new ButtonFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        // 点击 exit button 和左上角windows关闭调用的逻辑不同
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent e) {
                System.out.println("Called when opened");
            }

            @Override
            public void windowClosing(WindowEvent e) {
                System.out.println("Called when closing");
            }

            @Override
            public void windowClosed(WindowEvent e) {
                System.out.println("Called when closed");
            }

//            @Override
//            public void windowIconified(WindowEvent e) {
//                super.windowIconified(e);
//            }
//
//            @Override
//            public void windowDeiconified(WindowEvent e) {
//                super.windowDeiconified(e);
//            }
//
//            @Override
//            public void windowActivated(WindowEvent e) {
//                super.windowActivated(e);
//            }
//
//            @Override
//            public void windowDeactivated(WindowEvent e) {
//                super.windowDeactivated(e);
//            }
//
//            @Override
//            public void windowStateChanged(WindowEvent e) {
//                super.windowStateChanged(e);
//            }
//
//            @Override
//            public void windowGainedFocus(WindowEvent e) {
//                super.windowGainedFocus(e);
//            }
//
//            @Override
//            public void windowLostFocus(WindowEvent e) {
//                super.windowLostFocus(e);
//            }
        });
    }

    private JPanel buttonPanel;
    private static final int WIDTH = 300;
    private static final int HEIGHT = 200;

    public ButtonFrame() {
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
