package chapter30;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorDemo {

    public static long testExecutor(ExecutorService executorService) {
        long time = System.currentTimeMillis();
        for (int i = 0; i < 1000; i++) {
            executorService.execute(() -> {
                int j = 2;
            });
        }
        executorService.shutdown();
        return System.currentTimeMillis() - time;
    }

    public static void main(String[] args) {
        ExecutorService threadPool = Executors.newCachedThreadPool();
        threadPool.execute(() -> {
            for (int i = 0; i < 1000; i++) {
                System.out.print("  " + i);
            }
        });
        threadPool.execute(() -> {
            for (int i = 0; i < 1000; i++) {
                System.out.print("  " + 'a');
            }
        });
        threadPool.execute(() -> {
            for (int i = 0; i < 1000; i++) {
                System.out.print("  " + 'b');
            }
        });
        threadPool.shutdown();

        while (!threadPool.isTerminated()) {

        }

        System.out.println("线程结束了");
    }
}
