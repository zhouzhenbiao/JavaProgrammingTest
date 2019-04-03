package javaFX.chapter14;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class NodeStyleRotateDemo extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Button btOK = new Button("OK");
        btOK.setScaleX(1);//这个是在原来的基础上的乘法放大。
        btOK.setScaleY(1);
        btOK.setStyle("-fx-border-color: blue; -f x-background-color: lightpink");
        btOK.setRotate(-30);
        StackPane pane = new StackPane();
        pane.getChildren().add(btOK);

        //一个面板的旋转会导致面板上所有的节点都跟着旋转。
        pane.setRotate(30);
        pane.setStyle("-fx-background-color: lightgray;");

        Scene scene = new Scene(pane, 200, 250);
        primaryStage.setTitle("NodeStyleRotateDemo");
        //用默认的主舞台放入场景
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
