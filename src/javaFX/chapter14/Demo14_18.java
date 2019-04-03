package javaFX.chapter14;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polyline;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class Demo14_18 extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Pane pane = new Pane();
        pane.setPadding(new Insets(10, 10, 10, 10));

        Line line1 = new Line();
        line1.setStartX(20);
        line1.setStartY(240);
        line1.setEndX(480);
        line1.setEndY(240);
        line1.setStroke(Color.BLACK);
        line1.setFill(Color.BLACK);
        pane.getChildren().add(line1);

        Line line2 = new Line();
        line2.setStartX(250);
        line2.setStartY(20);
        line2.setEndX(250);
        line2.setEndY(280);
        line2.setStroke(Color.BLACK);
        line2.setFill(Color.BLACK);
        pane.getChildren().add(line2);

        Polyline polyline = new Polyline();
        ObservableList<Double> list = polyline.getPoints();
        double scaleFactor = 0.0125;
        for (int i = -120; i < 120; i++) {
            list.add(i + 250.0);
            list.add(240.0 - scaleFactor * i * i);
        }
        pane.getChildren().add(polyline);

        Text text = new Text(370, 60, "y = x^2");
        text.setStroke(Color.BLACK);
//        text.setText("y = x^2");
//        text.setX(370);
//        text.setY(60);
        pane.getChildren().add(text);

        Scene scene = new Scene(pane, 500, 300);

        primaryStage.setTitle("Demo14_18");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    //一个 500 * 300 的场景放入主舞台中。
    // ①--> startWidth = 0, startHeight = 240
    //      endWidth = 480, endHeight = 240
}
