package chapter21;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

public class Demo21_6 {
    public static void main(String[] args) {
        int[] number = new int[50000];
        for (int i = 0; i < number.length; i++) {
            int temp = (int) (Math.random() * 100 + 1);
            number[i] = temp;
        }

        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < number.length; i++) {
            if (map.containsKey(number[i])) {
                Integer value = map.get(number[i]);
                value++;
                map.put(number[i], value);
            } else
                map.put(number[i], 1);
        }
        System.out.println(map.toString());
        long time = System.currentTimeMillis();
        Collection<Integer> values = map.values();
        int max = map.get(map.keySet().iterator().next());
        Iterator<Integer> iterator = values.iterator();
        while (iterator.hasNext()) {
            Integer next = iterator.next();
            if (next > max) {
                max = next;
            }
        }
        Iterator<Integer> keyIterator = map.keySet().iterator();
        while (keyIterator.hasNext()) {
            Integer next = keyIterator.next();
            if (map.get(next) == max) {
                System.out.print(next + "  ");
            }
        }

        System.out.println(System.currentTimeMillis() - time);
    }
}
