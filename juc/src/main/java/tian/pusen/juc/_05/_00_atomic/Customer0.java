package tian.pusen.juc._05._00_atomic;

import java.util.concurrent.atomic.AtomicInteger;

public class Customer0 implements Runnable {
    AtomicInteger count = new AtomicInteger();

    public int countIncrease() {
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
        Customer0 coustomer = new Customer0();
        for (int i = 0; i < 10; i++) {
            new Thread(coustomer).start();
        }
    }

}
