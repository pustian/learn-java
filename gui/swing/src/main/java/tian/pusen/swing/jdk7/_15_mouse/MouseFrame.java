package tian.pusen.swing.jdk7._15_mouse;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

public class MouseFrame extends JFrame {
    public static void main(String[] args) {
        JFrame frame = new MouseFrame();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
//        frame.setSize(800, 600);
        frame.setVisible(true);
    }

    public MouseFrame() {
        add(new MouseComponent());
        pack();
    }
}

class MouseComponent extends JComponent {
    private static final int SIDE_LENGTH = 10;
    private ArrayList<Rectangle2D> squares;
    private Rectangle2D current;

    public MouseComponent() {
        squares = new ArrayList<Rectangle2D>();
        current = null;

        addMouseListener(new MouseHandler());
        addMouseMotionListener(new MouseMotionHandler());
    }

    public Rectangle2D find(Point2D point2D) {
        for (Rectangle2D rectangle2D : squares) {
            if (rectangle2D.contains(point2D))
                return rectangle2D;
        }
        return null;
    }

    public void add(Point2D point2D) {
        double x = point2D.getX();
        double y = point2D.getY();

        current = new Rectangle2D.Double(x - SIDE_LENGTH / 2, y - SIDE_LENGTH / 2, SIDE_LENGTH, SIDE_LENGTH);
        squares.add(current);
        repaint();
    }

    public void remove(Rectangle2D rectangle2D) {
        if (rectangle2D == null) {
            return;
        }
        if (rectangle2D == current) {
            current = null;
        }
        squares.remove(rectangle2D);
        repaint();
    }

    // MouseListener 需要实现全部
    private class MouseHandler extends MouseAdapter {
        @Override
        public void mousePressed(MouseEvent e) {
            current = find(e.getPoint());
            if (current == null) {
                add(e.getPoint());
            }
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            current = find(e.getPoint());
            if (current != null && e.getClickCount() >= 2) {
                remove(current);
            }
        }
    }

    // 也可以实现 MouseMotionAdapter
    private class MouseMotionHandler implements MouseMotionListener {
        @Override
        public void mouseMoved(MouseEvent e) {
            if (find(e.getPoint()) == null) {
                setCursor(Cursor.getDefaultCursor());
            } else {
                setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
            }
        }

        @Override
        public void mouseDragged(MouseEvent e) {
            if(current != null) {
                int x = e.getX();
                int y = e.getY();

                current.setFrame(x - SIDE_LENGTH/2, y-SIDE_LENGTH/2, SIDE_LENGTH, SIDE_LENGTH);
                repaint();
            }
        }
    }
}

