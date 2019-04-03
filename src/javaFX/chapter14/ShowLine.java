package javaFX.chapter14;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

public class ShowLine extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Scene scene = new Scene(getPane(), 200, 200);

        primaryStage.setTitle("Show Line");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private Pane getPane() {
        Pane pane = new Pane();

        Line line1 = new Line();
        line1.setStartX(10);
        line1.endXProperty().bind(pane.widthProperty().subtract(10));
        line1.setStartY(10);
        line1.endYProperty().bind(pane.heightProperty().subtract(10));
        line1.setStyle("-fx-stroke: chartreuse; -fx-stroke-width: 5px;");

        pane.getChildren().add(line1);

        Line line2 = new Line();
        line2.setStartX(10);
        line2.startYProperty().bind(pane.heightProperty().subtract(10));
        line2.endXProperty().bind(pane.widthProperty().subtract(10));
        line2.setEndY(10);
        line2.setStyle("-fx-stroke: red; -fx-stroke-width: 5px;");

        pane.getChildren().add(line2);

        return pane;
    }
}
