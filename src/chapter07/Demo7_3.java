package chapter07;

import java.util.Scanner;

public class Demo7_3 {
    public static void main(String[] args) {
        System.out.print("Enter the integers between 1 and 100 : ");
        Scanner input = new Scanner(System.in);
        String nextLine = input.nextLine();

        displayCount(nextLine);
    }

    public static void displayCount(String nextLine) {
        String[] split = nextLine.split("\\s+");
        int[] array = new int[100];
        if (!split[split.length - 1].equals("0")) {
            System.out.println("输入不是以0结束");
            return;
        }

        for (int i = 0; i < split.length - 1; i++) {
            Integer valueOf = Integer.valueOf(split[i]);
            if (valueOf < 1 && valueOf > 100) {
                System.out.println("Please enter the integers between 1 and 100: ");
                return;
            }
            array[valueOf - 1]++;
        }
        for (int i = 0; i < array.length - 1; i++) {
            if (array[i] != 0) {
                System.out.println((i + 1) + " occurs " + array[i] + (array[i] > 1 ? " times" : " time"));
            }
        }
    }
}
