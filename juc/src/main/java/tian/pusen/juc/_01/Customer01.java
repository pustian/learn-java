package tian.pusen.juc._01;


// |线程1---| 线程2---|------|------|------| 多线程
// 线程1 |Customer i    countIncrease       | 堆区  --> 线程共享区
// 线程2 |Customer i    countIncrease       | 堆区  --> 线程共享区
public class Customer01{
    int count = 0;

    public int countIncrease() {
        return ++count;
    }

    public static void main(String[] args) {
        Runnable runnable = new Runnable() {
            public void run() {
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Customer01 customer01 = new Customer01();
                System.out.println(""+ customer01.countIncrease());
            }
        };
        for (int i = 0; i < 10; i++) {
            new Thread(runnable).start();
        }
    }
}
