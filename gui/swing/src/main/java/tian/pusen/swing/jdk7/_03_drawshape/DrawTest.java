package tian.pusen.swing.jdk7._03_drawshape;


import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

public class DrawTest {
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                JFrame frame = new DrawFrame("drawFrame");
                frame.setVisible(true);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            }
        });
    }
}

class DrawFrame extends JFrame {
    public DrawFrame(String title) {
        super(title);
        add(new DrawComponent());
        pack();

//        Container contentPane = getContentPane();
//        Component component = new DrawComponent();
//        contentPane.add(component);
//
//        setSize(800, 600);
    }
}

class DrawComponent extends JComponent {
    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D graphics2D = (Graphics2D)g;
        Paint defaultPaint = graphics2D.getPaint();

        graphics2D.setPaint(Color.red);
        graphics2D.draw(Shape.rect);
        graphics2D.setPaint(defaultPaint);

        graphics2D.setPaint(Color.GREEN);
        graphics2D.fill(Shape.ellipse);
//        graphics2D.draw(Shape.ellipse);
        graphics2D.setPaint(defaultPaint);

        double centerX = Shape.rect.getCenterX();
        double centerY = Shape.rect.getCenterY();
        double radius = 150;
        Ellipse2D circle = new Ellipse2D.Double();
        circle.setFrameFromCenter(centerX, centerY, centerX+radius, centerY+radius);
        graphics2D.draw(circle);
    }

    private static final int WIDTH =  800;
    private static final int HEIGHT =  600;
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(WIDTH, HEIGHT);
    }
}

class Shape {
    static float topX = 100.0F;
    static float topY = 100.0F;
    static float width = 200.0F;
    static float height = 150.0F;
    static Rectangle2D rect = new Rectangle2D.Float(topX, topY, width, height);

    static double topDX = 100.0;
    static double topDY = 100.0;
    static double widthD = 200.0;
    static double heightD = 150.0;
    static Ellipse2D ellipse = new Ellipse2D.Double(topDX, topDY, widthD, heightD);
}