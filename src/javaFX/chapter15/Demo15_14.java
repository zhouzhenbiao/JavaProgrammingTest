package javaFX.chapter15;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Demo15_14 extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Pane pane = new Pane();
        Polygon polygon = new Polygon();
        polygon.setStroke(Color.BLACK);
        //当setFill(null)时，Node.contains只能在边界有效
        polygon.setFill(Color.WHITE);
        polygon.getPoints().addAll(40.0, 20.0, 70.0, 40.0, 60.0, 80.0, 45.0, 45.0, 20.0, 60.0);
        Text text = new Text();
        text.setStroke(Color.BLACK);
        text.setFill(null);
        pane.getChildren().addAll(polygon, text);

        Scene scene = new Scene(pane, 250, 150);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Demo15_14");
        primaryStage.show();

        pane.setOnMouseMoved(event -> {
            double sceneX = event.getSceneX();
            double sceneY = event.getSceneY();
            text.setX(sceneX);
            text.setY(sceneY);
            text.setText(polygon.contains(sceneX, sceneY) ?  "鼠标指针在多边形内" : "鼠标指针在多边形之外");
        });
    }
}
