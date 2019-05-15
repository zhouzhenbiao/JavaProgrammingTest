package chapter30;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * semaphore 测试用例，厕所案例
 * 理论的听起来有些绕口，其实假设生活中一个常见的场景：
 * 每天早上，大家都热衷于带薪上厕所，但是公司厕所一共只有10个坑位。。
 * 那么只能同时10个人用着，后面来的人都得等着（阻塞），如果走了2个人，
 * 那么又可以进去2个人。这里面就是Semaphore的应用场景，争夺有限的资源。
 *
 * /**
 * 与TestSemaphore 类 进行测试，截取部分console结果
 * 0 is using the toilet
 1 is using the toilet
 2 is using the toilet
 3 is using the toilet
 4 is using the toilet
 5 is using the toilet
 6 is using the toilet
 7 is using the toilet
 8 is using the toilet
 9 is using the toilet
 2 is leaving
 10 is using the toilet
 1 is leaving
 11 is using the toilet
 0 is leaving
 17 is using the toilet
 14 is using the toilet
 16 is using the toilet
 15 is using the toilet
 13 is using the toilet
 3 is leaving
 5 is leaving
 4 is leaving
 6 is leaving
 7 is leaving
 8 is leaving
 9 is leaving
 12 is using the toilet
 19 is using the toilet
 18 is using the toilet
 10 is leaving
 21 is using the toilet
 20 is using the toilet
 11 is leaving
 16 is leaving
 22 is using the toilet
 13 is leaving
 17 is leaving
 14 is leaving
 15 is leaving
 26 is using the toilet
 25 is using the toilet
 24 is using the toilet
 23 is using the toilet
 18 is leaving
 27 is using the toilet
 12 is leaving
 28 is using the toilet
 29 is using the toilet
 19 is leaving
 21 is leaving
 20 is leaving
 23 is leaving
 24 is leaving
 22 is leaving
 26 is leaving
 25 is leaving
 27 is leaving
 28 is leaving
 29 is leaving
 */
public class ToiletRaceWithoutSemaphore {

    private static final int THREAD_COUNT = 30;

    private static ExecutorService threadPool = Executors.newFixedThreadPool(THREAD_COUNT);

    private static Semaphore s = new Semaphore(10);

    public static void main(String[] args) {
        for (int i = 0; i < THREAD_COUNT; i++) {
            threadPool.execute(new Employee(String.valueOf(i), s));
        }

        threadPool.shutdown();
    }

    public static class Employee implements Runnable {

        private String id;
        private Semaphore semaphore;
        private static Random rand= new Random(47);

        public Employee(String id, Semaphore semaphore) {
            this.id = id;
            this.semaphore = semaphore;
        }

        public void run() {
            try {
                semaphore.acquire();
                System.out.println(this.id + " is using the toilet");
                Thread.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                semaphore.release();
                System.out.println(this.id + " is leaving");
            }
        }
    }
}
