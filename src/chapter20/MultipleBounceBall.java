package chapter20;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class MultipleBounceBall extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        BorderPane borderPane = new BorderPane();
        HBox hBox = new HBox();
        hBox.setSpacing(10);
        hBox.setPadding(new Insets(7));
        hBox.setAlignment(Pos.CENTER);
        Button btAdd = new Button(" + ");
        Button btSubtract = new Button(" - ");
        hBox.getChildren().addAll(btAdd, btSubtract);

        MultipleBouncePane ballPane = new MultipleBouncePane();
        ballPane.setStyle("-fx-border-color: yellow;");
        borderPane.setCenter(ballPane);
        borderPane.setBottom(hBox);

        Scene scene = new Scene(borderPane, 250, 150);
        primaryStage.setScene(scene);
        primaryStage.setTitle("MultipleBounceBall");
        primaryStage.show();

        scene.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.UP) {
                ballPane.increaseSpeed();
                System.out.println("up");
            } else if (event.getCode() == KeyCode.DOWN) {
                ballPane.decreaseSpeed();
                System.out.println("down");
            }
        });
        ballPane.setOnMousePressed(event -> ballPane.pause());
        ballPane.setOnMouseReleased(event -> ballPane.play());

        btAdd.setOnAction(event -> ballPane.add());
        btSubtract.setOnAction(event -> ballPane.subtract());


    }

    private class MultipleBouncePane extends Pane {
        private Timeline animation;

        public MultipleBouncePane() {
            animation = new Timeline(new KeyFrame(Duration.millis(50), event -> moveBall()));
            animation.setCycleCount(Timeline.INDEFINITE);
            animation.play();
        }

        //添加一个球
        public void add() {
            Color color = new Color(Math.random(), Math.random(), Math.random(), 0.5);
            this.getChildren().add(new Ball(30, 30, 20, color));
        }

        //增加球的移动速度
        public void increaseSpeed() {
            animation.setRate(animation.getRate() + 0.1);
        }

        //降低球的移动速度
        public void decreaseSpeed() {
            animation.setRate(animation.getRate() > 0 ? animation.getRate() - 0.1 : 0);
        }

        //暂停球的移动
        public void pause() {
            animation.pause();
        }

        //开始球的移动
        public void play() {
            animation.play();
        }

        public void subtract() {
            if (this.getChildren().size() > 0) {
                this.getChildren().remove(0);
            }
        }

        public void moveBall() {
            for (Node node : this.getChildren()) {
                Ball ball = (Ball) node;
                double x = ball.getCenterX(), y = ball.getCenterY(), radius = ball.getRadius();
                if (x < radius || x > this.getWidth() - radius)
                    ball.dx *= -1;
                if (y < radius || y > getHeight() - radius)
                    ball.dy *= -1;
                x += ball.dx;
                y += ball.dy;
                ball.setCenterX(x);
                ball.setCenterY(y);
            }
        }
    }

    private class Ball extends Circle {
        private double dx = 1, dy = 1;

        public Ball(double x, double y, double radius, Color color) {
            super(x, y, radius);
            this.setFill(color);
        }
    }
}
