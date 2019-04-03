package chapter20;

import java.util.ArrayList;

public class TestCollection {
    public static void main(String[] args) {
        ArrayList<String> collection1 = new ArrayList<>();
        collection1.add("suzhichao");
        collection1.add("zhouzhenbiao");
        collection1.add("raojiangwei");
        collection1.add("chenhuihui");
        System.out.println("A list of name in collection1");
        System.out.println(collection1);
        System.out.println("地址： " + System.identityHashCode(collection1));

        System.out.println("\nIs chenhuihui in collection1: " + collection1.contains("chenhuihui"));
        System.out.println(collection1.remove("chenhuihui"));

        System.out.println("\n" + collection1.size() + " collection1 size");

        ArrayList<String> collection2 = new ArrayList<>();
        collection2.add("dengjingjing");
        collection2.add("caixubin");
        collection2.add("wangnan");
        collection2.add("raojiangwei");

        System.out.println("\nA list of name in collection2");
        System.out.println(collection2);
        System.out.println("地址： " + System.identityHashCode(collection2));

        ArrayList<String> clone1 = (ArrayList<String>) collection1.clone();
        clone1.addAll(collection2);
        System.out.println("\nnames in collection1 or collection2 并集: ");
        System.out.println(clone1.toString() + collection1);
        System.out.println("地址： " + System.identityHashCode(clone1));


        clone1 = (ArrayList<String>) collection1.clone();
        clone1.retainAll(collection2);
        System.out.println("\nnames in collection1 and collection2 交集： ");
        System.out.println(clone1);
        System.out.println("地址： " + System.identityHashCode(clone1));

        clone1 = (ArrayList<String>) collection1.clone();
        clone1.removeAll(collection2);
        System.out.println("\nnames in collection1, bu ont in collection2 差集: ");
        System.out.println(clone1);
        System.out.println("地址： " + System.identityHashCode(clone1));
    }
}
