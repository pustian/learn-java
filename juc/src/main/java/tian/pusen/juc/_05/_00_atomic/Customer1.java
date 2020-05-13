package tian.pusen.juc._05._00_atomic;

import java.util.concurrent.atomic.AtomicInteger;

// |Customer  i    countIncrease      | 方法区 --> 线程共享区
// |Customer                          | 堆区   --> 线程共享区
// |------|------|------|------|------| 多线程
public class Customer1 implements Runnable {
    static AtomicInteger count = new AtomicInteger();

    public static int countIncrease() {
        return count.incrementAndGet();
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
