package chapter17;

import java.io.*;

public class Demo17_10 {

    public static void main(String[] args) throws IOException {
        String fileName = "D:/IMG_0635.JPG";
        String newFileName = "D:/Copy01.JPG";
        File inFile = new File(fileName);
        File outFile = new File(newFileName);
        //
        try (
                BufferedInputStream input = new BufferedInputStream(new FileInputStream(inFile));
                BufferedOutputStream output = new BufferedOutputStream(new FileOutputStream(outFile))
        ) {
            int temp = 0;
            int count = 0;
            while ((temp = input.read()) != -1) {
                count++;
                output.write(temp);
            }

            System.out.println("共 " + count + " 个字节");
        }
    }
}
