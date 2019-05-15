package chapter30;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 与ToiletRaceWithoutSemaphore 类 进行测试，截取部分console结果
 * 0 is using the toilet
 * 0 is leaving
 * 1 is using the toilet
 * 1 is leaving
 * 2 is using the toilet
 * 2 is leaving
 * 3 is using the toilet
 * 3 is leaving
 */
public class TestSemaphore {
    private static final int THREAD_COUNT = 30;

    private static ExecutorService threadPool = Executors.newFixedThreadPool(THREAD_COUNT);

    public static void main(String[] args) {
        for (int i = 0; i < THREAD_COUNT; i++) {
            threadPool.execute(new Employee(String.valueOf(i)));
        }

        threadPool.shutdown();
    }

    public static class Employee implements Runnable {

        private static Lock lock = new ReentrantLock();
        private String id;

        public Employee(String id) {
            this.id = id;
        }

        public void run() {
            lock.lock();
            try {
                System.out.println(this.id + " is using the toilet");
                Thread.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
                System.out.println(this.id + " is leaving");
            }
        }
    }
}
