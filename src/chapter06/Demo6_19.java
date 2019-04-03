package chapter06;

import java.util.Scanner;

public class Demo6_19 {


    public static void main(String[] args) {
        System.out.println("请输入三条边");
        Scanner input = new Scanner(System.in);

        double side1 = input.nextInt();
        double side2 = input.nextInt();
        double side3 = input.nextInt();

        System.out.println(area(side1, side2, side3));

    }

    public static boolean isValid(double side1, double side2, double side3) {
        if (side1 + side2 > side3 && side1 + side3 > side2 && side2 + side3 > side1 && side1 - side2 < side3
                && side2 - side3 < side1 && side1 - side3 < side2) {
            return true;
        } else
            return false;

    }

    public static double area(double side1, double side2, double side3) {
        if (isValid(side1, side2, side3)) {

            double s = (side1 + side2 + side3) / 2;
            double area = 0;
            area = Math.sqrt(s * (s - side1) * (s - side2) * (s - side3));
            return area;
        } else {
            System.out.println("qingchongxinshuru   或者 报错");
            return -1;
        }
    }


}
