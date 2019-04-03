package javaFX.chapter15;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * 动画：TimeLine(new KeyFrame(Duration, ActionEvent))
 * 实现了类似于线程类，每隔多少秒 执行一次actionEvent
 */
public class TimeLineDemo extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        StackPane pane = new StackPane();
        Text text = new Text("Java Programing is fun");
        text.setFill(Color.PINK);
        pane.getChildren().add(text);

        EventHandler<ActionEvent> eventHandler = event -> {
            if (text.getText().length() != 0)
                text.setText("");
            else
                text.setText("Java Programing is fun");
        };

        Timeline animation = new Timeline(
                //每隔五毫秒就执行一次这个ActionEvent，类似于线程类
                new KeyFrame(Duration.millis(1000), eventHandler));
        animation.setCycleCount(Timeline.INDEFINITE);
        animation.play();

        text.setOnMouseClicked(event -> {
            if (animation.getStatus() == Animation.Status.PAUSED)
                animation.play();
            else
                animation.pause();
        });
        Scene scene = new Scene(pane, 250, 200);
        primaryStage.setScene(scene);
        primaryStage.setTitle("TimeLineDemo");
        primaryStage.show();
    }
}
