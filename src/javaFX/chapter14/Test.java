package javaFX.chapter14;

import javafx.application.Application;
import javafx.stage.Stage;

public class Test extends Application {

    public Test() {
        System.out.println("Test constructor is invoked");//第二个输出
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        System.out.println("start method is invoked");
    }

    //先由JVM调用 static 的 main 方法，然后 JVM 为 Test 类自己创造一个实例，并调用 start 方法和生成一个 primaryStage （主舞台）
//    public static void main(String[] args) {
//        for (int i = 0; i < 10; i++) {
//            int temp = ((int) (Math.random() * 10) + 1) % 2;
//            System.out.println(temp);
//        }
//        System.out.println("launch application");//第一个输出
//        launch(args);
//
//    }
}
