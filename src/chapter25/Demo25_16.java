package chapter25;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

// 从外部读取一个文件，实现HuffmanCode
public class Demo25_16 {

    public static void main(String[] args) {
        try (
                Scanner input = new Scanner(System.in)
        ) {
            String filename = input.nextLine();
            if (null == filename || filename.equals("")) {
                System.out.println("文件名为空");
            } else {
                System.out.println(filename);
//                System.exit(1);
                HuffmanCode.printHuffmanCodeList(getText(filename));
            }
        }
    }

    public static String getText(String filename) {
        if (null == filename || filename.equals("")) return null;
        try {
            StringBuilder str = new StringBuilder();
            try (
                    FileInputStream inputStream = new FileInputStream(filename)
            ) {
                int i = 0;
                while ((i = inputStream.read()) != -1)
                    str.append((char) i);
                return str.toString();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "文件读取错误";
    }
}
