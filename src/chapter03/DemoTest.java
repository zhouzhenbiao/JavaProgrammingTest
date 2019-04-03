package chapter03;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class DemoTest {
    public static void main(String[] args) {//http://cs.armstrong.edu/liang  https://yongdanielliang.github.io
        System.out.print("Enter a URL: ");
        String URLString = new Scanner(System.in).nextLine();
        try {
            URL url = new URL(URLString);
            System.out.println(url.getHost());
            String urlPath = url.getPath();
            if (urlPath.equals("")) {
                System.out.println("空字符串");
            }
            if (urlPath == null) {
                System.out.println("null值");
            }
            Scanner input = new Scanner(url.openStream());
            while (input.hasNext()) {
                String nextLine = input.nextLine();
                System.out.println(nextLine);
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
            System.out.println("不正确的网址");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    //对于不是简单类型的数据类型，声明一个数组变量时并不给数组分配任何内存空间。他只是创建一个对数组的引用的存储位置。
    //new 关键字才为非基本数据类型的数据类型开辟内存空间。声明只是存储了对数组的引用而已

    //不同于基本数据类型变量的声明，声明数组变量并不会给数组分配任何空间。数组变量不是基本数据类型。数组变量包含的是对数组的引用

    /**
     * 传递共享信息
     *
     * @param targerArray
     * @return int[]
     */
    public static int[] printArray(int[] targerArray) {
        for (int i = 0; i < targerArray.length - 1; i++) {
            targerArray[i] = targerArray[i] + 10;
        }
        return targerArray;
    }

    //要点：1、只能给方法中指定一个可变长数组2、当且仅当只存在一个可变长数组时，
    // 这个可变长数组必须是最后一个参数，任何常规参数都必须在它之前
    public static void printLength(Integer... numbers) {
        System.out.println("当前长度" + numbers.length);
        for (int targer : numbers) {
            System.out.printf("%4d", targer);
        }

    }

}