package chapter30;

import chapter23.MergeSort;

import java.util.Arrays;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

public class ParallelMergeSort {

    public static void main(String[] args) {
        final int SIZE = 10000000;
        int[] list1 = new int[SIZE];
        int[] list2 = new int[SIZE];

        for (int i = 0; i < list1.length; i++)
            list1[i] = list2[i] = (int) (Math.random() * 10000000);

        long time = System.currentTimeMillis();
        parallelMergeSort(list1);
        time = System.currentTimeMillis() - time;
        System.out.println("\nParallel time with " + Runtime.getRuntime().availableProcessors()
                + " processors is " + time + " milliseconds");
        MergeSort sort = new MergeSort();
        time = System.currentTimeMillis();
        sort.mergeSort(list2);
        time = System.currentTimeMillis() - time;
        System.out.println("\nSequential time is " + time + " milliseconds");
    }

    public static void parallelMergeSort(int[] list) {
        SortTask mainTask = new SortTask(list);
        ForkJoinPool pool = new ForkJoinPool();
        pool.invoke(mainTask);
    }

    private static class SortTask extends RecursiveAction {
        private final int THRESHOLD = 500;
        private int[] list;

        SortTask(int[] list) {
            this.list = list;
        }

        @Override
        protected void compute() {
            if (THRESHOLD > list.length) {
                Arrays.sort(list);
                return;
            }

            int[] left = new int[list.length >> 1];
            System.arraycopy(list, 0, left, 0, left.length);

            int secondHalfLength = list.length - left.length;
            int[] right = new int[secondHalfLength];
            System.arraycopy(list, left.length, right, 0, secondHalfLength);

            invokeAll(new SortTask(left), new SortTask(right));

            merge(left, right, list);
        }

        private void merge(int[] left, int[] right, int[] list) {
            int currentLeft = 0;
            int currentRight = 0;
            int currentList = 0;
            while (currentLeft < left.length && currentRight < right.length) {
                if (left[currentLeft] < right[currentRight])
                    list[currentList++] = left[currentLeft++];
                else
                    list[currentList++] = right[currentRight++];
            }

            while (currentLeft < left.length)
                list[currentList++] = left[currentLeft++];

            while (currentRight < right.length)
                list[currentList++] = right[currentRight++];
        }
    }
}
