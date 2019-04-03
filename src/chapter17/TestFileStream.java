package chapter17;

import java.io.*;
import java.util.Arrays;

public class TestFileStream {
    public static void main(String[] args) throws IOException {
        //D:/temp.txt, D:/20190106130720.png
        String s = "D:/20190106130720.png";

        //2233322
        System.out.println(Arrays.toString(s.toCharArray()));
        File file = new File(s);
//        try (
//                FileOutputStream output = new FileOutputStream(file, true)
//        ) {
//            /**
//             * 写入的时候也只写入了一个字节
//             */
//            output.write(342343242);
//        }

        try (
                FileInputStream input = new FileInputStream(file)
        ) {
            int temp = 0;
            int count = 0;
            /**
             * input.read() 方法只截取一个字节的数据，截取前八位，后面多出来的不管！！！
             */
            while ((temp = input.read()) != -1) {
                count++;
                System.out.println(temp);
            }
            System.out.println(count);
//            count = input.read(b);
//            System.out.println(count);
//            System.out.println(Arrays.toString(b));
        }

        try (
                FileInputStream input = new FileInputStream(file)
        ) {
            byte[] b = new byte[63730];
            input.read(b);
            System.out.println(Arrays.toString(b));

        }
//
//
////        try {
////            try (
////                    DataInputStream input = new DataInputStream(new FileInputStream("D:/temp.txt"))
////            ) {
////                while (true)
////                    System.out.println(input.readUnsignedByte());
////            }
////        } catch (EOFException e) {
////            System.out.println("文件已读完！");
////        }
    }
}
