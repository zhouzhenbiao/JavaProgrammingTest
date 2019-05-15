package chapter30;

public class TaskThreadDemo {

    public static void main(String[] args) {
        PrintChar printA = new PrintChar('a', 1000);
        PrintChar printB = new PrintChar('b', 1000);
        PrintNum print100 = new PrintNum(1000);

        Thread threadA = new Thread(printA);
        Thread threadB = new Thread(printB);
        Thread thread100 = new Thread(print100);

        //调用 start() 方法是启动一个线程
        thread100.start();
        threadA.start();
        threadB.start();

        //调用 run() 方法是执行一个方法
//        threadA.run();
//        threadB.run();
//        thread100.run();
    }

    private static class PrintChar implements Runnable {

        private char chatToPrint;
        private int times;

        public PrintChar(char c, int t) {
            this.chatToPrint = c;
            this.times = t;
        }

        @Override
        public void run() {
            for (int i = 0; i < times; i++) {
                System.out.print("  " + chatToPrint);
            }
        }
    }

    private static class PrintNum implements Runnable {

        private int lastNum;

        public PrintNum(int num) {
            this.lastNum = num;
        }

        @Override
        public void run() {
            Thread thread4 = new Thread(new PrintChar('c', 400));
            thread4.start();

            for (int i = 0; i < lastNum; i++) {
                System.out.print("  " + i);
                if (i == 50) try {
                    thread4.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
    }
}
