package javaFX.chapter15;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * 自己根据 Node in Scene 的 sceneX 和 sceneY 到 centerX 和 centerY 的勾股定理
 */
public class Demo15_12 extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Pane pane = new Pane();
        Circle circle = new Circle(130, 100, 50);
        circle.setStroke(Color.BLACK);
        circle.setFill(null);
        Text text = new Text();
        text.setStroke(Color.BLACK);
        text.setFill(null);
        pane.getChildren().add(circle);
        pane.getChildren().add(text);

        Scene scene = new Scene(pane, 350, 200);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Demo15_12");
        primaryStage.show();

        pane.setOnMouseMoved(event -> {
            double sceneX = event.getSceneX();
            double sceneY = event.getSceneY();
            text.setX(sceneX);
            text.setY(sceneY - 4);
            double x = Math.abs(sceneX - circle.getCenterX());
            double y = Math.abs(sceneY - circle.getCenterY());
            if (Math.pow(x, 2) + Math.pow(y, 2) > Math.pow(circle.getRadius(), 2))
                text.setText("鼠标指针在--圆外");
            else if (Math.pow(x, 2) + Math.pow(y, 2) == Math.pow(circle.getRadius(), 2))
                text.setText("鼠标指针刚刚好在圆的边界上");
            else
                text.setText("鼠标指针在----圆内");
        });
    }
}
