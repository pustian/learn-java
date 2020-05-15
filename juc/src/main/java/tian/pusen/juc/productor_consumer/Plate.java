package tian.pusen.juc.productor_consumer;

import java.util.ArrayList;
import java.util.List;

public class Plate {
    /** 装鸡蛋的盘子 */
    List<Object> eggs = new ArrayList<Object>();

    /** 取鸡蛋 */
    public synchronized Object getEgg() {
        while (eggs.size() == 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Object egg = eggs.get(0);
        eggs.clear();// 清空盘子
        notify();// 唤醒阻塞队列的某线程到就绪队列
        System.out.println("拿到鸡蛋"+egg);
        return egg;
    }

    /** 放鸡蛋 */
    public synchronized void putEgg(Object egg) {
        while (eggs.size() > 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        eggs.add(egg);// 往盘子里放鸡蛋
        notify();// 唤醒阻塞队列的某线程到就绪队列
        System.out.println("放入鸡蛋"+egg);
    }

//    public  int eggSize() {
//        return eggs.size();
//    }
}
