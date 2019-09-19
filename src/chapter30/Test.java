package chapter30;

public class Test implements Runnable {
//        t.start();

    public Test() throws InterruptedException {
        Thread t = new Thread(this);
            t.sleep(100);
    }

    @Override
    public synchronized void run() {
        System.out.println("test");
    }

    public static void main(String[] args) throws InterruptedException {
        new Test();
    }
}
