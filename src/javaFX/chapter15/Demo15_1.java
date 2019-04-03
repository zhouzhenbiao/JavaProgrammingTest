package javaFX.chapter15;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Collections;

/**
 * 实现了单击按钮（refresh）以显示随机的三张图片
 */
public class Demo15_1 extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        ImageView[] imageViews = new ImageView[4];
        ArrayList<Image> imagesList;
        HBox hBox = new HBox();
        hBox.setSpacing(10);
        hBox.setPadding(new Insets(10, 10, 10, 10));
        hBox.setAlignment(Pos.CENTER);

        BorderPane borderPane = new BorderPane();
        borderPane.setPadding(new Insets(0, 0, 10, 0));
        borderPane.setCenter(hBox);
        imagesList = getImages();
        for (int i = 0; i < 4; i++) {
            imageViews[i] = new ImageView(imagesList.get(i));
            hBox.getChildren().add(imageViews[i]);
        }

        Button bt_refresh = new Button("refresh");
        borderPane.setBottom(bt_refresh);
        borderPane.setAlignment(bt_refresh, Pos.CENTER);

        Scene scene = new Scene(borderPane);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Demo15_1");
        primaryStage.show();

        bt_refresh.setOnAction(event -> {
            Collections.shuffle(imagesList);
            for (int i = 0; i < 4; i++)
                imageViews[i].setImage(imagesList.get(i));
        });
    }

    private ArrayList<Image> getImages() {//javaFX/chapter15/resource/image/card/1.png
        ArrayList<Image> list = new ArrayList<>();
        for (int i = 0; i < 54; i++) {
            list.add(new Image("javaFX/chapter15/resource/image/card/" + (i + 1) + ".png"));
        }
        return list;
    }
}