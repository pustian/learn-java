package tian.pusen.juc.productor_consumer;

public class Consumer implements Runnable {
    private Plate plate;
    private Object egg = new Object();

    public Consumer(Plate plate) {
        this.plate = plate;
    }

    // 生产者将生产的鸡蛋放入盘子
    public void run() {
        plate.getEgg();
    }

    public Object getEgg() {
        return egg;
    }
}
