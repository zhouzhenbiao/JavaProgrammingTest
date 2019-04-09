package chapter20;

import java.io.File;
import java.util.LinkedList;

public class Demo20_18 {
    public static void main(String[] args) {

        File file = new File("C:/Users/DELL/learnGit");
        if (file.exists()) {
            System.out.println((double) getSize(file) / 1024);
        } else
            System.out.println("jiade lujing");
    }

    public static long getSize(File directory) {
        long size = 0L;
        LinkedList<File> queue = new LinkedList<>();
        for (File file : directory.listFiles()) {
            queue.offer(file);
        }

        while (!queue.isEmpty()) {
            if (queue.peek().isFile()) {
                size += queue.remove().length();
            } else {
                for (File file : queue.remove().listFiles()) {
                    queue.offer(file);
                }
            }
        }

        return size;
    }
}
