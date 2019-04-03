package javaFX.chapter14;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class MyJavaFX extends Application {
    @Override
    public void start(Stage primaryStage) {

        Button btOK = new Button("OK");

        //Scene : 场景； 构造方法可以用 (node, width, height) 创建
        // node 在 Scene (场景) 中表演，
        Scene scene = new Scene(btOK, 200, 250);

        primaryStage.setTitle("MyJavaFX");

        //将 Scene (场景) 放入 Stage (舞台) 中，因为 Stage (舞台) 支持场景的平台；
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    //命令行运行， main 方法不是必需的方法。
    //不支持 javaFX 的 IDE 运行才需要 main 方法。去调用 .launch() 方法！
    public static void main(String[] args) {
        launch(args);
    }
}
