package chapter03;

import java.util.Scanner;

public class Demo3_9 {
    public static void main(String[] args) {
        System.out.print("Enter the first 9 digits of an ISBN-10 as integer :");
        Scanner scanner = new Scanner(System.in);
        String nextLine = scanner.nextLine();
        try {
            int parseInt = Integer.parseInt(nextLine);
            boolean flag = nextLine.length() == 9;
            if (flag) {
                int sum = 0;
                int tempInt = 0; //用于存放从 String 转换的 int
                String lastString;
                for (int i = 1; i < 10; i++) {
                    tempInt = Integer.parseInt(nextLine.substring(i - 1, i));
                    sum += tempInt * i;
                }
                int lastNumber = sum % 11;
                switch (lastNumber) {
                    case 10:
                        lastString = "X";
                        break;
                    default:
                        lastString = String.valueOf(lastNumber);
                }
                System.out.println("The ISBN-10 number is " + nextLine + lastString);

            } else {
                System.out.println("您输入的数字长度有误!");
            }
        } catch (NumberFormatException e) {
            System.out.println("您输入的不是数字");
            System.out.println("以下是提示信息");
            e.printStackTrace();
        }

    }
}