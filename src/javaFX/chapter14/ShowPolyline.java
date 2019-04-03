package javaFX.chapter14;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polyline;
import javafx.stage.Stage;

public class ShowPolyline extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Scene scene = new Scene(new MyPolyline(), 400, 400);

        primaryStage.setTitle("Show Polygon");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    class MyPolyline extends Pane {
        public void paint() {
            Polyline polyline = new Polyline();
            polyline.setFill(Color.WHITE);
            polyline.setStroke(Color.BLACK);
            ObservableList<Double> list = polyline.getPoints();

            double centerX = getWidth() / 2, centerY = getHeight() / 2;
            double radius = Math.min(getWidth(), getHeight()) * 0.4;

            for (int i = 0; i < 6; i++) {
                list.add(centerX + radius * Math.cos(2 * i * Math.PI / 6));
                list.add(centerY - radius * Math.sin(2 * i * Math.PI / 6));
            }

            getChildren().clear();
            getChildren().add(polyline);
        }

        @Override
        protected void setWidth(double value) {
            super.setWidth(value);
            paint();
        }

        @Override
        protected void setHeight(double value) {
            super.setHeight(value);
            paint();
        }
    }
}


