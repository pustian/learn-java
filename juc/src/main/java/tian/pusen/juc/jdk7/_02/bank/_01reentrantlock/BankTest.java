package tian.pusen.juc.jdk7._02.bank._01reentrantlock;

public class BankTest {
    private static final int ACCOUNTS_NUM = 100;
    private static final int INITIAL_BALANCE = 1000;
    public static void main(String[] args) {
        Bank bank = new Bank(ACCOUNTS_NUM, INITIAL_BALANCE);
        for(int i=0; i< ACCOUNTS_NUM; ++i) {
            TransferRunnable transferRunnable = new TransferRunnable(bank, i, INITIAL_BALANCE);
            Thread thread = new Thread(transferRunnable);
            thread.start();
        }
    }
}
