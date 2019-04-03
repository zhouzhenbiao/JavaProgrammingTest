package javaFX.chapter15;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

/**
 * 在面板移动小球，并提供向左、向右、向上、向下移动小球的方法，进行边界检查以防止球完全移到视线之外
 */
public class Demo15_3 extends Application {
    public final double radius = 20;
    private double x = radius, y = radius;

    @Override
    public void start(Stage primaryStage) throws Exception {
        Pane pane = new Pane();
        HBox hBox = new HBox();
        BorderPane borderPane = new BorderPane();
//        borderPane.setPadding(new Insets(10));
        Circle circle = new Circle(x, y, radius);
        circle.setFill(Color.GREEN);
        circle.setStroke(Color.BLACK);

        pane.getChildren().add(circle);
        borderPane.setCenter(pane);

        Button bt_left = new Button("Left");
        Button bt_right = new Button("Right");
        Button bt_up = new Button("Up");
        Button bt_down = new Button("Down");
        hBox.getChildren().addAll(bt_left, bt_right, bt_up, bt_down);
        hBox.setAlignment(Pos.CENTER);
        hBox.setPadding(new Insets(0, 0, 10, 0));
        hBox.setSpacing(8);
        borderPane.setBottom(hBox);

        primaryStage.setScene(new Scene(borderPane, 350, 200));
        primaryStage.setTitle("Demo15_3");
        primaryStage.show();
        primaryStage.setResizable(false);

        bt_left.setOnAction(event ->
                circle.setCenterX(circle.getCenterX() <= circle.getRadius() ?
                        circle.getCenterX() : circle.getCenterX() - 10));
        bt_right.setOnAction(event ->
                circle.setCenterX(circle.getCenterX() >= pane.getWidth() - circle.getRadius() ?
                        circle.getCenterX() : circle.getCenterX() + 10));
        bt_up.setOnAction(event ->
                circle.setCenterY(circle.getCenterY() <= circle.getRadius() ?
                        circle.getCenterY() : circle.getCenterY() - 10));
        bt_down.setOnAction(event -> circle.setCenterY(
                circle.getCenterY() > pane.getHeight() - circle.getRadius() ?
                        circle.getCenterY() : circle.getCenterY() + 10));
    }
}
