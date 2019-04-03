package chapter18;

import java.io.File;

public class DirectorySize {

    public static void main(String[] args) {
        System.out.println("共 : " + getSize(new File("D:\\ruajian")) + " 字节");
    }

    //用递归计算一个目录/文件的大小
    public static long getSize(File file) {
        long size = 0;
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            for (String s : file.list()) {
                System.out.println(s);
            }
            for (File f : files) {
                size += getSize(f);
            }
        } else {
            size += file.length();
        }
        return size;
    }
}
