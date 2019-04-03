package javaFX.chapter15;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * 实现了鼠标事件(MouseEvent)，setOnMouseDragged() -> 鼠标拖动事件
 *                           setOnMouseClicked() -> 鼠标点击事件
 *                           setOnMouseMoved() -> 鼠标移动事件，有案例，但是还是不懂有什么效果
 *                           setOnMouseExited() -> 鼠标退出时间
 */
public class MouseEventDemo extends Application {

    private static int click = 0;

    @Override
    public void start(Stage primaryStage) throws Exception {
        Text text = new Text(40, 40, "Programming is fun");
        text.setStyle("-fx-fill: gold; -fx-stroke: chartreuse;");
        text.setOnMouseMoved((MouseEvent e) -> text.setText("mouse enter text!" + click));
        text.setOnMouseExited((MouseEvent e) -> text.setText("Programming is fun" + click));
        //鼠标拖动效果
        text.setOnMouseDragged(
                event -> {
                    text.setX(event.getX());
                    text.setY(event.getY());
                });
        text.setOnMouseClicked(
                event -> {
                    click++;
                });
        Pane pane = new Pane(text);

        Scene scene = new Scene(pane, 300, 100);

        primaryStage.setTitle("MouseEventDemo");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
