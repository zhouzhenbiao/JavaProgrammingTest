package javaFX.chapter15;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;

/**
 * 游戏，手眼协调，点击了一个圆会消失，并在面板里随机生成一个圆，20个过后统计总时长
 */
public class Demo15_19 extends Application {
    private boolean flag = true;
    private long time = 0L;
    @Override
    public void start(Stage primaryStage) throws Exception {
        Pane pane = new Pane();
        Scene scene = new Scene(pane, 350, 200);
        //20个圆装进去
        ArrayList<Circle> circles = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            int randomX = (int) (Math.random() * (pane.getWidth() - 20)) + 10;
            int randomY = (int) (Math.random() * (pane.getHeight() - 20)) + 10;
            circles.add(new Circle(randomX, randomY, 10));
        }

        primaryStage.setScene(scene);
        primaryStage.setTitle("Demo15_19单击鼠标开始游戏");
        primaryStage.show();
        pane.setOnMouseClicked(event -> {
            if (pane.getChildren().size() != 0 && flag) {
                if (pane.getChildren().get(0).contains(event.getSceneX(), event.getSceneY())) {
                    pane.getChildren().remove(0);
                    if (circles.size() != 0) {
                        Circle circle = circles.get(circles.size() - 1);
                        circle.setStroke(Color.BLACK);
                        circle.setFill(Color.color(Math.random(), Math.random(), Math.random()));
                        pane.getChildren().add(circle);
                        circles.remove(circles.size() - 1);
                    } else {
                        time = System.currentTimeMillis() - time;
                        Text text = new Text("点击中20个小球共花费 : " + String.format("%.2f", time / 1000.0) + " 秒");
                        text.setStroke(Color.BLACK);
                        text.setFill(Color.WHITE);
                        text.setX(20);
                        text.setY(20);
                        pane.getChildren().add(text);
                        flag = false;
                    }
                }
            } else {
                time = System.currentTimeMillis();
                if (circles.size() != 0) {
                    Circle circle = circles.get(circles.size() - 1);
                    circle.setStroke(Color.BLACK);
                    circle.setFill(Color.color(Math.random(), Math.random(), Math.random()));
                    pane.getChildren().add(circle);
                    circles.remove(circles.size() - 1);
                }
            }
        });
    }
}