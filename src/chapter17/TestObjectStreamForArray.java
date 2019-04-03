package chapter17;

import java.io.*;

public class TestObjectStreamForArray {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        int[] numbers = {1, 2, 3, 4, 5};
        String[] strings = {"John", "Susan", "Kim"};
        File file = new File("D:/temp.dat");
        try (
                ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(file))
        ) {
            output.writeObject(numbers);
            output.writeObject(strings);
        }
        int[] newNumbers;
        String[] newStrings;
        try (
                ObjectInputStream input = new ObjectInputStream(new FileInputStream(file))
        ) {
            newNumbers = (int[]) input.readObject();
            newStrings = (String[]) input.readObject();
        }
        for (int i : newNumbers) {
            System.out.println(i);
        }
        for (String string : newStrings) {
            System.out.println(string);
        }
    }
}
