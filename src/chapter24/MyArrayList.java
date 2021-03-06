package chapter24;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class MyArrayList<E> extends MyAbstractList<E> {
    public static final int INITIAL_CAPACITY = 16;

    private E[] data = (E[]) (new Object[INITIAL_CAPACITY]);

    public MyArrayList() {
    }

    public MyArrayList(E[] objects) {
        for (int i = 0; i < objects.length; i++) {
            add(objects[i]);
        }
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("index " + index + " out fo bounds");
        }
    }

    private void ensureCapacity() {
        if (size >= data.length) {
            E[] newData = (E[]) (new Object[size * 2 + 1]);
            System.arraycopy(data, 0, newData, 0, size);
            data = newData;
        }
    }

    public void trimToSize() {
        if (size != data.length) {
            E[] newData = (E[]) (new Object[size]);
            System.arraycopy(data, 0, newData, 0, size);

            data = newData;
        }
    }

    @Override
    public void add(int index, E e) {
        ensureCapacity();

        for (int i = size - 1; i >= index; i--)
            data[i + 1] = data[i];

        data[index] = e;

        size++;
    }

    @Override
    public E remove(int index) {
        //判断是否越界
        checkIndex(index);
        E e = data[index];
        //删除当前元素，将后一个元素放到前一个
        for (int i = index; i < size - 1; i++)
            data[i] = data[i + 1];
        data[size - 1] = null;
        size--;
        return e;
    }

    @Override
    public boolean contains(E e) {
        for (int i = 0; i < size; i++)
            if (e.equals(data[i])) return true;
        return false;
    }

    @Override
    public int indexOf(E e) {
        for (int i = 0; i < size; i++)
            if (e.equals(data[i])) return i;
        return -1;
    }

    @Override
    public int lastIndexOf(E e) {
        for (int i = size - 1; i >= 0; i--)
            if (e.equals(data[i])) return i;
        return -1;
    }

    @Override
    public E set(int index, E e) {
        checkIndex(index);
        E old = data[index];

        data[index] = e;
        return old;
    }

    @Override
    public E get(int index) {
        checkIndex(index);
        return data[index];
    }

    @Override
    public void clear() {
        data = (E[]) new Object[INITIAL_CAPACITY];
        size = 0;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("[ ");

        for (int i = 0; i < size; i++) {
            result.append(data[i]);
            if (i < size - 1) result.append(", ");
        }

        return result.toString() + " ]";
    }

    @Override
    public Iterator<E> iterator() {
        return new MyItr();
    }

    private class MyItr implements Iterator<E> {

        private int currentIndex = 0;

        //当 next 之后，remove删除的应该是当前的 next 元素，所以新建一个变量，保存 currentIndex
        private int lastReturn = -1;

        private int expectedSizeCount = size;

        @Override
        public boolean hasNext() {
            return currentIndex < size;
        }

        @Override
        public E next() {
            checkForComodification();
            int i = currentIndex;
            if (i >= size)
                throw new NoSuchElementException();
            if (i >= MyArrayList.this.data.length)
                throw new ConcurrentModificationException();

            currentIndex = i + 1;
            lastReturn = i;
            return data[i];
        }

        @Override
        public void remove() {
            if (lastReturn < 0)
                throw new IllegalStateException();

            checkForComodification();

            currentIndex = lastReturn;
            MyArrayList.this.remove(lastReturn);
            lastReturn = -1;
            expectedSizeCount = size;

        }

        private void checkForComodification() {
            if (expectedSizeCount != size)
                throw new ConcurrentModificationException();
        }

    }

    public static void main(String[] args) {
        MyArrayList<String> list = new MyArrayList<>();
        list.add("苏志超");
        System.out.println("(1) : " + list);

        list.add(0, "饶江威");
        System.out.println("(2) : " + list);

        list.add("周振标");
        System.out.println("(3) : " + list);

        list.add("蔡旭斌");
        System.out.println("(4) : " + list);

        list.add(2, "邓静静");
        System.out.println("(5) : " + list);

        list.add(5, "王楠");
        System.out.println("(6) : " + list);

        list.remove("饶江威");
        System.out.println("(7) : " + list);

        list.remove(2);
        System.out.println("(8) : " + list);

        list.remove(list.size - 1);
        System.out.println("(9) : " + list);

        Iterator<String> iterator = list.iterator();

        System.out.println(iterator.next());
        iterator.remove();
//        list.remove(0);
        System.out.println(iterator.next());
        System.out.println(list);
    }
}
