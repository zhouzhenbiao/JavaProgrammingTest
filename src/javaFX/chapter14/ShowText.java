package javaFX.chapter14;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ShowText extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {

        Pane pane = new Pane();
        pane.setPadding(new Insets(10, 10, 10, 10));

        Text text = new Text();
        text.setText("Programming is fun");
        //完成了一个动态属性绑定！
        text.xProperty().bind(pane.widthProperty().divide(4).add(2));
        text.yProperty().bind(pane.heightProperty().divide(2).add(4));
        text.setStyle("-fx-stroke: chartreuse;");

        pane.getChildren().add(text);

        Scene scene = new Scene(pane, 200, 50);

        primaryStage.setTitle("Show Test");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
