package javaFX.chapter15;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * 实现了鼠标事件，
 */
public class Demo15_8_2 extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Pane pane = new Pane();
        Text text = new Text();
        pane.getChildren().add(text);
        Scene scene = new Scene(pane, 300, 200);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Demo15_8_2");
        primaryStage.show();

        pane.setOnMousePressed(event -> {
            double x = event.getSceneX();
            double y = event.getSceneY();
            text.setX(x);
            text.setY(y - 4);
            text.setText("(" + x + ", " + y + ")");
        });
        pane.setOnMouseReleased(event -> text.setText(""));
    }
}