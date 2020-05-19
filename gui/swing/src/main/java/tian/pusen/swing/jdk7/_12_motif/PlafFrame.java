package tian.pusen.swing.jdk7._12_motif;

import tian.pusen.swing.jdk7._11_eventHandler.EventHandlerTest;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PlafFrame extends JFrame{
    public static void main(String[] args) {
        JFrame frame = new PlafFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    private JPanel buttonPanel;
    public PlafFrame() {
        buttonPanel = new MessagePanel();
        UIManager.LookAndFeelInfo[] infos = UIManager.getInstalledLookAndFeels();
        for(UIManager.LookAndFeelInfo info: infos) {
            System.out.println("info Name=" + info.getName() + " class=" + info.getClassName());
            makeButton(info.getName(), info.getClassName());
        }
        add(buttonPanel);
        pack();
    }

    void makeButton(String name, final String platName) {
        JButton button = new JButton(name);
        buttonPanel.add(button);

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    UIManager.setLookAndFeel(platName);
                    SwingUtilities.updateComponentTreeUI(PlafFrame.this);
                    pack();
                } catch (ClassNotFoundException ex) {
                    ex.printStackTrace();
                } catch (InstantiationException ex) {
                    ex.printStackTrace();
                } catch (IllegalAccessException ex) {
                    ex.printStackTrace();
                } catch (UnsupportedLookAndFeelException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }
}
class MessagePanel extends JPanel {
    private static final int MESSAGE_X = 75;
    private static final int MESSAGE_Y = 100;

    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        graphics.drawString("Not a hello world program!", MESSAGE_X, MESSAGE_Y);
    }

    private static final int DEFAULT_WIDTH = 300;
    private static final int DEFAULT_HEIGHT = 200;

    public Dimension getPreferredSize() {
        return new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT);
    }
}
