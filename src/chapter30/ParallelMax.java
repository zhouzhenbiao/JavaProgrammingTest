package chapter30;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class ParallelMax {

    public static void main(String[] args) {
        final int SIZE = 100000000;
        int[] list = new int[SIZE];
        for (int i = 0; i < list.length; i++)
            list[i] = (int) (Math.random() * 10000000 + 1);

        long time = System.currentTimeMillis();
        System.out.println("\nThe maximal number is " + max(list));
        time = System.currentTimeMillis() - time;
        System.out.println("\nThe number of processors is " + Runtime.getRuntime().availableProcessors());
        System.out.println("Time is " + time + " milliseconds");
    }

    public static int max(int[] list) {
        RecursiveTask<Integer> mainTask = new MaxTask(list, 0, list.length);
        ForkJoinPool pool = new ForkJoinPool();
        return pool.invoke(mainTask);

    }


    private static class MaxTask extends RecursiveTask<Integer> {

        private final int THRESHOLD = 1000;
        private int[] list;
        private int low;
        private int high;

        public MaxTask(int[] list, int low, int high) {
            this.list = list;
            this.low = low;
            this.high = high;

        }

        @Override
        protected Integer compute() {
            if (high - low < THRESHOLD) {
                int max = list[low];
                for (int i = low + 1; i < high; i++) {
                    if (list[i] > max)
                        max = list[i];
                }
                return max;
            } else {
                int mid = (low + high) >> 1;
                MaxTask left = new MaxTask(list, low, mid);
                MaxTask right = new MaxTask(list, mid, high);
                left.fork();
                right.fork();

                return Math.max(left.join(), right.join());
            }
        }
    }
}
