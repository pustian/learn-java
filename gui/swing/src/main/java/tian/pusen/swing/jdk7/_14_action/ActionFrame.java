package tian.pusen.swing.jdk7._14_action;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ActionFrame extends JFrame {
    public static void main(String[] args) {
        JFrame frame = new ActionFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    private JPanel buttonPanel;
    private static final int WIDTH = 300;
    private static final int HEIGHT = 200;

    public ActionFrame() {
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
        ColorAction yellowAction = new ColorAction("yellow", Color.YELLOW);
        ColorAction blueAction = new ColorAction("blue", Color.BLUE);
        ColorAction redAction = new ColorAction("red", Color.RED);

        // Ctrl + Y B R  键盘和 names
        InputMap inputMap = buttonPanel.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
        inputMap.put(KeyStroke.getKeyStroke("ctrl Y"), "panel.yellow");
        inputMap.put(KeyStroke.getKeyStroke("ctrl B"), "panel.blue");
        inputMap.put(KeyStroke.getKeyStroke("ctrl R"), "panel.red");
        // 关联names 和actions
        ActionMap actionMap = buttonPanel.getActionMap();
        actionMap.put("panel.yellow", yellowAction);
        actionMap.put("panel.blue", blueAction);
        actionMap.put("panel.red", redAction);


        // 注册
        yelloButton.addActionListener(yellowAction);
        blueButton.addActionListener(blueAction);
        redButton.addActionListener(redAction);
    }

    private class ColorAction extends AbstractAction {

        public ColorAction(String name, Color color) {
            putValue(Action.NAME, name);
            putValue(Action.SHORT_DESCRIPTION, "Set panel color to " + name);
            putValue("color", color);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            Color color = (Color) getValue("color");
            buttonPanel.setBackground(color);
        }
    }
}
