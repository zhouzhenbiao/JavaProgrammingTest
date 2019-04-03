package chapter18;

import java.util.Scanner;

public class TowerOfHanoi {
    public static void main(String[] args) {
        System.out.print("Enter number Of disks : ");
        Scanner input = new Scanner(System.in);
        //有 N 个盘子
        int n = input.nextInt();

        TowerOfHanoi.moveDisks(n, 'A', 'B', 'C');
    }

    public static void moveDisks(int n, char fromTower, char toTower, char auxTower) {
        if (n == 1)
            System.out.println("把第 " + n + " 个磁盘从 " + fromTower + " 塔移向 " + toTower + " 塔");
        else {
            TowerOfHanoi.moveDisks(n - 1, fromTower, auxTower, toTower);
            System.out.println("把第 " + n + " 个磁盘从 " + fromTower + " 塔移向 " + toTower + " 塔");
            TowerOfHanoi.moveDisks(n - 1, auxTower, toTower, fromTower);
        }
    }
}
