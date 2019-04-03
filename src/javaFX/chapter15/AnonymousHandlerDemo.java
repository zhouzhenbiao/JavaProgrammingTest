package javaFX.chapter15;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class AnonymousHandlerDemo extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        TextField textField = new TextField();
        textField.setAlignment(Pos.BOTTOM_RIGHT);

        Text text = new Text(40, 40, "Programming is fun");
        text.setStroke(Color.GREEN);
        text.setFill(Color.PINK);

        Pane pane = new Pane();
        pane.getChildren().add(text);

        Button btUp = new Button("Up");
        Button btDown = new Button("Down");
        Button btLeft = new Button("Left");
        Button btRight = new Button("Right");

        HBox hBox = new HBox(10);
        hBox.setAlignment(Pos.CENTER);
        hBox.getChildren().addAll(btUp, btDown, btLeft, btRight);

        BorderPane borderPane = new BorderPane();
        borderPane.setTop(textField);
        borderPane.setCenter(pane);
        borderPane.setBottom(hBox);

        //Create and register the handler
        btUp.setOnAction((ActionEvent e) -> text.setY(text.getY() > 10 ? text.getY() - 5 : text.getY()));
        btDown.setOnAction((ActionEvent e) -> text.setY(text.getY() < pane.getHeight() - 10 ? text.getY() + 5 : text.getY()));
        btLeft.setOnAction((ActionEvent e) -> text.setX(text.getX() > 10 ? text.getX() - 5 : text.getX()));
        btRight.setOnAction((ActionEvent e) -> text.setX(text.getX() < pane.getWidth() - 10 ? text.getX() + 5 : text.getX()));

        Scene scene = new Scene(borderPane, 400, 350);

        primaryStage.setTitle("AnonymousHandlerDemo");
        primaryStage.setScene(scene);
        primaryStage.show();

    }
}
