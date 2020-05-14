package tian.pusen.juc._01_multithread;

public class Memvisiable implements Runnable{ //这个线程是用来修改flag的值的
    public volatile
    boolean flag = false;

    @Override
    public void run() {
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        flag = true;
        System.out.println("ThreadDemo线程修改后的flag = " + flag);
    }

    public static void main(String[] args){ //这个线程是用来读取flag的值的
        Memvisiable memvisiable = new Memvisiable();
        Thread thread = new Thread( memvisiable );
        thread.start();
        while (true){
            if (memvisiable .flag){
                System.out.println("主线程读取到的flag = " + memvisiable.flag);
                break;
            }
        }
    }
}
