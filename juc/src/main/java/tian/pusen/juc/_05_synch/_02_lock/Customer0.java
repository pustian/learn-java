package tian.pusen.juc._05_synch._02_lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

// |Customer i    countIncrease       | 堆区  --> 线程共享区
// |------|------|------|------|------| 多线程
public class Customer0 implements Runnable {
    int count = 0;
    Lock countLock = new ReentrantLock();

    public int countIncrease() {
        try {
            countLock.lock();
            return ++count;
        }finally {
            countLock.unlock();
        }
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
