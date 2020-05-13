package tian.pusen.juc._05._01_synchronize._02;

// |Customer i    countIncrease       | 堆区  --> 线程共享区
// |------|------|------|------|------| 多线程
public class Customer0 implements Runnable {
    int count = 0;

    public int countIncrease() {
        synchronized (this) {
            return ++count;
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
