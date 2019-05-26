package chapter30;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

/**
 * 通用的并行合并排序
 */
public class Demo30_13 {
    public static void main(String[] args) {
        Integer[] list = new Integer[10000000];
        for (int i = 0; i < list.length; i++) {
            list[i] = (int) (Math.random() * 100000000 + 1);
        }

        long time = System.currentTimeMillis();
        parallelMergeSort(list);
        System.out.println(System.currentTimeMillis() - time);

        System.out.println("\n runtime is " + Runtime.getRuntime().availableProcessors());
    }

    public static <E extends Comparable<E>> void parallelMergeSort(E[] list) {
        MergeSortTask<E> mergeSortTask = new MergeSortTask<>(list);
        ForkJoinPool pool = new ForkJoinPool();
        pool.invoke(mergeSortTask);
    }

    //并行排序
    private static class MergeSortTask<E extends Comparable<E>> extends RecursiveAction {

        private E[] list;

        public MergeSortTask(E[] list) {
            this.list = list;
        }

        @Override
        protected void compute() {
            if (list.length > 1) {
                //进行排序调用一个方法，分解问题
                int leftLength = list.length >> 1;
                int rightLength = list.length - leftLength;
                E[] left = (E[]) (new Comparable[leftLength]);

                System.arraycopy(list, 0, left, 0, leftLength);

                E[] right = (E[]) (new Comparable[rightLength]);
                System.arraycopy(list, leftLength, right, 0, rightLength);

                invokeAll(new MergeSortTask<>(left), new MergeSortTask<>(right));

                //进行排序调用一个方法，合并问题
                merge(left, right, list);
            }
        }

        private void merge(E[] left, E[] right, E[] list) {

            int currentLeftIndex = 0;
            int currentRightIndex = 0;
            int currentListIndex = 0;

            while (currentLeftIndex < left.length && currentRightIndex < right.length) {
                if (left[currentLeftIndex].compareTo(right[currentRightIndex]) < 0)
                    list[currentListIndex++] = left[currentLeftIndex++];
                else
                    list[currentListIndex++] = right[currentRightIndex++];
            }

            while (currentLeftIndex < left.length)
                list[currentListIndex++] = left[currentLeftIndex++];

            while (currentRightIndex < right.length)
                list[currentListIndex++] = right[currentRightIndex++];
        }
    }
}
