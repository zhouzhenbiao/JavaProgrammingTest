package chapter18;

import java.math.BigInteger;
import java.util.Scanner;

public class Demo18_1 {

    //factorial 阶乘，用BigInteger类实现
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("输入要计算的阶乘 ：");
        String number = input.nextLine();
        System.out.println(factorial(new BigInteger(number)));

    }
    public static BigInteger factorial(BigInteger bigInteger) {
        if (bigInteger.equals(BigInteger.ZERO)) {
            return BigInteger.ONE;
        } else
            return bigInteger.multiply(factorial(bigInteger.subtract(BigInteger.ONE)));
    }
}
