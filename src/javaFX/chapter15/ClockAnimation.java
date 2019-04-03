package javaFX.chapter15;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class ClockAnimation extends Application {

    private double hour;
    private double minute;
    private double second;
    private Pane pane = new Pane();
    private Circle circle = new Circle(150, 150, 100);

    private Text text_12 = new Text(137, 70, "12");//高度 15像素
    private Text text_6 = new Text(144, 245, "6");
    private Text text_9 = new Text(54, 157, "9");
    private Text text_3 = new Text(234, 157, "3");

    private Line lineHour = new Line(150, 150, 110, 150);
    private Line lineMinute = new Line(150, 150, 150, 200);
    private Line lineSecond = new Line(150, 150, 150, 75);

    private Text showTime = new Text("");
    private BorderPane borderPane = new BorderPane();

    public ClockAnimation() {
        GregorianCalendar calendar = new GregorianCalendar();
        this.hour = calendar.get(Calendar.HOUR_OF_DAY);
        this.minute = calendar.get(Calendar.MINUTE);
        this.second = calendar.get(Calendar.SECOND);
        initClock();
    }

    public ClockAnimation(double hour, double minute, double second) {
        this.hour = hour;
        this.minute = minute;
        this.second = second;
        initClock();
    }

    public double getHour() {
        return hour;
    }

    public void setHour(double hour) {
        this.hour = hour;
        initClock();
    }

    public double getMinute() {
        return minute;
    }

    public void setMinute(double minute) {
        this.minute = minute;
        initClock();
    }

    public double getSecond() {
        return second;
    }

    public void setSecond(double second) {
        this.second = second;
        initClock();
    }

    @Override
    public void start(Stage primaryStage) {
        //画一个静态的钟，然后用TimeLine 1s 一闪，1min 一闪，1h 一闪，提供一个自动设置时间的功能 下拉列表
        Font font = Font.font("Consolas", 22);

        circle.setFill(Color.WHITE);
        circle.setStroke(Color.BLACK);

        text_12.setFont(font);
        text_12.setFill(Color.BLACK);
        text_6.setFont(font);
        text_6.setFill(Color.BLACK);
        text_9.setFont(font);
        text_9.setFill(Color.BLACK);
        text_3.setFont(font);
        text_3.setFill(Color.BLACK);

        lineHour.setFill(Color.BLACK);
        lineMinute.setFill(Color.BLACK);
        lineSecond.setFill(Color.BLACK);

        showTime.setFill(Color.BLACK);
        showTime.setText((int) getHour() + ": " + (int) getMinute() + ": " + (int) getSecond());

        pane.getChildren().add(circle);
        pane.getChildren().addAll(text_3, text_6, text_9, text_12);
        pane.getChildren().addAll(lineHour, lineMinute, lineSecond);
        borderPane.setTop(pane);        borderPane.setBottom(showTime);
        borderPane.setAlignment(showTime, Pos.CENTER);

        EventHandler<ActionEvent> secondEvent = event -> {
            //一秒动一次
            setSecond((getSecond() + 1) % 60);
            showTime.setText(String.format("%02d : %02d : %02d", (int) getHour(), (int) getMinute(), (int) getSecond()));//(int) getHour() + ": " + (int) getMinute() + ": " + (int) getSecond()
        };
        EventHandler<ActionEvent> minuteEvent = event -> {
            //每二十秒动一次
            setMinute((getMinute() + 20 / 60) % 60);
        };

        EventHandler<ActionEvent> hourEvent = event -> {
            //五分钟动一次
            setHour((getHour() + 5 / 60) % 24);
        };
        Timeline timelineSecond = new Timeline(new KeyFrame(Duration.seconds(1), secondEvent));
        timelineSecond.setCycleCount(Timeline.INDEFINITE);
        timelineSecond.play();

        Timeline timelineMinute = new Timeline(new KeyFrame(Duration.seconds(20), minuteEvent));
        timelineMinute.setCycleCount(Timeline.INDEFINITE);
        timelineMinute.play();

        Timeline timelineHour = new Timeline(new KeyFrame(Duration.minutes(5), hourEvent));
        timelineHour.setCycleCount(Timeline.INDEFINITE);
        timelineHour.play();

        Scene scene = new Scene(borderPane, 300, 300);
        primaryStage.setTitle("ClockAnimation");
        primaryStage.setScene(scene);
        //resizeable 可改变大小的能力
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    //有了具体的时间，开始画画
    private void initClock() {
        //秒针
        double sLength = circle.getRadius() * 0.75;
        double secondX = circle.getCenterX() + sLength * Math.sin(second / 60 * 2 * Math.PI);
        double secondY = circle.getCenterY() - sLength * Math.cos(second / 60 * 2 * Math.PI);
        lineSecond.setEndX(secondX);
        lineSecond.setEndY(secondY);

        //分针
        double mLength = circle.getRadius() * 0.65;
        double minuteX = circle.getCenterX() + mLength * Math.sin((minute + second / 60) / 60 * 2 * Math.PI);
        double minuteY = circle.getCenterY() - mLength * Math.cos((minute + second / 60) / 60 * 2 * Math.PI);
        lineMinute.setEndX(minuteX);
        lineMinute.setEndY(minuteY);

        //时针
        double hLength = circle.getRadius() * 0.5;
        double hourX = circle.getCenterX() + hLength * Math.sin((hour % 12 + minute / 60) / 12 * 2 * Math.PI);
        double hourY = circle.getCenterY() - hLength * Math.cos((hour % 12 + minute / 60) / 12 * 2 * Math.PI);
        lineHour.setEndX(hourX);
        lineHour.setEndY(hourY);
    }
}
