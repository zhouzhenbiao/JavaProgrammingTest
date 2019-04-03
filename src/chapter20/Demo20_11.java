package chapter20;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;
import java.util.Stack;

public class Demo20_11 {
    public static void main(String[] args) {
        try {
            try (
                    FileInputStream inputStream = new FileInputStream("src/chapter20/Demo20_6.java");
                    Scanner input = new Scanner(inputStream)
            ) {
                StringBuilder stringBuilder = new StringBuilder();
                while (input.hasNext()) {
                    stringBuilder.append(input.next().trim());
                }
                String s = stringBuilder.toString();
                s = s.replaceAll("[0-9a-zA-Z\\s]+", "");
                System.out.println(s);
                System.out.println(isMatch(s));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean isMatch(String s) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char charAt = s.charAt(i);
            if (charAt == '(' || charAt == '[' || charAt == '{') {
                stack.push(charAt);
            } else if (charAt == ')') {
                if (!stack.empty() && stack.peek() == '(')
                    stack.pop();
                else
                    return false;
            } else if (charAt == ']') {
                if (!stack.empty() && stack.peek() == '[')
                    stack.pop();
                else
                    return false;
            } else if (charAt == '}') {
                if (!stack.empty() && stack.peek() == '{')
                    stack.pop();
                else
                    return false;
            } else
                continue;
        }
        if (s.length() != 0 && stack.empty())
            return true;
        else
            return false;
    }
}
