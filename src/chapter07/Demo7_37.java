package chapter07;

import java.util.Arrays;
import java.util.Scanner;

public class Demo7_37 {

    public static void main(String[] args) {
        System.out.print("请输入球的个数： ");
        Scanner input = new Scanner(System.in);
        int count = input.nextInt();
        System.out.print("请输入机器的槽数： ");
        int capacity = input.nextInt();
        System.out.println("------------------------");
        int[] slots = print(count, capacity); //槽中球的个数

        int max = getMax(slots);
        System.out.println("\n" + Arrays.toString(slots) + "  slots.length  " + slots.length + " max " + max + "\n");

        for (int i = max; i > 0; i--) {
            for (int j = 0; j < slots.length; j++) {
                if (slots[j] == i) {
                    slots[j]--;
                    System.out.print("O");
                } else {
                    System.out.print("_");
                }
            }
            System.out.println();
        }
    }

    public static int[] print(int count, int capacity) {
        int[] slots = new int[capacity];
        for (int i = 0; i < count; i++) {

            int countLeft = 0;  //数字 0 代表往左边偏移，既left
            int countRight = 0;  //数字 1 代表往左边偏移，既right

            for (int j = 0; j < capacity - 1; j++) {
                int flag = (int) (Math.random() * 2); //0：代表left 1：代表right
                if (flag == 0) {
                    countLeft--;
                    System.out.print("L");
                } else {
                    countRight++;
                    System.out.print("R");
                }
            }
            System.out.println();
            if (capacity % 2 == 1) {
                int index = capacity / 2; //指针，记录数组solts的下标
                slots[index + (countLeft + countRight) / 2]++;
            } else {
                int index = capacity / 2; //指针，记录数组solts的下标
                if ((countLeft + countRight) < 0) {
                    slots[(countLeft + countRight) / 2 + index - 1]++;
                } else {
                    slots[(countLeft + countRight) / 2 + index]++;
                }
            }
        }
        return slots;
    }

    public static int getMax(int[] slots) {
        int[] target = new int[slots.length];
        System.arraycopy(slots, 0, target, 0, slots.length);
        for (int i = 1; i < slots.length; i++) {
            if (target[i - 1] > target[i]) {
                target[i] = target[i - 1];
            }
        }
        return target[target.length - 1];
    }
}
