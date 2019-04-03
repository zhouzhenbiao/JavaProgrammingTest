package chapter12;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

//统计每个字母出现的次数
public class Demo12_30 {

    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("d:/Demo6_34.java");
//        System.out.println(file.exists());
//        System.out.println(file.isFile());

        Scanner input = new Scanner(file);
        int[] array = new int[26];

        while (input.hasNext()) {
            char[] charArray = input.next().toUpperCase().toCharArray();
            for (int i = 0; i < charArray.length; i++) {
                int index = 0;
                index = charArray[i] - 65;
                if (index < 26 && index >= 0)
                    array[index]++;
            }
        }

        for (int i = 0; i < array.length; i++) {
            System.out.println("Number of " + (char) (i + 65) + "s : " + array[i]);
        }
//        System.out.println((char) (0 + 56));
    }
}
