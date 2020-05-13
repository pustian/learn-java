package tian.pusen.juc._02_exception;

public class ThreadException01 {
    public static void main(String[] args) {
        System.out.println("main begin");
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("thread begin");
                System.out.print("Arithmetic 1/0 = ");
                System.out.println(1/0);
                System.out.println("thread end");
            }
        });

        thread.start();
        System.out.println("main end");
    }
}
