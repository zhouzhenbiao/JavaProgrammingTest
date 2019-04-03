package javaFX.chapter15;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

/**
 * 使用箭头键画线，所画的线从面板的（100， 100）开始，当按下向右向左向上向下时，相应地向东向西向南向北方向画线
 */
public class Demo15_9 extends Application {
    private double startX = 0;
    private double startY = 0;
    private double endX = 0;
    private double endY = 0;

    @Override
    public void start(Stage primaryStage) throws Exception {
        Pane pane = new Pane();
        pane.setPadding(new Insets(10));
        startX = 100;
        startY = 100;

        Scene scene = new Scene(pane, 300, 200);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Demo15_9");
        primaryStage.show();

        pane.requestFocus();

        pane.setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case LEFT: {
                    endX = startX - 10 >= 0 ? startX - 10 : startX;
                    endY = startY;
                    System.out.println(startX + startY + endX + endY);
                    Line line = new Line(startX, startY, endX, endY);
                    line.setStroke(Color.BLACK);
                    pane.getChildren().add(line);
                    startX = endX;
                    startY = endY;
                }
                break;
                case RIGHT: {
                    endX = startX + 10 >= pane.getWidth() ? startX : startX + 10;
                    endY = startY;
                    System.out.println(startX + startY + endX + endY);
                    Line line = new Line(startX, startY, endX, endY);
                    line.setStroke(Color.BLACK);
                    pane.getChildren().add(line);
                    startX = endX;
                    startY = endY;
                }
                break;
                case UP: {
                    endX = startX;
                    endY = startY - 10 >= 0 ? startY - 10 : startY;
                    System.out.println(startX + startY + endX + endY);
                    Line line = new Line(startX, startY, endX, endY);
                    line.setStroke(Color.BLACK);
                    pane.getChildren().add(line);
                    startX = endX;
                    startY = endY;
                }
                break;
                case DOWN: {
                    endX = startX;
                    endY = startY + 15 >= pane.getHeight() ? startY : startY + 10;
                    System.out.println(startX + startY + endX + endY);
                    Line line = new Line(startX, startY, endX, endY);
                    line.setStroke(Color.BLACK);
                    pane.getChildren().add(line);
                    startX = endX;
                    startY = endY;
                }
                break;
                default: {
                }
            }
        });
    }
}
