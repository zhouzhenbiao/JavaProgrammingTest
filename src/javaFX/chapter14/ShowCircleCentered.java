package javaFX.chapter14;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class ShowCircleCentered extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        //创建了一个面板，把UI组件放入
        Pane pane = new Pane();
        pane.setPadding(new Insets(20, 20, 20, 20));
        //创建一个UI组件，最基本的组成
        Circle circle = new Circle();
        //属性绑定(bind)
//        DoubleProperty centerXProperty = circle.centerXProperty();
//        DoubleProperty centerYProperty = circle.centerYProperty();
//        ReadOnlyDoubleProperty widthProperty = pane.widthProperty();
//        ReadOnlyDoubleProperty heightProperty = pane.heightProperty();
//        centerXProperty.bind(widthProperty.divide(2));
//        centerYProperty.bind(heightProperty.divide(2));
        circle.centerXProperty().bind(pane.widthProperty().divide(2));
        circle.centerYProperty().bind(pane.heightProperty().divide(2));
        circle.setStyle("-fx-border-width: 5px");

        circle.setRadius(100);
//        circle.setStroke(Color.BLACK);
//        circle.setFill(Color.GREEN);
        circle.setStyle("-fx-stroke: lightpink; -fx-fill: aquamarine");

        //把组件放入面板中
        pane.getChildren().add(circle);

        //创建一个场景，放入面板
        Scene scene = new Scene(pane, 200, 200);
        primaryStage.setTitle("ShowCircleCentered");
        //用主舞台把场景放入其中
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
