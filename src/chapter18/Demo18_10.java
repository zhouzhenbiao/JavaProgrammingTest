package chapter18;

import java.util.Scanner;

/**
 * 字符串中某个指定字符出现的次数
 */
public class Demo18_10 {
    public static void main(String[] args) {

        /**
         * 唯一的问题就是不够严谨，没有更好的控制输入 String 的正确性
         */
        //基本情形，str.length == 0
        Scanner input = new Scanner(System.in);
        System.out.print("请输入希望比较的字符串 : ");
        String str = input.nextLine();
        System.out.print("请输入需要统计的字母 : ");
        String a = input.next();
        System.out.println(count(str, a.charAt(0)));
    }

    private static int count(String str, char a) {
        if (str.length() == 0)
            return 0;
        else {
            if (str.charAt(0) == a)
                return 1 + count(str.substring(1, str.length()), a);
            else
                return count(str.substring(1, str.length()), a);
        }
    }
}
