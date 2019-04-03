package chapter06;

import java.util.Scanner;

public class Demo6_18 {
    public static void main(String[] args) {
        System.out.println("密码规则如下：");
        System.out.println(" • 密码必须至少8为字符。");
        System.out.println(" • 密码仅能包含字母和数字。");
        System.out.println(" • 密码必须包含至少两个数字。");
        Scanner input = new Scanner(System.in);
        String nextLine = input.nextLine();

        if (validPassword(nextLine)) {
            System.out.println("合法密码");
        } else {
            System.out.println("不合法密码");
        }
    }

    public static Boolean validPassword(String password) {
        //条件一：至少8位字符
        if (password.length() < 8) {
            System.out.println("条件一");
            return false;
        }
        int count = 0;
        for (int i = 0; i < password.length() - 1; i++) {
            char charAt = password.charAt(i);
            //条件二：仅能包含字母和数字
            if (!((charAt >= '0' && charAt <= '9') || (charAt >= 'a' && charAt <= 'z') ||
                    (charAt >= 'A' && charAt <= 'Z'))) {
                System.out.println("条件二");
                return false;
            }
            //条件三：密码必须包含至少两个数字
            if ((charAt >= '0' && charAt <= '9')) {
                count++;
            }
        }

        if (count < 2) {
            System.out.println("条件三");
            return false;
        }
        return true;
    }
}
