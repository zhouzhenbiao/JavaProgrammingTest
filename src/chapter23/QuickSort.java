package chapter23;

import org.junit.Test;

/**
 * 快速排序
 */
public class QuickSort {

    public void quickSort(int[] list) {
        quickSort(list, 0, list.length - 1);
    }

    public void quickSort(int[] list, int first, int last) {
        if (last > first) {
            int pivotIndex = partition(list, first, last);
            quickSort(list, first, pivotIndex - 1);
            quickSort(list, pivotIndex + 1, last);
        }
    }

    /**
     * 先确定 pivot = list[first]，通过pivot来把数组分开，
     *
     * @param list
     * @param first
     * @param last
     * @return index -> pivot == list[index]  得到中心点的索引位置
     */
    public int partition(int[] list, int first, int last) {
        int pivot = list[first];
        int low = first + 1;
        int high = last;

        while (low < high) {
            while (low <= high && list[low] <= pivot)
                low++;

            while (low <= high && list[high] > pivot)
                high--;

            if (high > low) {
                int temp = list[high];
                list[high] = list[low];
                list[low] = temp;
            }
        }

        while (high > first && list[high] >= pivot)
            high--;

        if (pivot > list[high]) {
            list[first] = list[high];
            list[high] = pivot;

            return high;
        } else
            return first;
    }

    @Test
    public void testPartition() {
        final int SIZE = 10000000;
        int[] list = new int[SIZE];
        for (int i = 0; i < SIZE; i++) {
            int temp = (int) (Math.random() * SIZE + 1);
            list[i] = temp;
        }
        long time = System.currentTimeMillis();
        quickSort(list);
        time = System.currentTimeMillis() - time;
        System.out.println(time);
    }
}
