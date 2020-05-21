package tian.pusen.swing.jdk7._31_text;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//JLabel
//JTextField
//JPasswordField
//JTextArea
public class TextComponentFrame extends JFrame {
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame frame = new TextComponentFrame();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
            }
        });
    }

    static final int TEXTAREA_ROWS = 8;
    static final int TEXTAREA_COLUMNS = 20;

    public TextComponentFrame() {
//        setLayout(new BorderLayout());
        final JTextField textField = new JTextField();
        final JPasswordField passwordField = new JPasswordField();

        JPanel northPanel = new JPanel();
        northPanel.setLayout(new GridLayout(2, 2));
        northPanel.add(new JLabel("User Name:"));
//        northPanel.add(new JLabel("User Name:", SwingConstants.RIGHT));
        northPanel.add(textField);
//        northPanel.add(new JLabel("Password:"));
        northPanel.add(new JLabel("Password :", SwingConstants.RIGHT));
        northPanel.add(passwordField);
        add(northPanel, BorderLayout.NORTH);

        final JTextArea textArea = new JTextArea(TEXTAREA_ROWS, TEXTAREA_COLUMNS);
        JScrollPane scrollPane = new JScrollPane(textArea);
        add(scrollPane, BorderLayout.CENTER);

        JPanel southPanel = new JPanel();
        JButton insertButton = new JButton("Insert");
        southPanel.add(insertButton);
        insertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textArea.append("User name:" + textField.getText()
                        + "password:" + new String(passwordField.getPassword()) + "\n");
            }
        });
        add(southPanel, BorderLayout.SOUTH);
        pack();
    }
}
