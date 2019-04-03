package javaFX.chapter14;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class FontDemo extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Circle circle = new Circle();
        circle.setRadius(50);
        circle.setStroke(Color.BLACK);
        circle.setFill(new Color(0.5, 0.5, 0.5, 0.1));

        Label label = new Label("JavaFX");
        Font font = Font.font("Courier", FontWeight.BOLD, 20);
//        label.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.ITALIC, 20));
        label.setFont(font);
        StackPane pane = new StackPane();
        //同一个UI组件不能多次的放入同一个面板或者场景中。
        pane.getChildren().addAll(circle, label);

        Scene scene = new Scene(pane);
        primaryStage.setTitle("FontDemo");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
