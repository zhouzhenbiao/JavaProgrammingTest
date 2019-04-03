package javaFX.chapter15;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

/**
 * 当单击Rotate按钮时，将一个矩形向右旋转15度
 */
public class Demo15_2 extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        BorderPane borderPane = new BorderPane();

        Rectangle rectangle = new Rectangle(120, 50);
        rectangle.setFill(Color.WHITE);
        rectangle.setStroke(Color.BLACK);

        StackPane stackPane = new StackPane();
        stackPane.getChildren().add(rectangle);
        borderPane.setPadding(new Insets(10, 10, 10, 10));
        borderPane.setCenter(stackPane);

        Button bt_rotate = new Button("Rotate");
        borderPane.setBottom(bt_rotate);
        borderPane.setAlignment(bt_rotate, Pos.CENTER);

        Scene scene = new Scene(borderPane, 300, 200);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Demo15_2");
        primaryStage.show();

        bt_rotate.setOnAction(event -> {
            rectangle.setRotate(rectangle.getRotate() + 15);
        });
    }
}