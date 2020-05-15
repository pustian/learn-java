package tian.pusen.juc.productor_consumer;

public class MainTest {
    public static void main(String args[]) {
        Plate plate = new Plate();
        for(int i = 0; i < 10; i++) {
            new Thread(new Productor(plate)).start();
            new Thread(new Consumer(plate)).start();
        }
    }
}
