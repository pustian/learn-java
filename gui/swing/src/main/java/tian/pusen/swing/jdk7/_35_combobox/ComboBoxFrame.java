package tian.pusen.swing.jdk7._35_combobox;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ComboBoxFrame extends JFrame {
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame frame = new ComboBoxFrame();
                frame.setVisible(true);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//                frame.setSize(300, 200);
            }
        });
    }

    private JComboBox<String> faceCombo;
    private JLabel label;
    private static final int DEFAULT_SIZE = 24;

    public ComboBoxFrame() {
        label = new JLabel("The quick brown fox jumps over the lazy dog,");
        label.setFont(new Font("Serif", Font.PLAIN, DEFAULT_SIZE) );
        add(label, BorderLayout.CENTER);

        faceCombo = new JComboBox<String>();
        faceCombo.addItem("Serif");
        faceCombo.addItem("SansSerif");
        faceCombo.addItem("Monospaced");
        faceCombo.addItem("Dialog");
        faceCombo.addItem("DialogInput");

        faceCombo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                label.setFont(new Font(faceCombo.getItemAt(faceCombo.getSelectedIndex()), Font.PLAIN, DEFAULT_SIZE));
            }
        });

        JPanel comboPanel  = new JPanel();
        comboPanel.add(faceCombo);
        add(comboPanel, BorderLayout.SOUTH);
        pack();
    }
}
