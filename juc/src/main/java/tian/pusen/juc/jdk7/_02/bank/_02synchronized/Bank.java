package tian.pusen.juc.jdk7._02.bank._02synchronized;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Bank {
    private final double[] accounts;

    public Bank(int n, double initialBalance) {
        accounts = new double[n];
        for(int i=0; i< n; ++i) {
            accounts[i] = initialBalance;
        }
    }

    public synchronized void transfer(int from, int to, double amount) {
        try {
            while (accounts[from] < amount) {
                wait();
            }
            System.out.print(Thread.currentThread());
            accounts[from] -= amount;
            System.out.printf(" %10.2f from %d to %d", amount, from, to);
            accounts[to] += amount;
            System.out.printf(" Total Balance: %10.2f\n", getTotalBalance());
            notifyAll();
        } catch (InterruptedException e) {
            System.out.println(e);
        } finally {
            notifyAll();
        }
    }
    public double getTotalBalance() {
        synchronized (Bank.class) {
            double sum = 0;
            for (double a : accounts) {
                sum += a;
            }
            return sum;
        }
    }

    public int size() {
        return accounts.length;
    }
}