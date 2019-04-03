package javaFX.chapter15;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * 输入并显示字符串，从键盘接收一个字符串并把它显示在面板上，回车键表明输入结束
 */
public class Demo15_10 extends Application {

    private boolean flag = true;

    @Override
    public void start(Stage primaryStage) throws Exception {
        StackPane pane = new StackPane();
        Text text = new Text();
        pane.getChildren().add(text);

        Scene scene = new Scene(pane, 350, 200);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Demo15_10");
        primaryStage.show();

        pane.requestFocus();
        pane.setOnKeyPressed(event -> {
            if (flag) {
                if (event.getCode() == KeyCode.ENTER) {
                    text.setText("此次输入为：" + text.getText());
                    flag = false;
                } else {
                    text.setText(text.getText() + event.getText());
                }
            }
        });
    }
}