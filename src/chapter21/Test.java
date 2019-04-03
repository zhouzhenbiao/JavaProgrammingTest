package chapter21;

import java.util.LinkedHashSet;

public class Test {
    public static void main(String[] args) {
        LinkedHashSet<String> set1 = new LinkedHashSet<>();
        set1.add("Beijing");

        LinkedHashSet<String> set2 = set1;
        LinkedHashSet<String> set3 = (LinkedHashSet<String>) set1.clone();
        System.out.println("set1 is : " + set1 + set1.hashCode());
        System.out.println("set2 is : " + set2 + set2.hashCode());
        System.out.println("set3 is : " + set3 + set3.hashCode());
    }
}
