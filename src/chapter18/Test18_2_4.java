package chapter18;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Test18_2_4 {

    public static void main(String[] args) {
        System.out.print("欲计算 2`n （2的N次方）\n 请输入 2 的指数 : ");
        Scanner input = new Scanner(System.in);
        try {
            int n = input.nextInt();
            System.out.println(n < 0 ? 1.0 / powerOfTwo(Math.abs(n)) : powerOfTwo(n));

        } catch (InputMismatchException e) {
            System.out.println("输入的不是整数！！！");
            e.printStackTrace();
        }
    }

    //欲计算 2`n （2的N次方）请输入 2 的指数 :
    public static long powerOfTwo(int n) {
        if (n == 0)
            return 1;
        else
            return 2 * powerOfTwo(n - 1);
    }

}
