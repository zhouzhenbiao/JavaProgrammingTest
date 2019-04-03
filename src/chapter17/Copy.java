package chapter17;

import java.io.*;
import java.util.Scanner;

public class Copy {

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter sourceFile : ");
        String sourceFile = scanner.next();
        System.out.print("\nEnter targetFile : ");
        String targetFile = scanner.next();
        if (sourceFile.equals("") || targetFile.equals("") || sourceFile == null || targetFile == null) {
            System.out.println("Usage ： java copy sourceFile targetFile");
            System.exit(1);
        }
        File inputFile = new File("D:/" + sourceFile);
        if (!inputFile.exists()) {
            System.out.println("SourceFile " + sourceFile + " does not exist");
            System.exit(2);
        }
        File outputFile = new File("D:/" + targetFile);
        if (!outputFile.exists()) {
            System.out.println("targetFile " + targetFile + " does not exist");
            System.exit(2);
        }
        try (
                BufferedInputStream input = new BufferedInputStream(new FileInputStream(inputFile));
                BufferedOutputStream output = new BufferedOutputStream(new FileOutputStream(outputFile))
        ) {
            int temp = 0;
            int numberOfBytesCopied = 0;
            while ((temp = input.read()) != -1) {
                output.write(temp);
                numberOfBytesCopied++;
            }
            System.out.println(numberOfBytesCopied + " bytes copied");
        }

    }
}
