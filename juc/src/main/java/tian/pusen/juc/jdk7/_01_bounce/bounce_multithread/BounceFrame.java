////package tian.pusen.core.java.juc._01.jdk7.bounce_multithread;
//
//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//
////public class BounceFrame extends JFrame {
////    private BallComponent comp;
//////    public static final int STEPS = 1000;
//////    public static final int DELAY = 3;
////
////    public BounceFrame() {
//////        setTitle("BounceThread");
////        comp = new BallComponent();
////        add(comp, BorderLayout.CENTER);
////
////        JPanel buttonPanel = new JPanel();
////        addButton(buttonPanel, "start", new ActionListener() {
////            @Override
////            public void actionPerformed(ActionEvent e) {
////                addBall();
////            }
////        });
////        addButton(buttonPanel, "close", new ActionListener() {
////            @Override
////            public void actionPerformed(ActionEvent e) {
////                System.exit(0);
////            }
////        });
////        add(buttonPanel, BorderLayout.SOUTH);
////        pack();
////    }
////
////    public void addButton(Container c, String title, ActionListener actionListener) {
////        JButton button = new JButton(title);
////        c.add(button);
////        button.addActionListener(actionListener);
////    }
////    public void addBall() {
////        Ball ball = new Ball();
////        comp.add(ball);
////        Runnable runnable = new BallRunnable(ball, comp);
////        Thread thread = new Thread(runnable);
////        thread.start();
//////        try{
//////            for(int i =0; i< STEPS; ++i) {
//////                ball.move(comp.getBounds());
//////                comp.paintComponrnt(comp.getGraphics());
//////                Thread.sleep(DELAY);
//////            }
//////        } catch (InterruptedException e) {
//////            e.printStackTrace();
//////        }
////    }
//}
