package chapter21;

import java.util.*;

public class Test {
    public static void main(String[] args) {
        LinkedHashMap<String, String> map = new LinkedHashMap<>();
        map.put("123", "John Smith");
        map.put("111", "George Smith");
        map.put("123", "Steve Yao");
        map.put("222", "Steve Yao");
        System.out.println("(1): " + map);
        System.out.println("(2): " + new TreeMap<String, String>(map));
        System.out.println("(3): " + map.values());

        int[] numbers1 = {1, 2, 3};
        int[] numbers2 = {2, 1, 3};
        System.out.println(Arrays.equals(numbers1, numbers2));


        LinkedList<Integer> linkedList = new LinkedList<>();
        linkedList.addFirst(1);
        linkedList.addFirst(2);
        linkedList.addFirst(3);

        LinkedList<Integer> linkedList1 = new LinkedList<>();
        linkedList1.add(2);
        linkedList1.add(1);
        linkedList1.add(3);
        System.out.println(linkedList.equals(linkedList1));
    }
}
