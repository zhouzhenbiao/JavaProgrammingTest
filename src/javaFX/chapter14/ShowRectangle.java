package javaFX.chapter14;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ShowRectangle extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {

        Rectangle r1 = new Rectangle(25, 10, 60, 30);
        r1.setStyle("-fx-stroke: black; -fx-fill: white;");

        Rectangle r2 = new Rectangle(25, 50, 60, 30);
        Rectangle r3 = new Rectangle(25, 90, 60, 30);
        r3.setArcWidth(15);
        r3.setArcHeight(25);

        Group group = new Group();
        group.getChildren().addAll(new Text(10, 27, "r1"), r1,
                new Text(10, 67, "r2"), r2, new Text(10, 107, "r3"), r3);

        for (int i = 0; i < 32; i++) {
            Rectangle rectangle = new Rectangle(100, 50, 100, 30);
//            rectangle.setFill(Color.WHITE);
            rectangle.setFill(null);
            rectangle.setStroke(Color.color(Math.random(), Math.random(), Math.random()));
            rectangle.setRotate(i * 180 / 32);
            group.getChildren().add(rectangle);
        }

        group.setScaleX(2);
        group.setScaleY(2);

        Scene scene = new Scene(new StackPane(group), 250, 150);

        primaryStage.setTitle("Show Rectangle");
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.setResizable(false);
    }
}
