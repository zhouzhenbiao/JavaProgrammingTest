package chapter23;

import java.util.Map;

/**
 * 归并排序，时间复杂度O(n log n)
 */
public class Test {
    public void mergeSort(Integer[] list) {
        if (list.length > 1) {
            int leftLength = list.length / 2;
            Integer[] left = new Integer[leftLength];
            System.arraycopy(list, 0, left, 0, leftLength);

            mergeSort(left);

            int rightLength = list.length - leftLength;
            Integer[] right = new Integer[rightLength];
            System.arraycopy(list, leftLength, right, 0, rightLength);
            mergeSort(right);

            mergeSort(left, right, list);
        }
    }

    public void mergeSort(Integer[] left, Integer[] right, Integer[] list) {
        if (list.length > 0) {
            int currentElement1 = 0;
            int currentElement2 = 0;
            int currentElement3 = 0;

            while (currentElement1 < left.length && currentElement2 < right.length) {
                if (left[currentElement1].compareTo(right[currentElement2]) < 0)
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

    @org.junit.Test
    public void testMergeSort() {
        Integer[] list = new Integer[10000000];
        for (int i = 0; i < list.length; i++) {
            list[i] = (int) (Math.random() * 100000000 + 1);
        }

        long time = System.currentTimeMillis();
        mergeSort(list);
        System.out.println(System.currentTimeMillis() - time);
    }
}
