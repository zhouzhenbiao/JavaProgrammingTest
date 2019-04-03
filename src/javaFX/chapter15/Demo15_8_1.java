package javaFX.chapter15;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polyline;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * 实现了简单画画功能
 */
public class Demo15_8_1 extends Application {

    private Polyline polyline = new Polyline();

    @Override
    public void start(Stage primaryStage) throws Exception {
        Pane pane = new Pane();
        Text text = new Text();
        polyline.setStroke(Color.GREEN);
        pane.getChildren().add(polyline);

        pane.getChildren().add(text);
        Scene scene = new Scene(pane, 1200, 800);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Demo15_8_1");
        primaryStage.show();

        pane.setOnMouseDragged(event -> {
            double x = event.getSceneX();
            double y = event.getSceneY();
            text.setX(x);
            text.setY(y - 4);
            text.setText("(" + x + ", " + y + ")");

            polyline.getPoints().addAll(x, y);
        });

        pane.setOnMouseReleased(event -> {
            polyline = new Polyline();
            polyline.setStroke(Color.GREEN);
            pane.getChildren().add(polyline);
        });
    }
}