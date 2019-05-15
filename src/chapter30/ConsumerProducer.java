package chapter30;

import java.util.LinkedList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 生产者和消费者，Lock lock = new ReentrantLock();lock 实现的
 * 两种情况，rend 操作 Condition notEmpty 当 notEmpty 为空时 notEmpty.await ， 不空时则成功读出一个并 notFull.signal
 *          write 操作 Condition notFull 当 notFull 满了时 notFull await ，不满则成功写入一个并 notEmpty.signal
 */
public class ConsumerProducer {

    private static Buffer buffer = new Buffer();

    public static void main(String[] args) {
        ExecutorService threadPool = Executors.newFixedThreadPool(2);
        threadPool.execute(new ConsumerTask());
        threadPool.execute(new ProducerTask());
        threadPool.shutdown();
    }

    private static class ProducerTask implements Runnable {

        @Override
        public void run() {
            try {
                int i = 0;
                while (true) {
                    System.out.println("Producer writes " + i);
                    buffer.write(i++);
                    Thread.sleep(1000L);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static class ConsumerTask implements Runnable {
        @Override
        public void run() {
            try {
                while (true) {
                    System.out.println("\t\t\tConsumer reads " + buffer.read());
                    Thread.sleep(1000L);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static class Buffer {
        private LinkedList<Integer> list = new LinkedList<>();
        private static final int CAPACITY = 1;

        //create a lock
        private static Lock lock = new ReentrantLock();
        //create two condition
        private static Condition notEmpty = lock.newCondition();
        private static Condition notFull = lock.newCondition();

        /**
         * 写数据，就是判断 list.size == CAPACITY ? 满了就 await 被 read 的 signalAll 通知 ，
         * 没满就 write 同时 用write 的 signalAll 通知 read 的 await
         *
         * @param value
         */
        public void write(int value) {
            lock.lock();
            try {
                while (list.size() == CAPACITY) {
                    System.out.println("Wait fro notFull condition");
                    notFull.await();
                }

                list.offer(value);
                notEmpty.signal();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }

        public int read() {
            int value = 0;
            lock.lock();
            try {
                while (list.isEmpty()) {
                    System.out.println("\t\t\tWait for notEmpty condition");
                    notEmpty.await();
                }
                value = list.remove();
                notFull.signal();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
            return value;
        }
    }
}
