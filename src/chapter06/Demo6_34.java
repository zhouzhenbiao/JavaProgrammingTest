package chapter06;

import java.util.Scanner;

public class Demo6_34 {
    public static void main(String[] args){
        System.out.println("请输入年份：（例如： 2012）");
        Scanner input = new Scanner(System.in);
        int year = input.nextInt();
        System.out.println("请输入月份：（例如： 1）");
        int month = input.nextInt();

        printMonth(year, month);
    }

    public static void printMonth(int year, int month) {
        printMonthTitle(year, month);
        printMonthBody(year, month);
    }

    public static void printMonthTitle(int year, int month) {
        System.out.println("\t\t" + getMonthName(month) + " " + year);
        System.out.println("-----------------------------");
        System.out.println(" Sun Mon Tue Wed Thu Fri Sat");
    }

    public static String getMonthName(int month) {
        String monthName = "";
        switch(month) {
            case 1: monthName = "一月"; break;
            case 2: monthName = "二月"; break;
            case 3: monthName = "三月"; break;
            case 4: monthName = "四月"; break;
            case 5: monthName = "五月"; break;
            case 6: monthName = "六月"; break;
            case 7: monthName = "七月"; break;
            case 8: monthName = "八月"; break;
            case 9: monthName = "九月"; break;
            case 10: monthName = "十月"; break;
            case 11: monthName = "十一月"; break;
            case 12: monthName = "十二月"; break;
            default : monthName = "错误的月份。即输入错误";
        }
        return monthName;
    }

    public static void printMonthBody(int year, int month) {
        int startDay = getStartDay(year, month);
        int numberOfDaysInMonth = getNumberOfDaysInMonth(year, month);
        int i = 0;
        for (i = 0; i < startDay; i++) {
            System.out.print("    ");
        }

        for (i = 1;  i <= numberOfDaysInMonth; i++) {
            System.out.printf("%4d", i);
            if ((i + startDay) % 7 == 0) {
                System.out.println();
            }
        }
        System.out.println();
    }

    public static int getStartDay(int year, int month) {
        final int START_DAY_FOR_JAN_1_1800 = 3;
        int totalNumberOfDays = getTotalNumberOfDays(year, month);
        return (totalNumberOfDays + START_DAY_FOR_JAN_1_1800) % 7;
    }

    public static int getTotalNumberOfDays(int year, int month) {
        int total = 0;
        for (int i = 1800; i < year; i++) {
            total += isLeapYear(i) ? 366 : 365;
        }
        for (int i = 1; i < month; i++) {
            total += getNumberOfDaysInMonth(year, i);
        }
        return total;
    }

    public static int getNumberOfDaysInMonth(int year, int month) {
        if (month == 1 || month == 3 || month == 5 || month == 7 ||
                month == 8 || month == 10 || month == 12) {
            return 31;
        }
        if (month == 4 || month == 6 || month == 9 || month == 11){
            return 30;
        }
        if (month == 2) {
            return isLeapYear(year) ? 29 : 28;
        }

        return 0;//输入错误
    }

    public static Boolean isLeapYear(int year) {
        return year % 400 == 0 || (year % 4 == 0 && year % 100 != 0);
    }

}
