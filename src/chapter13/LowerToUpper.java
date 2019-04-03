package chapter13;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class LowerToUpper {
    /**
     * 1、将输入的小写字符换成大写，然后保存到 “t.txt” 中。
     */

    public static void main(String[] args) {

        System.out.println("请输入字符串");
        Scanner input = new Scanner(System.in);
        String str = input.nextLine();

        //这里调用了方法，
        char[] upper = LowerToUpper.lower2Upper(str);

        try {
            //每次运行之后，要记得在 工作路径 下删除一次 “t.txt” 文件。
            PrintWriter printWriter = new PrintWriter("t.txt");

            //new String(upper),把字符串作为构造函数的入参。
            printWriter.print(new String(upper));

            //重点！！！ 所有的io文件都要记得关闭资源，既调用 .close(); 方法
            printWriter.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static char[] lower2Upper(String str) {
        //在控制台输入的 String 字符串用一个 .toCharArray() 方法就能把字符串变成 字符数组 ，
        // 然后把每个字符 拎 出来用比较是不是在 “a - z” 内。
        char[] charArray = str.toCharArray();
        for (int i = 0; i < charArray.length; i++) {
            if (charArray[i] >= 'a' && charArray[i] <= 'z') {
                //如果在这个范围内，ASCII码减小 32 数据类型变成 int 类型后强制转换成 char 类型后重新赋值
                charArray[i] = (char) (charArray[i] - 32);
            }
        }
        return charArray;
    }
}
