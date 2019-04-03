package chapter21;

import java.util.HashSet;

public class TestMethodsInCollection {
    public static void main(String[] args) {
        //create set1
        HashSet<String> set1 = new HashSet<>();
//        LinkedHashSet
        //Add String to set1
        set1.add("London");
        set1.add("Paris");
        set1.add("New York");
        set1.add("San Francisco");
        set1.add("Beijing");

        System.out.println("set1 is: " + set1);
        System.out.println(set1.size() + " elements in set1");

        //Delete a String from set1
        set1.remove("London");
        System.out.println("\nset1 is " + set1);
        System.out.println(set1.size() + " elements in set1");

        //Create set2
        HashSet<String> set2 = new HashSet<>();

        //Add String to set2
        set2.add("London");
        set2.add("Shanghai");
        set2.add("Paris");
        System.out.println("\nset2 is " + set2);
        System.out.println(set2.size() + " elements in set2");

        System.out.println("\nIs Taipei in set2? " + set2.contains("Taipei"));

        //并集
        set1.addAll(set2);
        System.out.println("\n并集After adding set2 to set1, set1 is " + set1);

        //差集
        set1.removeAll(set2);
        System.out.println("差集After removing set2 from set1, set1 is " + set1);

        //交集
        set1.retainAll(set2);
        System.out.println("交集After removing non-common elements is set2 from set1, set1 is " + set1);
    }
}
