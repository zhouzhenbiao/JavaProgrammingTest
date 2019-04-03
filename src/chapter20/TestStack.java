package chapter20;

import java.util.AbstractQueue;
import java.util.Queue;
import java.util.Stack;

public class TestStack {
    public static void main(String[] args) {
        char ch = 'A';
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(ch);
        stringBuilder.append(ch);
        stringBuilder.append("123");
        System.out.println(stringBuilder.length());
        System.out.println(stringBuilder.toString());
        System.out.println(stringBuilder.indexOf("123"));
    }
}
