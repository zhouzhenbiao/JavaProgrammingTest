package chapter24;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class MyLinkedList<E> extends MyAbstractList<E> {

    private Node<E> head;
    private Node<E> tail;

    public MyLinkedList() {
    }

    public MyLinkedList(E[] elements) {
        super(elements);
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException("index " + index + " out fo bounds");
    }

    public void addFirst(E e) {
        Node<E> newNode = new Node<>(e);
        newNode.next = head;
        head = newNode;
        size++;

        if (tail == null)
            tail = head;
    }

    public void addLast(E e) {
        Node<E> newNode = new Node<>(e);
        if (tail == null)
            head = tail = newNode;
        else {
            tail.next = newNode;
            tail = tail.next;
        }
        size++;
    }

    public E getFirst() {
        if (size == 0)
            return null;
        else
            return head.element;
    }

    public E getLast() {
        if (size == 0)
            return null;
        else
            return tail.element;
    }

    public E removeFirst() {
        if (size == 0)
            return null;
        else {
            Node<E> temp = head;
            head = head.next;
            if (head == null) tail = null;
            size--;
            return temp.element;
        }
    }

    public E removeLast() {
        if (size == 0)
            return null;
        else if (size == 1) {
            Node<E> temp = this.head;
            head = tail = null;
            size = 0;
            return temp.element;
        } else {
            Node<E> current = this.head;

            for (int i = 0; i < size - 2; i++)
                current = current.next;

            Node<E> temp = tail;
            tail = current;
            tail.next = null;
            size--;
            return temp.element;
        }
    }

    @Override
    public void add(int index, E e) {
        if (size == 0)
            addFirst(e);
        else if (index >= size) addLast(e);
        else if (index == 0) addFirst(e);
        else {
            Node<E> current = this.head;

            for (int i = 0; i < index - 1; i++)
                current = current.next;

            Node<E> next = current.next;
            current.next = new Node<>(e);
            current.next.next = next;
            size++;
        }
    }

    @Override
    public E remove(int index) {
        checkIndex(index);
        if (index == 0) return removeFirst();
        else if (index == size - 1) return removeLast();
        else {
            Node<E> previous = this.head;

            for (int i = 0; i < index - 1; i++)
                previous = previous.next;

            Node<E> current = previous.next;
            previous.next = (previous.next).next;
            size--;
            return current.element;
        }
    }

    @Override
    public boolean contains(E e) {
        if (size == 0) return false;
        else {
            Node<E> current = this.head;
            while (current.next != null) {
                if (current.element.equals(e)) return true;
                current = current.next;
            }
            if (current.element.equals(e)) return true;
            return false;
        }
    }

    @Override
    public int indexOf(E e) {
        if (size == 0)
            return -1;
        else {
            Node<E> current = this.head;

            int i;
            for (i = 0; i < size - 1; i++) {
                if (current.element.equals(e)) return i;
                current = current.next;
            }

            return current.element.equals(e) ? i : -1;
        }
    }

    @Override
    public int lastIndexOf(E e) {
        if (size == 0) return -1;
        else {
            E[] temp = (E[]) new Object[size];
            Node<E> current = this.head;

            for (int i = 0; i < size - 1; i++) {
                temp[i] = current.element;
                current = current.next;
            }
            temp[size - 1] = current.element;

            for (int i = size - 1; i >= 0; i--) {
                if (temp[i].equals(e)) return i;
            }
            return -1;
        }
    }

    @Override
    public E set(int index, E e) {
        checkIndex(index);

        Node<E> current = this.head;
        for (int i = 0; i < index; i++)
            current = current.next;

        E old = current.element;
        current.element = e;
        return old;
    }

    @Override
    public E get(int index) {
        checkIndex(index);
        Node<E> current = this.head;
        for (int i = 0; i < index; i++)
            current = current.next;

        return current.element;
    }

    @Override
    public void clear() {
        size = 0;
        head = tail = null;
    }

    @Override
    public Iterator<E> iterator() {
        return new MyItr();
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("[ ");
        Node<E> current = this.head;
        for (int i = 0; i < size - 1; i++) {
            result.append(current.element);
            result.append(", ");
            current = current.next;
        }
        result.append(current.element);

        return result.toString() + " ]";
    }

    private class MyItr implements Iterator<E> {

        private int currentIndex = 0;
        private Node<E> next;
        private Node<E> lastReturn;

        private int expectedSizeCount = size;

        public MyItr() {
            next = MyLinkedList.this.head;
        }

        @Override
        public boolean hasNext() {
            return currentIndex < size;
        }

        @Override
        public E next() {
            checkForComodification();
            if (currentIndex >= size)
                throw new NoSuchElementException();

            lastReturn = next;
            next = next.next;
            currentIndex++;

            return lastReturn.element;
        }

        @Override
        public void remove() {
            if (lastReturn == null)
                throw new IllegalStateException();

            checkForComodification();
            MyLinkedList.this.remove(lastReturn.element);
            lastReturn = null;
            currentIndex--;
            expectedSizeCount = size;
        }

        private void checkForComodification() {
            if (expectedSizeCount != size)
                throw new ConcurrentModificationException();
        }
    }

    private static class Node<E> {
        E element;
        Node<E> next;

        public Node(E element) {
            this.element = element;
        }
    }

    public static void main(String[] args) {
        MyLinkedList<String> list1 = new MyLinkedList<>();
        list1.addFirst("private");
        list1.addFirst("public");
        list1.addFirst("pro");
        System.out.println("addFirst : " + list1);

        MyLinkedList<String> list2 = new MyLinkedList<>();
        list2.addLast("public");
        list2.addLast("static");
        list2.addLast("void");
        System.out.println("addLast : " + list2);

        String first = list2.getFirst();
        System.out.println("list2.getFirst : " + first);
        String last = list2.getLast();
        System.out.println("list2.getLast : " + last);

        list1.removeFirst();
        System.out.println("list1.removeFirst : " + list1);
        list1.removeLast();
        System.out.println("list1.removeLast : " + list1);

        list1.add(0, "String");
        list1.add(0, "Integer");
        list1.add(1, "Double");
        list1.add(1, "Float");
        list1.addFirst("String");
        System.out.println("list1.add(0, E) : " + list1);

        System.out.println("list1.contains(\"abc\") : " + list1.contains("abc"));

        System.out.println("list1.indexOf(\"String\") : " + list1.indexOf("String"));

        System.out.println("list1.lastIndexOf(\"String\") : " + list1.lastIndexOf("String"));

        System.out.println(list1);
        list1.set(5, "private");
        System.out.println("list1.set(5, \"private\") : " + list1);

        System.out.println("list1.get(3) : " + list1.get(3));

        System.out.println("--------list1.iterator---------");
        Iterator<String> iterator = list1.iterator();
        while (iterator.hasNext()) {
            String next = iterator.next();
            if (next.equals("Double")) iterator.remove();
            System.out.println(next);
        }

        System.out.println(list1);

    }
}
