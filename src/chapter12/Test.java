package chapter12;

public class Test {
    public static void main(String[] args) {
        StringBuilder stringBuilder = new StringBuilder("\t\t\t");
        System.out.println(stringBuilder.deleteCharAt(stringBuilder.length() - 1).length());
    }
}
