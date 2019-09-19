package javaFX.chapter15;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Test extends Application{

    private static final int SIZE = 8;

    @Override
    public void start(Stage primaryStage) throws Exception {
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setGridLinesVisible(true);
        gridPane.setStyle("-fx-border-color: gold;");
        Text[][] texts = new Text[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                texts[i][j].setText("          \n          ");
                texts[i][j].setStroke(Color.BLACK);
                texts[i][j].setStyle("-fx-border-color: green;");
                gridPane.add(texts[i][j], j, i);
            }
        }
        Scene scene = new Scene(gridPane);
        primaryStage.setScene(scene);
        primaryStage.setTitle("画格子");
        primaryStage.show();
    }
}
