package javaFX.chapter14;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ShowHBoxVBox extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        BorderPane borderPane = new BorderPane();

        borderPane.setTop(getHBox());
        borderPane.setBottom(getVBox());
        borderPane.setStyle("-fx-background-color: chartreuse;");

        Scene scene = new Scene(borderPane);

        primaryStage.setTitle("Show HBox VBox");
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    //HBox 将它的子节点布局在单个水平行中， FlowPane 是分布在一行中且为未排满的同一行中
    private HBox getHBox() {
        HBox hBox = new HBox();
        hBox.setSpacing(20);
        hBox.setPadding(new Insets(15, 15, 15, 15));
        hBox.setStyle("-fx-background-color: gold;");

        hBox.getChildren().addAll(new Button("Computer Science"), new Button("Chemistry"));
        hBox.getChildren().add(new ImageView(new Image("javaFX/chapter14/image/中国国旗.jpg")));

        return hBox;
    }

    private VBox getVBox() {
        VBox vBox = new VBox();
        vBox.setPadding(new Insets(15, 5, 5, 5));
        vBox.setSpacing(20);

        vBox.getChildren().add(new Label("Courses"));

        Label[] courses = {new Label("CSCI 1301"), new Label("CSCI 1302"),
                new Label("CSCI 2410"), new Label("CSCI 3720")};

        for (Label course : courses) {
            vBox.setMargin(course, new Insets(0, 0, 0, 15));
            vBox.getChildren().add(course);
        }

        return vBox;
    }

}
