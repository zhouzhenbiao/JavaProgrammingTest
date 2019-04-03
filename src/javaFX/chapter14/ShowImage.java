package javaFX.chapter14;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class ShowImage extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Pane pane = new HBox(10);
        pane.setPadding(new Insets(5, 5, 5, 5));

        Image image = new Image("javaFX/chapter14/image/中国国旗.jpg");
        ImageView imageView1 = new ImageView(image);
//        imageView1.setRotate(90);
        pane.getChildren().add(imageView1);

        ImageView imageView2 = new ImageView(image);
        imageView2.setFitWidth(100);//这个属性是设置图像在javaFX中的的像素大小，宽和长
        imageView2.setFitHeight(100);
        pane.getChildren().add(imageView2);

        ImageView imageView3 = new ImageView(image);
        imageView3.setRotate(90);
        pane.getChildren().add(imageView3);

        Scene scene = new Scene(pane);

        primaryStage.setTitle("Show Image");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
