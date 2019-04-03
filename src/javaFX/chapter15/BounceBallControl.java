package javaFX.chapter15;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

public class BounceBallControl extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        //画出了一个球，现在只需对它控制
        BallPane ballPane = new BallPane();
        ballPane.setOnMousePressed(event -> ballPane.pause());
        ballPane.setOnMouseReleased(event -> ballPane.play());
        ballPane.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.UP) {
                ballPane.increaseSpeed();
                System.out.println(ballPane.rateProperty().toString());
            }
            else if (event.getCode() == KeyCode.DOWN)
                ballPane.decreaseSpeed();
        });
        Scene scene = new Scene(ballPane, 250, 150);
        primaryStage.setScene(scene);
        primaryStage.setTitle("BounceBallControl");
        primaryStage.show();
        ballPane.requestFocus();
    }
}
