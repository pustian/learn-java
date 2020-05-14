package tian.pusen.juc._01_multithread;

// |Customer  i    countIncrease      | 方法区 --> 线程共享区
// |Customer                          | 堆区   --> 线程共享区
// |------|------|------|------|------| 多线程
public class Customer1 implements Runnable {
    volatile
    static int count = 0;

    public static int countIncrease() {
        return ++count;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(countIncrease());
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(new Customer1()).start();
        }
    }

}
