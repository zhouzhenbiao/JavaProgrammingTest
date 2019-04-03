package javaFX.chapter14;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class ShowGridPane extends Application {
    /**
     * 最大的问题就是不能特意的去设置每个网格的大小（height and width），可能我还没找到在哪里吧
     * @param primaryStage
     * @throws Exception
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        GridPane gridPane = new GridPane();
//        网格线是否可见，默认 （false）
//        gridPane.setGridLinesVisible(true);
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setPadding(new Insets(11.5, 12.5, 13.5, 14.5));
        gridPane.setHgap(5);
        gridPane.setVgap(5);

        //左边的网格---标签
        gridPane.add(new Label("First Name : "), 0, 0);
        gridPane.add(new Label("MI : "), 0, 1);
        gridPane.add(new Label("Last Name : "), 0, 2);

        //右边的网格--标签
        gridPane.add(new TextField(), 1, 0);
        gridPane.add(new TextField(), 1, 1);
        gridPane.add(new TextField(), 1, 2);
        Button add_name = new Button("Add Name");
        add_name.setPrefHeight(50);
        gridPane.add(add_name, 1, 3);
        gridPane.setHalignment(add_name, HPos.RIGHT);
        gridPane.add(new Button("test"), 1, 4);

        Scene scene = new Scene(gridPane);

        primaryStage.setTitle("ShowGridPane");
        primaryStage.setScene(scene);
        primaryStage.show();



    }
}
