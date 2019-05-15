package chapter30;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 线程间协作
 * Condition condition 的 使用，一个方法块 condition.await
 另外的几个方法块 存在 condition.signal
 */
public class ThreadCooperation {

    private static Account account = new Account();

    public static void main(String[] args) {
        ExecutorService threadPool = Executors.newFixedThreadPool(2);
        threadPool.execute(() -> {
            try {
                //存钱操作
                for (int i = 0; i < 20; i++) {
                    account.deposit((int) (Math.random() * 10 + 1));
                    Thread.sleep(1000);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        threadPool.execute(() -> {
            //取钱操作
            for (int i = 0; i < 20; i++)
                account.withdraw((int) (Math.random() * 10 + 1));
        });

        threadPool.shutdown();
        while (!threadPool.isTerminated()) {

        }
        System.out.println("Thread 1\t\tThread 2\t\tBalance");
    }

    private static class Account {
        private static Lock lock = new ReentrantLock();
        private static Condition condition = lock.newCondition();

        private int balance;

        public int getBalance() {
            return balance;
        }

        /**
         * withdraw 取钱
         *
         * @param amount
         */
        public void withdraw(int amount) {
            lock.lock();
            try {
                while (balance < amount) {
                    System.out.println("\t\t\t取钱方法 在等待 存钱方法的执行并期望存钱之后的余额能有取钱多");
                    condition.await();
                }

                balance -= amount;
                System.out.println("\t\t\t取钱 : " + amount + "元\t\t余额 ：" + getBalance() + "元");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }

        /**
         * deposit 存款
         *
         * @param amount
         */
        public void deposit(int amount) {
            lock.lock();
            try {
                balance += amount;
                System.out.println("\t\t\t存钱 ： " + amount + "元\t\t余额 ： " + getBalance() + "元");
                condition.signalAll();
            } finally {
                lock.unlock();
            }
        }
    }
}
