package javaFX.chapter15;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class KeyEventDemo extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Text text = new Text(120, 40, "A");
        Pane pane = new Pane(text);
        text.setFont(Font.font(16));
        text.setFill(Color.PINK);
//        text.setStyle("-fx-background-color: gold;");
        text.setOnKeyPressed((
                KeyEvent e) -> {
            switch (e.getCode()) {
                case UP:
                    text.setY(text.getY() <= 20 ? text.getY() : text.getY() - 5);
                    break;
                case DOWN:
                    text.setY(pane.getHeight() - text.getY() >= 30 ? text.getY() + 5 : text.getY());
                    break;
                case LEFT:
                    text.setX(text.getX() <= 10 ? text.getX() : text.getX() - 5);
                    break;
                case RIGHT:
                    text.setX(pane.getWidth() - text.getX() >= 40 ? text.getX() + 5 : text.getX());
                    break;
                default:
                    if (e.getText().length() > 0)
                        text.setText(e.getText());
            }
        });
        Scene scene = new Scene(pane, 300, 160);

        primaryStage.setTitle("KeyEventDemo");
        primaryStage.setScene(scene);
        primaryStage.show();

        //只有获得输入焦点的结点才可以接收KeyEvent事件，
        // 在一个Text.requestFocus()使得text可以接收键盘的焦点
        //而且该方法必须在 primaryStage.show() （舞台被显示后调用）
        //并且 scene(场景)是接收键盘事件的顶级容器
        text.requestFocus();
    }
}
