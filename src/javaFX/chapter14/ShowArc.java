package javaFX.chapter14;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * Arc 是一个圆弧
 */
public class ShowArc extends Application{
    @Override
    public void start(Stage primaryStage) throws Exception {
        // 前两个参数是 类似于 圆的中心坐标， 中间两个是 椭圆的 两个半径，后两个，一个是开始角度，持续角度大小，0°为正东方向
        Arc arc1 = new Arc(150, 100, 80, 80, 30, 35);
        arc1.setFill(Color.RED);
        arc1.setType(ArcType.ROUND);

        Arc arc2 = new Arc(150, 100, 80, 80, 30 + 90, 35);
        arc2.setFill(Color.WHITE);
        arc2.setStroke(Color.BLACK);
        arc2.setType(ArcType.OPEN);//圆（椭圆）的一部分，圆弧

        Arc arc3 = new Arc(150, 100, 80, 80, 30 + 180, 35);
        arc3.setType(ArcType.CHORD);
        arc3.setFill(Color.WHITE);
        arc3.setStroke(Color.BLACK);

        Arc arc4 = new Arc(150, 100, 80, 80, 30 + 270, 35);
        arc4.setFill(Color.GREEN);
        arc4.setType(ArcType.CHORD);
        arc4.setStroke(Color.BLACK);

        Group group = new Group();
        group.getChildren().addAll(new Text(210, 40, "arc1: round"), arc1,
                new Text(20, 40, "arc2: open"), arc2,
                new Text(20, 170, "arc3: chord"), arc3,
                new Text(210, 170, "arc4: chord"), arc4);

        Scene scene = new Scene(new StackPane(group), 300, 200);

        primaryStage.setTitle("Show Arc");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
