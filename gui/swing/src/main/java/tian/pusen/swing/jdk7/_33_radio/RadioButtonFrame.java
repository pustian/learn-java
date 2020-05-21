package tian.pusen.swing.jdk7._33_radio;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RadioButtonFrame extends JFrame {
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame frame =  new RadioButtonFrame();
                frame.setVisible(true);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//                frame.setSize(300, 200);
            }
        });
    }

    private JPanel buttonPanel;
    private ButtonGroup buttonGroup;
    private JLabel label;
    private static final int DEFAULT_SIZE = 36;

    private RadioButtonFrame() {
        label = new JLabel("The quick brown fox jumps over the lazy dop.");
        label.setFont(new Font("Serif", Font.PLAIN, DEFAULT_SIZE));
        add(label, BorderLayout.CENTER);

        buttonPanel = new JPanel();
        buttonGroup = new ButtonGroup();
        addRadioBuuton("small", 8);
        addRadioBuuton("medium", 12);
        addRadioBuuton("large", 18);
        addRadioBuuton("extra", 36);

        add(buttonPanel, BorderLayout.SOUTH);
        pack();
    }
    private void addRadioBuuton(String name, final int size) {
        boolean selected  = size == DEFAULT_SIZE;
        JRadioButton button = new JRadioButton(name, selected);
        buttonGroup.add(button);
        buttonPanel.add(button);

        ActionListener listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                label.setFont(new Font("Serif", Font.PLAIN, size));
            }
        };

        button.addActionListener(listener);
    }
}
