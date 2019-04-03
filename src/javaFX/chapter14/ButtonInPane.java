package javaFX.chapter14;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class ButtonInPane extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Button btOK = new Button("OK");
        btOK.setRotate(20);
        //设置按钮的大小   PrefSize 参照尺寸
        btOK.setPrefSize(100, 20);
        StackPane stackPane = new StackPane();

        //getChildren() 方法返回一个 java.collections.ObservableList 的一个实例，
        // ObservableList 类似于 ArrayList ，用于存储一个元素集合。调用 add(e) 将一个元素加入到列表中。
        stackPane.getChildren().add(btOK);
        Scene scene = new Scene(stackPane, 200, 50);

        primaryStage.setTitle("Button in a pane");
        primaryStage.setScene(scene);
        primaryStage.show();
        //防止用户改变舞台大小，调用 stage.setResizable(boolean);
        primaryStage.setResizable(false);
    }
}
