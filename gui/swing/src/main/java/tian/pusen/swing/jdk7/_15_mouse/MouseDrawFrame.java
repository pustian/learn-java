package tian.pusen.swing.jdk7._15_mouse;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

// 鼠标画线 双击取消
public class MouseDrawFrame extends JFrame {
    public static void main(String[] args) {
        JFrame frame = new MouseDrawFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public MouseDrawFrame(){
        add(new MouseDrawComponent());
        pack();
    }
}

class MouseDrawComponent extends JComponent {
    List<Point2D> point2Ds ;
    public MouseDrawComponent() {
        point2Ds = new ArrayList<Point2D>();
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                point2Ds.add(e.getPoint());
                repaint();
            }
        });
    }

    private void connectPoint(Graphics2D graphics2D) {
        for(int i=1; i< point2Ds.size(); ++i) {
            Point2D pre = point2Ds.get(i-1);
            Point2D cur = point2Ds.get(i);
            graphics2D.drawLine((int)pre.getX(), (int)pre.getY(), (int)cur.getX(), (int)cur.getY());
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D graphics2D = (Graphics2D) g;
        connectPoint(graphics2D);
    }

    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(WIDTH, HEIGHT);
    }
}
