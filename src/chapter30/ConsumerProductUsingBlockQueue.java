package chapter30;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ConsumerProductUsingBlockQueue {

    private static ArrayBlockingQueue<Integer> blockingQueue
            = new ArrayBlockingQueue<>(1);

    public static void main(String[] args) {
        ExecutorService threadPool = Executors.newFixedThreadPool(2);
        threadPool.execute(new ConsumerTask());
        threadPool.execute(new ProductTask());
        threadPool.shutdown();

    }

    private static class ConsumerTask implements Runnable {

        @Override
        public void run() {
            try {
                while (true) {
                    System.out.println("\t\t\tConsumer reads " + blockingQueue.take());
                    Thread.sleep(1000);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static class ProductTask implements Runnable {

        @Override
        public void run() {
            try {
                int i = 0;
                while (true) {
                    System.out.println("Product writes " + i);
                    blockingQueue.put(i++);
                    Thread.sleep(1000);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
