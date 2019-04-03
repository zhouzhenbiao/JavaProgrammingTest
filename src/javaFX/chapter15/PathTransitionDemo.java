package javaFX.chapter15;

import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * 让一个矩形沿着一个圆的轮廓移动
 */
public class PathTransitionDemo extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Pane pane = new Pane();
        Rectangle rectangle = new Rectangle(0, 0, 25, 50);
        rectangle.setStroke(Color.BLACK);
        rectangle.setFill(Color.PINK);
        Circle circle = new Circle(125, 100, 50);
        circle.setStroke(Color.BLACK);
        circle.setFill(Color.WHITE);

        pane.getChildren().add(circle);
        pane.getChildren().add(rectangle);

        PathTransition pt = new PathTransition();
        //在交替的周期中动画方向需要到转方向
        pt.setAutoReverse(false);
        //cycleCount 循环次数
        pt.setCycleCount(Timeline.INDEFINITE);
        //duration 持续时间
        pt.setDuration(Duration.millis(4000));
        //变换的目标节点
        pt.setNode(rectangle);
        //将圆设置为路径
        pt.setPath(circle);
        //并设置方向为垂直于切线
        pt.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);

        //按下鼠标
        circle.setOnMousePressed(event -> pt.pause());
        //释放鼠标
        circle.setOnMouseReleased(event -> pt.play());

        Scene scene = new Scene(pane, 250, 200);
        primaryStage.setScene(scene);
        primaryStage.setTitle("PathTransitionDemo");
        primaryStage.show();
    }
}
