package javaFX.chapter15;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

/**
 * 显示一个圆以及它的外接矩形，实现了当窗体改变大小时，圆和矩形自动改变大小
 */
public class ResizeableCircleRectangle extends Application {

    private Circle circle = new Circle(60);
    private Rectangle rectangle = new Rectangle(120, 120);
    private StackPane stackPane = new StackPane();

    @Override
    public void start(Stage primaryStage) {
        circle.setStroke(Color.BLACK);
        circle.setFill(Color.WHITE);
        rectangle.setStroke(Color.BLACK);
        rectangle.setFill(Color.WHITE);

        stackPane.getChildren().addAll(rectangle, circle);

        Scene scene = new Scene(stackPane, 140, 140);

        primaryStage.setScene(scene);
        primaryStage.setTitle("ResizeableCircleRectangle");
        primaryStage.show();

        stackPane.widthProperty().addListener(observable -> resize());
        stackPane.heightProperty().addListener(observable -> resize());
    }

    private void resize() {
        double length = Math.min(stackPane.getHeight(), stackPane.getWidth());
        circle.setRadius(length / 2 - 15);
        rectangle.setWidth(length - 30);
        rectangle.setHeight(length - 30);
    }
}
