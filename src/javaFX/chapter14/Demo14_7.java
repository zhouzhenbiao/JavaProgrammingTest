package javaFX.chapter14;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Demo14_7 extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        //创建一个网格面板
        GridPane gridPane = new GridPane();
//        gridPane.setPadding(new Insets(11, 12, 13, 14));
        //水平间距
//        gridPane.setHgap(5);
        //垂直间距
//        gridPane.setVgap(5);
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                //创建一个文本域
                TextField textField = new TextField();
                int temp = ((int) (Math.random() * 10) + 1) % 2;
                textField.setText(String.valueOf(temp));
                //两层 for 循环把文本域添加到面板中
                gridPane.add(textField, i, j);
            }
        }

        Scene scene = new Scene(gridPane, 230, 230);

        primaryStage.setTitle("Demo14_7");
        primaryStage.setScene(scene);
//        primaryStage.setResizable(false);
        primaryStage.show();
    }
}
