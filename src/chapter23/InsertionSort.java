package chapter23;

import org.junit.Test;

import java.util.Arrays;

/**
 * 插入排序，时间复杂度O(n^2)
 */
public class InsertionSort {
    /**
     * 插入排序具体实现
     * @param list
     */
    public void insertionSort(int[] list) {
        for (int i = 1; i < list.length; i++) {
            int currentElement = list[i];
            int k;
            for (k = i - 1; k >= 0 && list[k] > currentElement; k--) {
                list[k + 1] = list[k];
            }
            //因为上一轮的 k 被 k-- 了
            list[k + 1] = currentElement;
        }
    }

    @Test
    public void testInsertionSort() {
        int[] list = {2, 9, 5, 4, 8, 1, 6};
        insertionSort(list);
        System.out.println(Arrays.toString(list));
    }
}
