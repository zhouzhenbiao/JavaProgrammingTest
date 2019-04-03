package chapter18;

import java.util.Scanner;

/**
 * 用递归实现了输入一个整数，拆开每个数字 正序和反序 输出
 * eg：（12345 ：（反序）54321 ： （正序）12345）
 * 递归实现
 */
public class Demo18_8 {
    //用递归求最大公约数
    public static void main(String[] args) {
        //Java中整数除以整数，只取整数，舍去小数部分

        //怎么解决这个问题
        //当 value 为个位数时，是本身
        //十位数时，就是 除以 10
        //百位数 就是除以100
        //千位数 就是除以 1000

        System.out.print("输入一个整数 : ");
        Scanner input = new Scanner(System.in);
        int number = input.nextInt();
        display(number);
        reverseDisplay(number);
    }
    //正序输出 eg : 12345 (12345)
    private static void display(int value) {
        if (value >= 0 && value < 10)
            System.out.println(value);
        else {
            display(value / 10);
            System.out.println(value % 10);
        }
    }

    //反序输出 eg : 12345 (54321)
    private static void reverseDisplay(int value) {
        if (value != 0) {
            System.out.print(value % 10);
            reverseDisplay(value / 10);
        }
    }
}
