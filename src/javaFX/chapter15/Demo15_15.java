package javaFX.chapter15;

import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.util.Iterator;

public class Demo15_15 extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Pane pane = new Pane();

        Scene scene = new Scene(pane, 350, 200);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Demo15_15");
        primaryStage.show();

        pane.setOnMouseClicked(event -> {
            double sceneX = event.getSceneX();
            double sceneY = event.getSceneY();
            if (event.getButton() == MouseButton.PRIMARY) {
                Circle circle = new Circle(sceneX, sceneY, 10);
                circle.setStroke(Color.BLACK);
                circle.setFill(Color.WHITE);
                pane.getChildren().add(circle);
            } else if (event.getButton() == MouseButton.SECONDARY) {
                Iterator<Node> iterator = pane.getChildren().iterator();
                while (iterator.hasNext()) {
                    if (iterator.next().contains(sceneX, sceneY)) {

                        iterator.remove();
                        break;
                    }
                }
            }
        });
    }
}
