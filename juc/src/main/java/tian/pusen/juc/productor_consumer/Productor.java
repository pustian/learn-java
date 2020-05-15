package tian.pusen.juc.productor_consumer;

public class Productor implements Runnable{
    private Plate plate;
    private Object egg = new Object();

    public Productor(Plate plate) {
        this.plate = plate;
    }

    public void run() {
        plate.putEgg(egg);
    }

}
