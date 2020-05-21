package tian.pusen.swing.jdk7._36_slider;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.util.Dictionary;
import java.util.Hashtable;

public class SliderFrame extends JFrame {
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame frame = new SliderFrame();
                frame.setVisible(true);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//                frame.setSize(300, 200);
            }
        });
    }

    private JPanel sliderPanel;
    private JTextField textField;
    private ChangeListener changeListener;

    public SliderFrame() {
        sliderPanel = new JPanel();
        sliderPanel.setLayout(new GridBagLayout());
        changeListener = new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                JSlider source = (JSlider) e.getSource();
                textField.setText("" + source.getValue());
            }
        };

        JSlider palinSlider = new JSlider();
        addSlider(palinSlider, "Plain");

        JSlider ticksSlider = new JSlider();
        ticksSlider.setPaintTicks(true);
        ticksSlider.setMajorTickSpacing(20);
        ticksSlider.setMinorTickSpacing(5);
        addSlider(ticksSlider, "ticks");

        JSlider snapNoTicksSlider = new JSlider();
        snapNoTicksSlider.setPaintTicks(true);
        snapNoTicksSlider.setSnapToTicks(true);
        snapNoTicksSlider.setMajorTickSpacing(20);
        snapNoTicksSlider.setMinorTickSpacing(5);
        addSlider(snapNoTicksSlider, "snap to ticks");

        JSlider notrackSlider = new JSlider();
        notrackSlider.setPaintTicks(true);
        notrackSlider.setMajorTickSpacing(20);
        notrackSlider.setMinorTickSpacing(5);
        notrackSlider.setPaintTrack(false); ///***
        addSlider(notrackSlider, "no track");

        JSlider invertedSlider = new JSlider();
        invertedSlider.setPaintTicks(true);
        invertedSlider.setMajorTickSpacing(20);
        invertedSlider.setMinorTickSpacing(5);
        invertedSlider.setInverted(false); ///***
        addSlider(invertedSlider, "inverted");

        JSlider labelSlider = new JSlider();
        labelSlider.setPaintTicks(true);
        labelSlider.setMajorTickSpacing(20);
        labelSlider.setMinorTickSpacing(5);
        labelSlider.setPaintLabels(true); ///***
        addSlider(labelSlider, "label");

        JSlider alphaSlider = new JSlider();
        alphaSlider.setPaintLabels(true);
        alphaSlider.setPaintTicks(true);
        alphaSlider.setMajorTickSpacing(20);
        alphaSlider.setMinorTickSpacing(5);
        Dictionary<Integer, Component> labelTable = new Hashtable<Integer, Component>();
        labelTable.put(0, new JLabel("A"));
        labelTable.put(20, new JLabel("B"));
        labelTable.put(40, new JLabel("C"));
        labelTable.put(60, new JLabel("D"));
        labelTable.put(80, new JLabel("E"));
        labelTable.put(100, new JLabel("F"));
        alphaSlider.setLabelTable(labelTable);
        addSlider(alphaSlider, "Custom Labels");

        add(sliderPanel, BorderLayout.CENTER);

        textField = new JTextField();
        add(textField, BorderLayout.SOUTH);
        pack();
    }

    public void addSlider(JSlider slider, String description) {
        slider.addChangeListener(changeListener);
        JPanel panel = new JPanel();
        panel.add(slider);
        panel.add(new JLabel(description));
        panel.setAlignmentX(Component.LEFT_ALIGNMENT);
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridy = sliderPanel.getComponentCount();
        gridBagConstraints.anchor = GridBagConstraints.WEST;
        sliderPanel.add(panel, gridBagConstraints);
    }
}
