package javaFX.chapter15;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class HandleEvent extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        HBox hBox = new HBox(10);
        hBox.setAlignment(Pos.CENTER);

        Button bt_ok = new Button("OK");
        Button bt_cancel = new Button("Cancel");
        OKHandlerClass handler1 = new OKHandlerClass();
        bt_ok.setOnAction(handler1);
        CancelHandlerClass handler2 = new CancelHandlerClass();
        bt_cancel.setOnAction(handler2);
        bt_ok.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

            }
        });
        hBox.getChildren().addAll(bt_ok, bt_cancel);

        Scene scene = new Scene(hBox, 200, 200);

        primaryStage.setTitle("Handle Event");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public class OKHandlerClass implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent event) {
            System.out.println("OK button clicked");
        }
    }

    class CancelHandlerClass implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent event) {
            System.out.println("Cancel button clicked");
        }
    }
}




