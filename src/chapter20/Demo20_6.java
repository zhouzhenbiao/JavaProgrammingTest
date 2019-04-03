package chapter20;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * 在链表上使用遍历器，数据规模500w
 */
public class Demo20_6 {
    public static void main(String[] args) {
        LinkedList<Integer> linkedList = new LinkedList<>();
        int i = 0;
        while (i != 100000) {
            linkedList.addFirst(i);
            i++;
        }
        System.out.println("开始iterator了");
        System.out.println("遍历器时间： " + getIteratorTime(linkedList));
        System.out.println("开始get了");
        System.out.println("get(i)时间： " + getGetTime(linkedList));
    }

    public static long getIteratorTime(LinkedList<Integer> linkedList) {
        long time = System.currentTimeMillis();
        Iterator<Integer> iterator = linkedList.iterator();
        while (iterator.hasNext()) {
            iterator.next();
        }
        time = System.currentTimeMillis() - time;
        return time;
    }

    public static long getGetTime(LinkedList<Integer> linkedList) {
        long time = System.currentTimeMillis();
        for (Integer integer : linkedList) {
            integer = integer + 1;
        }
        time = System.currentTimeMillis() - time;
        return time;
    }
}
