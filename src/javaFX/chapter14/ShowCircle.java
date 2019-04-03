package javaFX.chapter14;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class ShowCircle extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        //创建一个圆和设置圆的属性（圆的位置，圆的半径，圆的笔画颜色，圆的填充颜色）
        Circle circle = new Circle();
        circle.setCenterX(100);
        circle.setCenterY(100);
        circle.setRadius(50);
        circle.setStroke(Color.BLACK);
        circle.setFill(Color.WHITE);

        //创建一个面板，将UI组件放入
        Pane pane = new Pane();
        pane.getChildren().add(circle);

        //创建一个场景，将面板放入
        Scene scene = new Scene(pane, 200, 200);

        primaryStage.setTitle("Show Circle");
        //创建一个舞台，将场景放入舞台，这里用的是 主舞台 (primaryStage)
        primaryStage.setScene(scene);
        primaryStage.show();

    }
}
