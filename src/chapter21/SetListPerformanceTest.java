package chapter21;

import java.util.*;

public class SetListPerformanceTest {
    private static final int N = 50000;

    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            list.add(i);
        }
        Collections.shuffle(list);

        HashSet<Integer> hashSet = new HashSet<>(list);
        System.out.println("HashSet contains time : " + getContainsTime(hashSet) + " milliseconds");
        System.out.println("HashSet remove time : " + getRemoveTime(hashSet) + " milliseconds");

        LinkedHashSet<Integer> linkedHashSet = new LinkedHashSet<>(list);
        System.out.println("LinkedHashSet contains time : " + getContainsTime(linkedHashSet) + "milliseconds");
        System.out.println("LinkedHashSet remove time : " + getRemoveTime(linkedHashSet) + " milliseconds");

        TreeSet<Integer> treeSet = new TreeSet<>(list);
        System.out.println("TreeSet contains time : " + getContainsTime(treeSet) + " milliseconds");
        System.out.println("TreeSet remove time : " + getRemoveTime(treeSet) + " milliseconds");

        ArrayList<Integer> arrayList = new ArrayList<>(list);
        System.out.println("ArrayList contains time : " + getContainsTime(arrayList) + " milliseconds");
        System.out.println("ArrayList remove time : " + getRemoveTime(arrayList) + " milliseconds");

        LinkedList<Integer> linkedList = new LinkedList<>(list);
        System.out.println("LinkedList contains time : " + getContainsTime(linkedList) + " milliseconds");
        System.out.println("LinkedList remove time : " + getRemoveTime(linkedList) + " milliseconds");

    }

    private static long getContainsTime(Collection<Integer> c) {
        long time = System.currentTimeMillis();
        for (int i = 0; i < N; i++) {
            c.contains((int) (Math.random() * N * 2));
        }
        return System.currentTimeMillis() - time;
    }

    private static long getRemoveTime(Collection<Integer> c) {
        long time = System.currentTimeMillis();
        for (int i = 0; i < N; i++) {
            c.remove(i);
        }
        return System.currentTimeMillis() - time;
    }
}
