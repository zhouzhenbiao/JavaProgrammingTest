package javaFX.chapter15;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * 使用了 Node.contains(double x, double y)方法 来判断
 */
public class Demo15_13 extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Pane pane = new Pane();
        Rectangle rectangle = new Rectangle(50, 40, 100, 40);
        rectangle.setStroke(Color.BLACK);
        rectangle.setFill(Color.WHITE);
        Text text = new Text();
        pane.getChildren().add(rectangle);
        pane.getChildren().add(text);
        Scene scene = new Scene(pane, 250, 150);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Demo15_13");
        primaryStage.show();

        pane.setOnMouseMoved(event -> {
            double sceneX = event.getSceneX();
            double sceneY = event.getSceneY();
            text.setX(sceneX);
            text.setY(sceneY);
            text.setText(rectangle.contains(sceneX, sceneY) ? "鼠标指针在矩形内" : "鼠标指针在矩形之外");
        });
    }
}
