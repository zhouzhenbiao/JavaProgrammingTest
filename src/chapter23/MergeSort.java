package chapter23;

import org.junit.Test;

/**
 * 归并排序，时间复杂度O(n log n)
 */
public class MergeSort {
    public void mergeSort(int[] list) {
        if (list.length > 1) {
            int leftLength = list.length / 2;
            int[] left = new int[leftLength];
            System.arraycopy(list, 0, left, 0, leftLength);

            mergeSort(left);

            int rightLength = list.length - leftLength;
            int[] right = new int[rightLength];
            System.arraycopy(list, leftLength, right, 0, rightLength);
            mergeSort(right);

            mergeSort(left, right, list);
        }
    }

    public void mergeSort(int[] left, int[] right, int[] list) {
        if (list.length > 0) {
            int currentElement1 = 0;
            int currentElement2 = 0;
            int currentElement3 = 0;

            while (currentElement1 < left.length && currentElement2 < right.length) {
                if (left[currentElement1] < right[currentElement2])
                    list[currentElement3++] = left[currentElement1++];
                else
                    list[currentElement3++] = right[currentElement2++];
            }
            while (currentElement1 < left.length)
                list[currentElement3++] = left[currentElement1++];
            while (currentElement2 < right.length)
                list[currentElement3++] = right[currentElement2++];
        }
    }

    @Test
    public void testMergeSort() {
        final int SIZE = 10000000;
        int[] list = new int[SIZE];
        for (int i = 0; i < SIZE; i++) {
            int temp = (int) (Math.random() * SIZE + 1);
            list[i] = temp;
        }
        long time = System.currentTimeMillis();
        mergeSort(list);
        time = System.currentTimeMillis() - time;
        System.out.println(time);
    }
}
