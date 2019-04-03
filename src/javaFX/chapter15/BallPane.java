package javaFX.chapter15;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.DoubleProperty;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

public class BallPane extends Pane {

    public final double radius = 20;
    private double x = radius, y = radius;
    private double dx = 0.8, dy = 0.8;
    private Circle circle = new Circle(x, y, radius);
    private Timeline animation;

    public  BallPane() {
        circle.setFill(Color.GREEN);
        this.getChildren().add(circle);

        animation = new Timeline(new KeyFrame(Duration.millis(40), event -> moveBall()));
        animation.setCycleCount(Timeline.INDEFINITE);
        animation.play();
    }

    //向耦合类提供本身属性的方法
    public void increaseSpeed() {
        animation.setRate(animation.getRate() + 0.1);
    }

    //向耦合类提供本身属性的方法
    public void decreaseSpeed() {
        animation.setRate(animation.getRate() - 0.1);
    }

    //向耦合类提供本身属性的方法
    public DoubleProperty rateProperty() {
        return animation.rateProperty();
    }

    //向耦合类提供本身属性的方法
    public void play() {
        animation.play();
    }

    //向耦合类提供本身属性的方法
    public void pause() {
        animation.pause();
    }

    protected void moveBall() {
        if (x < radius || x > getWidth() - radius)
            dx *= -1;
        if (y < radius || y > getHeight() - radius)
            dy *= -1;
        x += dx;
        y += dy;
        circle.setCenterX(x);
        circle.setCenterY(y);
    }
}
