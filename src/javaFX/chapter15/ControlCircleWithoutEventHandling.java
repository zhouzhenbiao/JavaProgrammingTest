package javaFX.chapter15;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class ControlCircleWithoutEventHandling extends Application {

    private CirclePane circlePane = new CirclePane();
    @Override
    public void start(Stage primaryStage) throws Exception {
        BorderPane borderPane = new BorderPane();

        borderPane.setCenter(circlePane);

        HBox hBox = new HBox();
        hBox.setSpacing(10);
        hBox.setAlignment(Pos.CENTER);
        Button btEnlarge = new Button("enlarge");
        Button btShrink = new Button("shrink");
        btEnlarge.setOnAction((ActionEvent e) -> circlePane.enlarge());
        btShrink.setOnAction((ActionEvent e) -> circlePane.shrink());
        hBox.getChildren().addAll(btEnlarge, btShrink);

        borderPane.setBottom(hBox);
        borderPane.setAlignment(hBox, Pos.CENTER);

        Scene scene = new Scene(borderPane, 200, 150);

        primaryStage.setTitle("ControlCircle");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public class CirclePane extends StackPane {
        private Circle circle = new Circle(50);

        public CirclePane() {
            circle.setStroke(Color.BLACK);
            circle.setFill(Color.WHITE);
            getChildren().add(circle);
        }

        public void enlarge() {
            circle.setRadius(circle.getRadius() + 2);
        }

        public void shrink() {
            circle.setRadius(circle.getRadius() > 2 ? circle.getRadius() - 2 : circle.getRadius());
        }
    }

}