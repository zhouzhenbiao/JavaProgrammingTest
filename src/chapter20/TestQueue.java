package chapter20;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.concurrent.PriorityBlockingQueue;

public class TestQueue {
    public static void main(String[] args) {
        Queue<String> queue = new LinkedList<>();
        queue.offer("Java");
        queue.offer("PHP");
        queue.offer("HTML");
        queue.offer("CSS");
        while (!queue.isEmpty()) {
            System.out.println(queue.remove());
        }
//        PriorityQueue
    }
}
