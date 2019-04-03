package javaFX.chapter14;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.stage.Stage;

public class ShowEllipse extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Scene scene = new Scene(new MyEllipse(), 300, 200);

        primaryStage.setTitle("Show Ellipse");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    class MyEllipse extends Pane {
        private void paint() {
            //实现类似 动态属性绑定的效果？ 第一步，先清空面板里的所有结点
            getChildren().clear();

            for (int i = 0; i < 16; i++) {
                Ellipse ellipse = new Ellipse(getWidth() / 2, getHeight() / 2,
                        getWidth() / 2 - 50, getHeight() / 2 - 50);
                ellipse.setStroke(Color.color(Math.random(), Math.random(), Math.random()));
                ellipse.setFill(Color.WHITE);
                ellipse.setRotate(i * 180 / 16);
                getChildren().add(ellipse);
            }

        }

        @Override
        protected void setWidth(double value) {
            super.setWidth(value);
            //每次更新了 pane 的 width 和 height 同时执行一次 paint 方法
            paint();
        }

        @Override
        protected void setHeight(double value) {
            super.setHeight(value);
            paint();
        }
    }
}



