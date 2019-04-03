package javaFX.chapter15;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * 两个可移动的顶点以及他们间的距离
 */
public class Demo15_16 extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Pane pane = new Pane();
        Circle circle1 = new Circle(40, 40, 10);
        circle1.setStroke(Color.BLACK);
        circle1.setFill(Color.WHITE);
        Circle circle2 = new Circle(160, 110, 10);
        circle2.setStroke(Color.BLACK);
        circle2.setFill(Color.WHITE);
        Line line = new Line();
        line.setStroke(Color.BLACK);
        line.setStartX(circle1.getCenterX());
        line.setStartY(circle1.getCenterY());
        line.setEndX(circle2.getCenterX());
        line.setEndY(circle2.getCenterY());

        pane.getChildren().add(line);
        pane.getChildren().addAll(circle1, circle2);
        Text text = new Text();
        text.setStroke(Color.BLACK);
        text.setFill(Color.WHITE);
        pane.getChildren().add(text);

        Scene scene = new Scene(pane, 250, 150);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Demo15_16");
        primaryStage.show();

        circle1.setOnMouseDragged(event -> {
            double differX = event.getSceneX() - circle1.getCenterX();
            double differY = event.getSceneY() - circle1.getCenterY();
            double newX = circle1.getCenterX() + differX;
            double newY = circle1.getCenterY() + differY;
            circle1.setCenterX(newX);
            circle1.setCenterY(newY);
            line.setStartX(newX);
            line.setStartY(newY);
            double centerX = (line.getStartX() + line.getEndX()) / 2;
            double centerY = (line.getStartY() + line.getEndY()) / 2;
            text.setX(centerX);
            text.setY(centerY - 4);
            text.setText(String.format("%.2f", Math.sqrt(Math.pow(Math.abs(line.getEndX() - line.getStartX()), 2)
                    + Math.pow(Math.abs(line.getEndY() - line.getStartY()), 2))));
        });

        circle2.setOnMouseDragged(event -> {
            double differX = event.getSceneX() - circle2.getCenterX();
            double differY = event.getSceneY() - circle2.getCenterY();
            double newX = circle2.getCenterX() + differX;
            double newY = circle2.getCenterY() + differY;
            circle2.setCenterX(newX);
            circle2.setCenterY(newY);
            line.setEndX(newX);
            line.setEndY(newY);
            double centerX = (line.getStartX() + line.getEndX()) / 2;
            double centerY = (line.getStartY() + line.getEndY()) / 2;
            text.setX(centerX);
            text.setY(centerY - 4);
            text.setText(String.format("%.2f", Math.sqrt(Math.pow(Math.abs(line.getEndX() - line.getStartX()), 2)
                    + Math.pow(Math.abs(line.getEndY() - line.getStartY()), 2))));
        });


    }
}