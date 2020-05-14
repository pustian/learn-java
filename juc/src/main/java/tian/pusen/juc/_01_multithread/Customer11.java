package tian.pusen.juc._01_multithread;


// |线程1---| 线程2---|------|------|------| 多线程
// 线程1 |Customer  i    countIncrease      | 方法区 --> 线程共享区
// 线程2
public class Customer11 {
    static int count = 0;

    public static int countIncrease() {
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
                Customer11 customer11 = new Customer11();
                System.out.println("" + Customer11.countIncrease());
            }
        };
        for (int i = 0; i < 10; i++) {
            new Thread(runnable).start();
        }
    }

}
