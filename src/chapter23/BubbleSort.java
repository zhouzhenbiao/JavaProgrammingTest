package chapter23;

import org.junit.Test;

import java.util.Arrays;

/**
 * 冒泡排序，时间复杂度O(n^2)
 */
public class BubbleSort {
    /**
     * 冒泡排序的具体实现
     * @param list
     */
    public void bubbleSort(int[] list) {
        //当有一轮轮空、进行“上浮”“冒泡”时，即接下来的比较没有意义，因为已经排完序了
        boolean needNextPass = true;
        for (int i = 1; i < list.length && needNextPass; i++) {
            needNextPass = false;
            for (int j = 0; j < list.length - i; j++) {
                if (list[j] > list[j + 1]) {
                    int temp = list[j + 1];
                    list[j + 1] = list[j];
                    list[j] = temp;
                    needNextPass = true;
                }
            }
        }
    }

    @Test
    public void testBubbleSort() {
        final int SIZE = 100000;
        int[] list = new int[SIZE];
        for (int i = 0; i < SIZE; i++) {
            int temp = (int) (Math.random() * SIZE + 1);
            list[i] = temp;
        }

        long time = System.currentTimeMillis();
        bubbleSort(list);
        time = System.currentTimeMillis() - time;
        System.out.println(time);
    }
}
