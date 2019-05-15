package chapter30;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Lock 的使用 Lock lock = new ReentrantLock();
 * lock.lock();
 * lock.unlock();
 */
public class AccountWithoutSyncLock {

    private static Account account = new Account();
    public static void main(String[] args) {
        ExecutorService threadPool = Executors.newCachedThreadPool();
        for (int i = 0; i < 100; i++)
            threadPool.execute(new AddAPennyTask());
        //如果不shutdown，会一直等待是否有 任务执行
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

    public static class Account {
        private int balance;
        private static Lock lock = new ReentrantLock();

        public void deposit(int amount) {
            lock.lock();
            try {
                int newBalance = amount + balance;
                Thread.sleep(5);
                balance = newBalance;
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }
}
