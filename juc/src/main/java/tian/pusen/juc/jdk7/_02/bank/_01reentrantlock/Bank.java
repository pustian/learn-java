package tian.pusen.juc.jdk7._02.bank._01reentrantlock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Bank {
    private final double[] accounts;
    private Lock bankLock = new ReentrantLock();
    private Condition sufficientFunds = bankLock.newCondition();

    public Bank(int n, double initialBalance) {
        accounts = new double[n];
        for(int i=0; i< n; ++i) {
            accounts[i] = initialBalance;
        }
    }

    public void transfer(int from, int to, double amount) {
        bankLock.lock();
        try {
            while (accounts[from] < amount) {
                sufficientFunds.await();
            }
            System.out.print(Thread.currentThread());
            accounts[from] -= amount;
            System.out.printf(" %10.2f from %d to %d", amount, from, to);
            accounts[to] += amount;
            System.out.printf(" Total Balance: %10.2f\n", getTotalBalance());
            sufficientFunds.signalAll();
        } catch (InterruptedException e) {
            System.out.println(e);
        } finally {
            bankLock.unlock();
        }
    }
    public double getTotalBalance() {
        bankLock.lock();
        try {
            double sum = 0;
            for (double a : accounts) {
                sum += a;
            }
            return sum;
        }finally {
            bankLock.unlock();
        }
    }

    public int size() {
        return accounts.length;
    }
}