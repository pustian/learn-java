package tian.pusen.swing.jdk7._34_borders;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BorderFrame extends JFrame {
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame frame = new BorderFrame();
                frame.setVisible(true);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//                frame.setSize(300, 200);
            }
        });
    }

    private JPanel buttonPanel;
    private JPanel showPanel;
    private ButtonGroup buttonGroup;
    private static final int DEFAULT_SIZE = 36;

    private BorderFrame() {
        showPanel = new JPanel();
        buttonPanel = new JPanel();
        buttonGroup = new ButtonGroup();

        addRadioBuuton("lowered bevel", BorderFactory.createLoweredBevelBorder());
        addRadioBuuton("raised bevel", BorderFactory.createRaisedBevelBorder());
        addRadioBuuton("etched", BorderFactory.createEtchedBorder());
        addRadioBuuton("line", BorderFactory.createLineBorder(Color.BLUE));
        addRadioBuuton("Matte", BorderFactory.createMatteBorder(10, 10, 10, 10, Color.RED));
        addRadioBuuton("Empty", BorderFactory.createEmptyBorder());

        Border etched = BorderFactory.createEtchedBorder();
        Border titled = BorderFactory.createTitledBorder(etched, "Border types");
        buttonPanel.setBorder(titled);

        setLayout(new GridLayout(2, 1));
        add(buttonPanel);
        add(showPanel);
        pack();
    }

    private void addRadioBuuton(String name, final Border border) {
        JRadioButton button = new JRadioButton(name);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showPanel.setBorder(border);
            }
        });
        buttonGroup.add(button);
        buttonPanel.add(button);
    }
}
