package tian.pusen.juc._02_exception;

public class ThreadException02 {
    public static void main(String[] args) {
        System.out.println("main begin");
        Thread thread = new Thread(new Runnable() {
            void deal(Throwable e) {
                System.out.println("Ex:" + e.getMessage());
            }
            @Override
            public void run() {
                System.out.println("thread begin");
                System.out.print("Arithmetic 1/0 = ");
                try {
                    System.out.println(1/0);
                }catch (ArithmeticException e) {
                    deal(e);
                }
                System.out.println("thread end");
            }
        });

        thread.start();
        System.out.println("main end");
    }
}
