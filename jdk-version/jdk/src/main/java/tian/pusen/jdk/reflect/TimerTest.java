package tian.pusen.jdk.reflect;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

public class TimerTest {
    public static void main(String[] args) throws InterruptedException {
        ActionListener listener = new TimePrinter();
        Timer t = new Timer(1000,  listener);
        t.start();

//        JOptionPane.showMessageDialog(null, "Quit ？");
        Thread.sleep(10000);
        System.exit(0);
    }
}

class TimePrinter implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("The time is " + new Date());
//        Toolkit.getDefaultToolkit().beep();
    }
}
