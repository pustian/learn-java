package tian.pusen.juc._02_exception;

public class ThreadException03 {
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
                System.out.println(1/0);
                System.out.println("thread end");
            }
        });

        thread.setUncaughtExceptionHandler(new ExceptionHandler());
        thread.start();
        System.out.println("main end");
    }
}

class ExceptionHandler implements Thread.UncaughtExceptionHandler {

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        System.out.println("thread t" + t.getName() + " runtime Exception=" + e.getMessage());
    }
}