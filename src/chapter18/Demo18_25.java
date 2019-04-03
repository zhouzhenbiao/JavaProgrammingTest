package chapter18;

/**
 * 字符串排序，输入一个字符串 eg： （abc）: abc, acb, bac, bca, cab, cba
 */
public class Demo18_25 {
    public static void main(String[] args) {
        String str = "ABCD";
        disPermutation(str);

    }


    private static void disPermutation(String s) {
        disPermutation("", s);
    }

    private static void disPermutation(String s1, String s2) {
        if (s2.length() == 0)
            System.out.println(s1);
        else {
            for (int i = 0; i < s2.length(); i++) {
                disPermutation(s1 + s2.charAt(0), s2.substring(1));
                s2 = s2.substring(1) + s2.charAt(0);
            }
        }
    }
}
