package chapter30;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Synchronized 语法块 关键字的使用
 */
public class AccountWithoutSynchronized {

    private static Account account = new Account();

    public static void main(String[] args) {
        ExecutorService threadPool = Executors.newCachedThreadPool();
        for (int i = 0; i < 100; i++) {
            threadPool.execute(new AddAPennyTask());
        }

        threadPool.shutdown();
        while (!threadPool.isTerminated()) {

        }

        System.out.println(account.balance);
    }

    private static class AddAPennyTask implements Runnable {


        @Override
        public void run() {
            account.deposit(1);
        }
    }

    private static class Account {
        private int balance = 0;

        public void deposit(int amount) {
            synchronized(Account.class) {
                int newBalance = balance + amount;

                try {
                    Thread.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                balance = newBalance;
            }
        }

    }
}
