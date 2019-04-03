package chapter17;

import java.io.*;

public class TestDataStream {
    public static void main(String[] args) throws IOException {
        //DataOutputStream 实现了 DataOutput 接口，并继承了FilterOutputStream
        /**
         * 基本实现流程就是--input
         * 把 字节 从 OutputStream中 写入/读出，创建对象时依赖 output/input Stream
         */
        try (
                FileOutputStream fileOutputStream = new FileOutputStream("D:/temp.dat");
                DataOutputStream output = new DataOutputStream(fileOutputStream)
        ) {
            output.writeUTF("John");
            output.writeDouble(85.5);
            output.writeUTF("Jim");
            output.writeDouble(185.5);
            output.writeUTF("George");
            output.writeDouble(105.25);
        }

        try {
            try (
                    FileInputStream fileInputStream = new FileInputStream("D:/temp.dat");
                    DataInputStream input = new DataInputStream(fileInputStream)
            ) {
                System.out.println(input.readUTF() + " : " + input.readDouble());
                System.out.println(input.readUTF() + " : " + input.readDouble());
                System.out.println(input.readUTF() + " : " + input.readDouble());
                //判断是否读到末尾看是不是会抛出异常
                System.out.println(input.readUTF());
            }
        } catch (EOFException e) {
            System.out.println("All data were read");
        }
    }

}
