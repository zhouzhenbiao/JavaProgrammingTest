package chapter12;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class DemoFile {

    public static void main(String[] args) throws IOException {

        //dir 是目录（文件夹）       file 是文件（.java .txt 或者没有后缀的文件）
//        File f = new File("D:/DemoFile_2.java");
//
//        System.out.println(f.createNewFile());
//        System.out.println(f.exists());
//
//        //如果文件已经存在，那么文件中的当前内容将会在不与用户确认的情况下被丢弃
//        如果文件不存在则创建一个文件
        PrintWriter printWriter = new PrintWriter("D:/DemoFile_2.java");
//        for (int i = 0; i < 99; i++) {
//            printWriter.print("我来到了这里!");
//        }
//        printWriter.close();
//        Scanner scanner = new Scanner(new File("D:/accountdata.dat"));
//        while (scanner.hasNext()) {
//            System.out.println(scanner.next());
//        }
        //PrintWrite 继承了 Writer ，Writer实现了 Closeable接口！！！！
        File file = new File("D:/Demo6_34.java");
        //创建文件的方法
        file.createNewFile();
        //创建文件夹的方法
        file.mkdir();
        PrintWriter output = new PrintWriter(new FileOutputStream(file, true));
        output.print("我最可爱呢");
        output.close();

        /**
         * file 必须使用方法，才能创建一个不不存在的文件  createNewFile()  mkdir()
         */
//        File file = new File("D:/Demo.txt");
//        System.out.println("是否存在" + file.exists());
//        System.out.println("是否是文件" + file.isFile());
//        System.out.println("是否是目录" + file.isDirectory());
    }
}
