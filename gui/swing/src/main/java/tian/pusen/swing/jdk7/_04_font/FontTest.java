package tian.pusen.swing.jdk7._04_font;

import javax.swing.*;
import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;

public class FontTest {
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                JFrame frame = new FontFrame();
                frame.setTitle("Font Test");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
            }
        });
    }
}

class FontFrame extends JFrame {
    public FontFrame() {
        add(new FontComponent());
        pack();
    }
}

class FontComponent extends JComponent {
    public void paintComponent(Graphics graphics ) {
        String message  = "hello world!";

        Graphics2D graphics2D = (Graphics2D)graphics;

        Font font = new Font("Serif", Font.BOLD, 36);
        graphics2D.setFont(font);

        // message的大小
        FontRenderContext context = graphics2D.getFontRenderContext();
        Rectangle2D bounds = font.getStringBounds(message, context);
        double x = (getWidth() -  bounds.getWidth()) /2;
        double y = (getHeight() -  bounds.getHeight()) /2;
        double ascent = - bounds.getY();
        double baseY = y + ascent;

        graphics2D.drawString(message, (int)x, (int)baseY);

        Paint defaultPaint = graphics2D.getPaint();
        // 画基线
        graphics2D.setPaint(Color.LIGHT_GRAY);
//        graphics2D.draw(new Line2D.Double(x, y, x + bounds.getWidth(), y));
        graphics2D.draw(new Line2D.Double(x, baseY, x + bounds.getWidth(), baseY));

        // 画封闭长方形
        Rectangle2D rect = new Rectangle2D.Double(x, y, bounds.getWidth(), bounds.getHeight());
        graphics2D.draw(rect);
    }

    private static final int DEFAULT_WIDTH = 300;
    private static final int DEFAULT_HEIGHT = 200;
    public Dimension getPreferredSize() {
        return new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT);
    }
}
