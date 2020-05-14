package tian.pusen.juc.core_java_v9._01_bounce.bounce_multithread;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Bounce
public class BounceThread {
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame frame = new BounceFrame();
                frame.setTitle("BounceThread");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
            }
        });
    }
}

class BallRunnable implements Runnable {
    public static final int STEPS = 1000;
    public static final int DELAY = 3;
    private Ball ball;
    private Component component;

    public BallRunnable(Ball ball, Component comp) {
        this.ball = ball;
        this.component = comp;
    }

    @Override
    public void run() {
        System.out.println("runnable");
        try {
            for (int i = 0; i < STEPS; i++) {
                ball.move(component.getBounds());
                component.repaint();
                Thread.sleep(DELAY);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class BounceFrame extends JFrame {
    private BallComponent component;

    public BounceFrame() {
        component = new BallComponent();
        add(component, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        addButton(buttonPanel, "start", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addBall();
            }
        });
        addButton(buttonPanel, "close", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        add(buttonPanel, BorderLayout.SOUTH);
        pack();
    }

    public void addButton(Container c, String title, ActionListener actionListener) {
        JButton button = new JButton(title);
        c.add(button);
        button.addActionListener(actionListener);
    }

    public void addBall() {
        Ball ball = new Ball();
        component.add(ball);
        Runnable runnable = new BallRunnable(ball, component);
        Thread thread = new Thread(runnable);
        thread.start();
    }
}