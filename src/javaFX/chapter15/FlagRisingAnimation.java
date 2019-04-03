package javaFX.chapter15;

import javafx.animation.PathTransition;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * 给出了一个升国旗的动画程序
 */
public class FlagRisingAnimation extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Pane pane = new Pane();
        ImageView imageView = new ImageView(new Image("javaFX/chapter14/image/中国国旗.jpg"));
        Line line = new Line(150, 300, 150, 100);
        line.setStroke(Color.BLACK);

        pane.getChildren().add(line);
        pane.getChildren().add(imageView);

        PathTransition pt = new PathTransition();
        pt.setNode(imageView);
        pt.setPath(line);
        pt.setDuration(Duration.seconds(2));
        pt.setCycleCount(5);

        pt.play();

        Scene scene = new Scene(pane, 300, 400);
        primaryStage.setTitle("FlagRisingAnimation");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
