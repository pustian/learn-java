package tian.pusen.swing.jdk7._01;

import javax.swing.*;
import java.awt.*;

public class SizedFrameTest {
    public static void main(String[] args) {
        EventQueue.invokeLater(
                new Runnable() {
                    public void run() {
                        JFrame frame = new SizedFrame();
                        frame.setTitle("SizedFrame");
                        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                        frame.setVisible(true);
                    }
                }
        );
    }
}
class SizedFrame extends JFrame {
    public SizedFrame() {
        Dimension screenSize = getScreenSize();
        int width = screenSize.width;
        int height = screenSize.height;
        setSize(width/2, height/2);
        setLocation(width/4, height/4);
        setLocationByPlatform(true);

        Image img = new ImageIcon("frame.ico").getImage();
        setIconImage(img);
    }

    // get screen dimensions
    public Dimension getScreenSize() {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();
        return screenSize;
    }
}
