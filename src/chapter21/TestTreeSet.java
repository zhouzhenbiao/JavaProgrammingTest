package chapter21;

import java.util.HashSet;
import java.util.TreeSet;

public class TestTreeSet {
    public static void main(String[] args) {
        HashSet<String> hashSet = new HashSet<>();
        hashSet.add("London");
        hashSet.add("Paris");
        hashSet.add("New York");
        hashSet.add("San Francisco");
        hashSet.add("Beijing");
        hashSet.add("New York");

        TreeSet<String> treeSet = new TreeSet<>(hashSet);
        System.out.println("Sorted tree set : " + treeSet);

        //Use the methods in SortedSet interface
        System.out.println("first(): " + treeSet.first());
        System.out.println("last(): " + treeSet.last());
        System.out.println("headSet(\"New York\"): " + treeSet.headSet("New York"));
        System.out.println("tailSet(\"New York\"): " + treeSet.tailSet("New York"));

        //Use the methods in NavigableSet interface
        System.out.println(treeSet.comparator());

    }
}
