package javaFX.chapter15;

import javafx.animation.FadeTransition;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * //只在它是本身的颜色上改变结点的透明度
 */
public class FadeTransitionDemo extends Application {

    @Override
    public void start(Stage primaryStage) {
        Pane pane = new Pane();

        Ellipse ellipse = new Ellipse(10, 10, 100, 50);
        ellipse.setStroke(Color.BLACK);
        ellipse.setFill(Color.RED);
        ellipse.centerXProperty().bind(pane.widthProperty().divide(2));
        ellipse.centerYProperty().bind(pane.heightProperty().divide(2));
        ellipse.radiusXProperty().bind(pane.widthProperty().multiply(0.4));
        ellipse.centerYProperty().bind(pane.heightProperty().multiply(0.4));

        pane.getChildren().add(ellipse);

        FadeTransition ft = new FadeTransition();
        ft.setCycleCount(Timeline.INDEFINITE);
        ft.setDuration(Duration.seconds(5));
        ft.setAutoReverse(false);
        ft.setNode(ellipse);
        ft.setFromValue(1.0);
        ft.setToValue(0.1);
        ft.setByValue(0.1);

        ft.play();

        ellipse.setOnMousePressed(event -> ft.pause());
        ellipse.setOnMouseReleased(event -> ft.play());

        Scene scene = new Scene(pane, 200, 150);
        primaryStage.setTitle("Fade渐变色动画");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
