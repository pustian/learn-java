package tian.pusen.swing.jdk7._32_checkbox;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CheckBoxFrame extends JFrame {
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame frame =  new CheckBoxFrame();
                frame.setVisible(true);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setSize(300, 200);
            }
        });
    }

    final JTextArea textArea = new JTextArea();
    final JCheckBox bold = new JCheckBox("bold");
    final JCheckBox itallic = new JCheckBox("itallic");

    private CheckBoxFrame() {
        add(textArea, BorderLayout.CENTER);

        JPanel panel = new JPanel();
        panel.add(bold);
        panel.add(itallic);
        add(panel, BorderLayout.SOUTH);

        ActionListener checkboxAction = new CheckboxAction();
        itallic.addActionListener(checkboxAction);
        bold.addActionListener(checkboxAction);
    }

    private class CheckboxAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String content  = "bold " + (bold.isSelected()?"selected":"not Selected")
                    + " itallic " + (itallic.isSelected()?"selected":"not Selected")
                    + "\n";
            System.out.println("content = " + content);
//            textArea.append( content );
            textArea.setText(content);
            System.out.println(textArea.getText());
        }
    }
}