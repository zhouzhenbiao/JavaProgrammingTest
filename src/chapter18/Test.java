package chapter18;

public class Test {
    static int c = 0;

    public static void main(String[] args) {
        String s = "abc";
//        System.out.println(s.substring(1) + s.charAt(0));
        printAllArray(s);
    }

    private static void printAllArray(String s) {
        printAllArray(s, "");
    }

    private static void printAllArray(String s, String n) {
        if (s.length() == 0) {
            System.out.println(n + " --- " + ++c);
        } else {
            for (int i = 0; i < s.length(); ++i) {
                printAllArray(s.substring(1), n + s.charAt(0));
                s = s.substring(1) + s.charAt(0);
            }
        }
    }
}
