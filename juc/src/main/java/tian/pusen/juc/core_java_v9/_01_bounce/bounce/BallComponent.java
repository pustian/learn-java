package tian.pusen.juc.core_java_v9._01_bounce.bounce;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class BallComponent extends JPanel {
    private static final int DEFAULT_WIDTH = 450;
    private static final int DEFAULT_HEIGHT = 350;

    private List<Ball> balls = new ArrayList<Ball>();

    public void add(Ball ball) {
        balls.add(ball);
    }

    public void paintComponrnt(Graphics g) {
        super.paintComponent(g); // erase background
        Graphics2D g2 = (Graphics2D )g;
        for(Ball ball: balls) {
            g2.fill(ball.getShape() );
        }
    }

    public Dimension getPreferredSize() {
        return new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT);
    }
}
