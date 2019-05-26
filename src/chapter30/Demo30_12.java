package chapter30;

import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

/**
 * 并行赋值，顺序赋值
 */
public class Demo30_12 {

    private final static int SIZE = 10000000;

    public static void main(String[] args) {
        double[] list1 = new double[SIZE];
        double[] list2 = new double[SIZE];
        long time = System.currentTimeMillis();
        sequenceValues(list1);
        time = System.currentTimeMillis() - time;
        System.out.println("顺序赋值 ：" + time);

        time = System.currentTimeMillis();
        parallelValues(list2);
        time = System.currentTimeMillis() - time;
        System.out.println("并行赋值 ：" + time);
    }

    public static void sequenceValues(double[] list) {
        Random random = new Random();
        for (int i = 0; i < list.length; i++)
            list[i] = random.nextDouble();
    }

    public static void parallelValues(double[] list) {
        AssignmentTask task = new AssignmentTask(list, 0, list.length);
        ForkJoinPool pool = new ForkJoinPool();
        pool.invoke(task);
    }


    private static class AssignmentTask extends RecursiveAction {

        private final int THRESHOLD = 800;
        private double[] list;
        private int low;
        private int high;
        private Random random;

        public AssignmentTask(double[] list, int low, int high) {
            random = new Random();
            this.list = list;
            this.low = low;
            this.high = high;
        }

        @Override
        protected void compute() {
            if (high - low < THRESHOLD) {
                for (int i = low; i < high; i++)
                    list[i] = random.nextDouble();
                return;
            } else {
                int mid = (low + high) >> 1;
                new AssignmentTask(list, low, mid).invoke();
                new AssignmentTask(list, mid, high).invoke();
            }
        }
    }
}
