package chapter12;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Demo12_11 {
    //总的大纲，读一个文件，重新写入一个新的文件同时替换字符，然后删除source，rename target
    // 1 ->
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入需替换单词：");
        String word = scanner.next();
        System.out.println("请输入文件名：");
        String fileName = scanner.next();
        scanner.close();
        File source = new File("D:\\ruajian\\IDEA\\" + fileName);
        if (!source.exists()) {
            System.out.println("文件不存在" + fileName);
            System.exit(1);
        }

        StringBuilder sb = new StringBuilder();
        StringBuilder brCount = new StringBuilder("");
        try {
            try (
                    Scanner input = new Scanner(source)
            ) {
                while (input.hasNext()) {
                    String s1 = input.next();
//                    System.out.println(s1);
                    switch (s1.charAt(s1.length() - 1)) {
                        case ';':
                            sb.append(s1).append("\r\n").append(brCount);
                            break;
                        case '{':
                            brCount.append("\t");
                            sb.append(s1).append("\r\n").append(brCount);
                            break;
                        case '}':
                            brCount.deleteCharAt(brCount.length() - 1);
                            sb.append(s1).append("\r\n").append(brCount);
                            break;
                        case ':':
                            sb.append(s1).append("\r\n").append(brCount);
                            break;
                        default:
                            sb.append(s1.replaceAll(word, "Integer")).append(" ");
                            break;
                    }
                }
                PrintWriter printWriter = new PrintWriter(source);
                System.out.println(sb.toString());
                printWriter.print(sb.toString());
                printWriter.close();
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
