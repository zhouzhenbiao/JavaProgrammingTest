package chapter19;

import org.junit.Test;

import java.util.ArrayList;

public class Demo19 {

    /**
     * Demo19.3 (ArrayList 中的不同元素)  --> true，已实现
     * 返回一个新的ArrayList，新的列表中包含自原来列表中不重复的元素
     *
     * @param list
     * @param <E>
     * @return ArrayList<String>
     */

    public static <E> ArrayList<E> removeDuplicates(ArrayList<E> list) {
        for (int i = 0; i < list.size(); i++)
            for (int j = i + 1; j < list.size(); j++)
                if (list.get(i).equals(list.get(j)))
                    list.remove(j);

        return list;
    }

    /**
     * Demo19_4(泛型线性搜索) --> true，已实现
     * 既然都是线性搜索了，即使下标一个一个轮过去，存在即返回 E key 的下标，不存在返回-1
     *
     * @param list
     * @param key
     * @param <E>
     * @return
     */
    public static <E extends Comparable<E>> int linearSearch(E[] list, E key) {
        for (int i = 0; i < list.length; i++)
            if (list[i].compareTo(key) == 0)
                return i;
        return -1;
    }

    /**
     * Demo19_5(数组中的最大元素) --> true，已实现
     * 寻找最大值，用comparable接口的comparableTo() 方法
     *
     * @param list
     * @param <E>
     * @return
     */
    public static <E extends Comparable<E>> E max(E[] list) {
        E max = null;
        if (list.length != 0) {
            max = list[0];
            for (int i = 1; i < list.length; i++)
                if (list[i].compareTo(max) > 0) {
                    max = list[i];
                }
            return max;
        } else
            return max;
    }

    /**
     * Demo19_6(二维数组中的最大元素) --> true，已实现
     * 一样，寻找最大值，用comparable接口的comparableTo() 方法
     *
     * @param lists
     * @param <E>
     * @return
     */
    public static <E extends Comparable<E>> E max(E[][] lists) {
        E max = null;
        if (lists == null || lists.length == 0 || lists[0].length == 0) {
            return max;
        } else {
            max = lists[0][0];
            for (E[] list : lists)
                for (E e : list)
                    if (e.compareTo(max) > 0)
                        max = e;
            return max;
        }
    }

    /**
     * Demo19_7(泛型二分查找法) --> true，已实现
     * 不同于线性查找，用到二分查找，二分查找默认已经排好序了
     *
     * @param list
     * @param key
     * @param <E>
     * @return
     */
    public static <E extends Comparable<E>> int binarySearch(E[] list, E key) {
        int low = 0;
        int high = list.length - 1;
        while (high >= low) {
            int mid = (low + high) / 2;
            if (list[mid].compareTo(key) == 0)
                return mid;
            else if (list[mid].compareTo(key) > 0)
                high = mid - 1;
            else
                low = mid + 1;
        }
        return -low - 1;
    }

    /**
     * Demo19_8(打乱ArrayList) --> true，已实现
     *
     * @param list
     * @param <E>
     */
    public static <E> void shuffle(ArrayList<E> list) {
        for (int i = 0; i < list.size(); i++) {
            int random = (int) (Math.random() * list.size());
            E e = list.get(random);
            list.set(random, list.get(i));
            list.set(i, e);
        }
    }

    /**
     * Demo19_9(对ArrayList排序) --> true，已实现
     * 用实现了Comparable接口的comparableTo() 方法实现，冒泡排序，大的往上走，左右比较，大的往上走，然后对顶端就是最大值
     *
     * @param list
     * @param <E>
     */
    public static <E extends Comparable<E>> void sort(ArrayList<E> list) {
        if (list.size() != 0) {
            //冒泡排序法就是找出列中最大值，并把最大值排到末尾
            for (int i = 0; i < list.size() - 1; i++)
                for (int j = 1; j < list.size() - i; j++)
                    if (list.get(j).compareTo(list.get(j - 1)) < 0) {
                        E e = list.get(j);
                        list.set(j, list.get(j - 1));
                        list.set(j - 1, e);
                    }
        }
    }

    @Test
    public void max() throws Exception {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(12321);
        list.add(432432);
        list.add(3443);
        list.add(232132);
        list.add(12);
        list.add(2);
        System.out.println(new Demo19().max(list));
    }

    public static <E extends Comparable<E>> E max(ArrayList<E> list) {
        E max = null;
        if (list.size() != 0) {
            max = list.get(0);
            for (int i = 1; i < list.size(); i++)
                if (max.compareTo(list.get(i)) < 0)
                    max = list.get(i);
            return max;
        } else
            return max;
    }



    public static void main(String[] args) {

    }
}
