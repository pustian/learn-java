package tian.pusen.swing.jdk7._05_image;

import javax.swing.*;
import java.awt.*;

public class ImageTest {
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                JFrame frame = new ImageFrame();
                frame.setVisible(true);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            }
        });
    }
}

class ImageFrame extends JFrame {
    public ImageFrame() {
        add(new ImageComponent());
        pack();
    }
}

class ImageComponent extends JComponent {
    @Override
    protected void paintComponent(Graphics g) {
        Image image = new ImageIcon("D:\\tmp\\600-450pix.JPG").getImage();
        if(null == image) {
            System.out.println("image is null");
            return;
        }
        g.drawImage(image, 0, 0, null);

        //
//        int width = image.getWidth(this);
//        int height = image.getHeight(this);
//        for (int i = 0; i * width < getWidth(); ++i) {
//            for(int j=0; j * height < getHeight(); ++j) {
//                g.copyArea();
//            }
//        }
    }

    private static final int DEFAULT_WIDTH = 600;
    private static final int DEFAULT_HEIGHT = 400;

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT);
    }
}