package chapter22;

import java.util.ArrayList;
import java.util.Scanner;

public class EfficientPrimeNumbers {
    /**
     * 核心在于假如一个数不是素数，那么这个数的最小因子一定是素数且小于等于Math.sqrt(number);
     * @param args
     */
    public static void main(String[] args) {
        //需求，想要得到 数字 n 以内的 所有素数
        System.out.println("本案例会得到数字 n 以内的 所有素数");
        System.out.print("请输入 数字 n：");
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();

        //用来装素数
        ArrayList<Integer> list = new ArrayList<>();
        int number = 2;
        int squareRoot = 1;

        long time = System.currentTimeMillis();
        while (number <= n) {
            boolean isPrime = true; //默认是素数

            //最小因子 <= Math.sqrt(number)根号number
            if (Math.pow(squareRoot, 2) < number)
                squareRoot++;

            //验证最小素数2，不用验证，对应的 ArrayList数组size == 0，根号素数3，squareRoot = 2，
            //所以也验证了list里面的list.get(0) == 2;，但是验证到了4，4之前有2..3..两个素数
            for (int k = 0; k < list.size() && list.get(k) <= squareRoot; k++) {
                //对于任意的一个递增的数，只需验证是不是 % 素数为零，是，即不是素数，不然就是素数
                if (number % list.get(k) == 0) {
                    isPrime = false;
                    break;
                }
            }

            if (isPrime)
                list.add(number);
            number++;
        }

        System.out.println(System.currentTimeMillis() - time);

        time = System.currentTimeMillis();
        int count = 0;
        for (Integer integer : list) {
            count++;
            System.out.printf("%7d", integer);
            if (count % 10 == 0) {
                System.out.println();
            }
        }
        System.out.println(System.currentTimeMillis() - time);
    }
}