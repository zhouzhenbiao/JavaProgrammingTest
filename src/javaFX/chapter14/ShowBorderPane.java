package javaFX.chapter14;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class ShowBorderPane extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        BorderPane borderPane = new BorderPane();

        borderPane.setTop(new CustomPane("Top"));
        borderPane.setBottom(new CustomPane("Bottom"));
        borderPane.setLeft(new CustomPane("Left"));
        borderPane.setRight(new CustomPane("Right"));
        borderPane.setCenter(new CustomPane("Center"));
        System.out.println("borderPane.getLayoutX() : " + borderPane.getLayoutX());
        System.out.println("borderPane.getLayoutY() : " + borderPane.getLayoutY());
        System.out.println("borderPane.getPrefWidth() : " + borderPane.getPrefWidth());
        System.out.println("borderPane.getPrefHeight() : " + borderPane.getPrefHeight());
//        ColumnConstraints
//        RowConstraints
        Scene scene = new Scene(borderPane);

        primaryStage.setTitle("ShowBorderPane");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    class CustomPane extends StackPane {

        public CustomPane(String title) {
            getChildren().add(new Label(title));
            setPadding(new Insets(11.5, 12.4, 13.5, 14.5));
            setStyle("-fx-background-color: darksalmon; -fx-border-color: lightgray;");
        }
    }
}
