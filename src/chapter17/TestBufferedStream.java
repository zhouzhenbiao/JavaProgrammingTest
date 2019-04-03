package chapter17;

import java.io.*;

public class TestBufferedStream {
    public static void main(String[] args) throws IOException {
//        try (
//                BufferedInputStream input = new BufferedInputStream(new FileInputStream("D:\\yingyong\\LOL压缩包\\LOL_V4.1.2.4_FULL.7z.001"))
//        ) {
//            for (int i = 0; i < 3; i++) {
//                System.out.println(input.read());
//            }
//        }
        File file = new File("D:/win10教育版激活链接.txt");
        System.out.println(file.exists());

        try (
                FileOutputStream fileOutputStream = new FileOutputStream(file, true);
                PrintWriter printWriter = new PrintWriter(fileOutputStream);
        ) {
            printWriter.println("用于测试！！");
            printWriter.close();
        }
    }

}
