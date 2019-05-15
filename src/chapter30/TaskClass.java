package chapter30;

public class TaskClass implements Runnable {


    @Override
    public void run() {
        System.out.println("每个任务都是Runnable接口的一个实例，也成为可运行对象（runnable object）");
    }

    public static void main(String[] args) {
        TaskClass task = new TaskClass();

        Thread thread = new Thread(task);
        thread.start();
        System.out.println("main 方法的打印");
    }
}
