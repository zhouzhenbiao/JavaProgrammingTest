package chapter21;

import java.util.LinkedHashSet;

public class TestLinkedHashSet {
    public static void main(String[] args) {
        LinkedHashSet<String> linkedHashSet = new LinkedHashSet<>();

        linkedHashSet.add("London");
        linkedHashSet.add("Paris");
        linkedHashSet.add("New York");
        linkedHashSet.add("San Francisco");
        linkedHashSet.add("Beijing");
        linkedHashSet.add("New York");

        for (String s : linkedHashSet) {
            System.out.print(s.toLowerCase() + " | ");
        }
    }
}
