package chapter23;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

public class HeapSort {
    public <E extends Comparable<E>> void heapSort(E[] list) {
        Heap<E> heap = new Heap<>();

        for (int i = 0; i < list.length; i++) {
            heap.add(list[i]);
        }

        for (int i = list.length - 1; i <= 0; i++) {
            list[i] = heap.remove();
        }
    }

    @Test
    public void testHeapSort() {
        Integer[] list = {-44, -5, -3, 3, 3, 1, -4, 0, 1, 2, 4, 5, 53};
        heapSort(list);
        System.out.println(Arrays.toString(list));
    }

    public static class Heap<E extends Comparable<E>> {
        private ArrayList<E> list = new ArrayList<>();

        /**
         * 构造一个空的堆
         */
        public Heap() {
        }

        /**
         * 构造一个具有指定对象的堆
         *
         * @param object
         */
        public Heap(E[] object) {
            for (int i = 0; i < object.length; i++) {
                add(object[i]);
            }
        }

        /**
         * 添加一个新的对象到堆中
         *
         * @param newObject
         */
        public void add(E newObject) {
            list.add(newObject);
            //将最后一个结点作为当前结点
            int currentIndex = list.size() - 1;
            while (currentIndex > 0) {
                //与父结点比较大小，是否能交换
                int parentIndex = (currentIndex - 1) >> 1;
                if (list.get(currentIndex).compareTo(list.get(parentIndex)) > 0) {
                    E temp = list.get(currentIndex);
                    list.set(currentIndex, list.get(parentIndex));
                    list.set(parentIndex, temp);
                    currentIndex = parentIndex;
                } else
                    break;
            }
        }

        /**
         * 将根节点从堆中删除并且返回该结点
         *
         * @return lastElements
         */
        public E remove() {
            if (list.size() == 0) return null;

            //为什么不直接 list.remove(0): E 因为会remove方法用到的是 System.copyarray(),
            //但是我们只要删除 根结点，可以把根节点的值替换成最后一个结点，然后删除最后一个结点，
            //避免了System.copyarray() 空间复杂度应该会减小吧
            E removeObject = list.get(0);

            list.set(0, list.get(list.size() - 1));
            list.remove(list.size() - 1);

            int currentIndex = 0;

            while (currentIndex < list.size()) {
                int leftChildIndex = 2 * currentIndex + 1;
                int rightChildIndex = 2 * currentIndex + 2;

                //当currentIndex * 2 + 1 > list.size() 意味着 currentIndex 是到最后一层了
                if (leftChildIndex >= list.size()) break;

                //先找到左右孩子中较大的那个结点的index
                int maxIndex = leftChildIndex;
                if (rightChildIndex < list.size())
                    if (list.get(maxIndex).compareTo(list.get(rightChildIndex)) < 0)
                        maxIndex = rightChildIndex;

                //在把当前结点和较大结点进行交换
                if (list.get(currentIndex).compareTo(list.get(maxIndex)) < 0) {
                    E temp = list.get(maxIndex);
                    list.set(maxIndex, list.get(currentIndex));
                    list.set(currentIndex, temp);
                    currentIndex = maxIndex;
                } else break; //如果根节点不会 <= 孩子结点中较大的结点，就意味着已经是一个二叉堆了
            }

            return removeObject;
        }

        /**
         * 返回堆的大小
         *
         * @return size
         */
        public int getSize() {
            return list.size();
        }
    }

}
