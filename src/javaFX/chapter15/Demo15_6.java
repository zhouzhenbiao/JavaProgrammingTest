package javaFX.chapter15;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * 当鼠标单击时，面板上交替显示两个文本"Java is fun" and "Java is powerful"
 */
public class Demo15_6 extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        StackPane pane = new StackPane();
        Text text = new Text();
        pane.getChildren().add(text);
        Scene scene = new Scene(pane, 300, 200);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Demo15_6");
        primaryStage.show();

        pane.setOnMouseClicked(event -> {
            if (text.getText().equals("Java is fun")) {
                text.setText("Java is powerful");
            } else {
                text.setText("Java is fun");
            }
        });
    }
}
