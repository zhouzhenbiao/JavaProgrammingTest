package chapter17;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;

public class Test {
    public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
//        int n = -5;
//        for (int i = 31; i >= 0; i--)
//            System.out.print(n >>> i & 1);
        Scanner input = new Scanner(System.in);
        System.out.println("请输入一个日期 : (eg :20080808)");
        String date = input.next();

        String yearStr = date.substring(0, 4);
        String monthStr = date.substring(4, 6);
        String dayStr = date.substring(6, 8);

        int yearInt = Integer.parseInt(yearStr);
        int monthInt = Integer.parseInt(monthStr);
        int dayInt = Integer.parseInt(dayStr);

        int[] month = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
//        if (monthInt > 13) {
//            System.out.println("无效月份");
//        }
//        if (yearInt < 2000 && yearInt > 2100) {
//            System.out.println("无效年份");
//        }
//        if (dayInt < 1 && dayInt > 31) {
//            System.out.println("无效日");
//        }
        /**
         * 修改的第一处
         * 不需要这行代码，因为第95， 96， 97行有判断是不是在范围内的年月日
         */
//        if (yearInt >= 2000 && yearInt <= 2100 || monthInt >= 1 && monthInt <= 12 || dayInt >= 1 && dayInt <= 31) {

        int yearID = 0;
        int monthID = 0;
        int dayID = 0;
        switch (monthInt) {
            case 2:
                monthID = 9;
                break;
            case 12:
                monthID = 10;
                break;
            case 1:
                ;
            case 3:
                ;
            case 5:
                ;
            case 7:
                ;
            case 8:
                ;
            case 10:
                monthID = 8;
                break;
            case 4:
                ;
            case 6:
                ;
            case 9:
                ;
            case 11:
                monthID = 7;
                break;

        }
        if (dayInt <= 26 && 1 <= dayInt) {
            dayID = 1;
        }
        switch (dayInt) {
            case 27:
                dayID = 2;
                break;
            case 28:
                dayID = 3;
                break;
            case 29:
                dayID = 4;
                break;
            case 30:
                dayID = 5;
                break;
            case 31:
                dayID = 6;
                break;
        }

        //当12月份，有2 <= monthInt <= month[2 - 1]
        if (yearInt >= 2000 && yearInt <= 2100) {
            if (monthInt >= 1 && monthInt <= 12) {

                if (dayInt >= 1 && dayInt <= month[monthInt - 1]) {
                    if (yearInt % 4 == 0 && yearInt % 100 != 0 || yearInt % 400 == 0) {
                        System.out.println("是否为闰年 ：Y");
                        yearID = 11;
                        month[1] = 29;
                        if (dayInt + 3 > month[monthInt - 1]) {
                            if (monthInt != 12) {
                                dayInt = dayInt + 3 - month[monthInt - 1];
                                monthInt++;
                            } else {
                                dayInt = dayInt + 3 - month[monthInt - 1];
                                monthInt = 1;
                                yearInt += 1;
                            }
                        } else {
                            dayInt = dayInt + 3;
                        }
                    } else {
                        yearID = 12;
                        System.out.println("是否为闰年 ：N");
                        month[1] = 28;
                        /**
                         * 修改的第二处
                         * 应该和 104-111 行代码一致，103-111行代码解决的是12月31日 + 3天yearInt + 1，就是不是闰年也应该有这个
                         */
                        if (dayInt + 3 > month[monthInt - 1]) {
                            if (monthInt != 12) {
                                dayInt = dayInt + 3 - month[monthInt - 1];
                                monthInt++;
                            } else {
                                dayInt = dayInt + 3 - month[monthInt - 1];
                                monthInt = 1;
                                yearInt += 1;
                            }
                        } else {
                            dayInt = dayInt + 3;
                        }
                    }
                    monthStr = Integer.toString(monthInt);
                    if (monthStr.length() == 1)
                        monthStr = "0" + monthStr;

                    dayStr = Integer.toString(dayInt);
                    if (dayStr.length() == 1)
                        dayStr = "0" + dayStr;

                    System.out.println("覆盖等价类(ID类型)：" + yearID + ", " + monthID + ", " + dayID);
                    System.out.println("输出一个日期: " + yearInt + "-" + monthStr + "-" + dayStr);
                    /**
                     * 修改的第三处
                     * else 后面应该跟上两个大括号，不然只能打印一条 sout，不能打印三条sout
                     */
                } else {
                    System.out.println("无效天数");
                    System.out.println("覆盖等价类（ID类型）：");
                    System.out.println("是否为闰年");
                }
            } else {
                System.out.println("无效月份");
                System.out.println("覆盖等价类（ID类型）：");
                System.out.println("是否为闰年");
            }
        } else {
            System.out.println("无效年份");
            System.out.println("覆盖等价类（ID类型）：");
            System.out.println("是否为闰年：");
        }
//        }
    }

}
