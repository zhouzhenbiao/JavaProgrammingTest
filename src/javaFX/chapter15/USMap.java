package javaFX.chapter15;

import javafx.application.Application;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * 提供一个程序，对美国地图进行显示、着色和改变大小
 * 美国地图各个州的GPS取自https://liveexample.pearsoncmg.com/data/usmap.txt
 */
public class USMap extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        MapPane mapPane = new MapPane();

        Scene scene = new Scene(mapPane, 1200, 800);
        primaryStage.setScene(scene);
        primaryStage.setTitle("USMap");
        primaryStage.show();

        mapPane.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.UP)
                mapPane.enlarge();
            else if (event.getCode() == KeyCode.DOWN)
                mapPane.shrink();
        });

        mapPane.requestFocus();
    }

    private class MapPane extends BorderPane {
        private Group group = new Group();

        public MapPane() {
            ArrayList<ArrayList<Point2D>> points = getPoints();
            for (ArrayList<Point2D> point2DArrayList : points) {
                Polygon polygon = new Polygon();
                for (Point2D point2D : point2DArrayList) {
                    polygon.getPoints().addAll(point2D.getX() * 14, point2D.getY() * -1.4);
                    polygon.setStroke(Color.BLACK);
                    polygon.setFill(Color.WHITE);
                    polygon.setStrokeWidth(1 / 14.0);
                    polygon.setOnMouseClicked(event -> {
                        if (event.getButton() == MouseButton.PRIMARY)
                            polygon.setFill(Color.PINK);
                        else if (event.getButton() == MouseButton.SECONDARY)
                            polygon.setFill(Color.GOLD);
                        else
                            polygon.setFill(Color.WHITE);
                    });
                }
                group.getChildren().add(polygon);
            }

            //多边形被放大了14倍
            group.setScaleY(14);
            group.setScaleY(14);
            this.setCenter(group);
        }

        //变大
        public void enlarge() {
            group.setScaleX(group.getScaleX() * 1.1);
            group.setScaleY(group.getScaleY() * 1.1);
        }

        //变小
        public void shrink() {
            group.setScaleX(group.getScaleX() * 0.9);
            group.setScaleY(group.getScaleY() * 0.9);
        }
    }

    //得到所有的GPS点，ArrayList<ArrayList<Potin2D>> Point2D的点形式
    private static ArrayList<ArrayList<Point2D>> getPoints() {
        ArrayList<ArrayList<Point2D>> points = new ArrayList<>();
        try {
            try (
                    //src/javaFX/chapter15/usmap.txt == true
                    // javaFX/chapter15/usmap.txt == false
                    Scanner input = new Scanner(
                            new FileInputStream("src/javaFX/chapter15/resource/usmap.txt"))
            ) {
                while (input.hasNext()) {
                    String s = input.nextLine().trim();
//                    System.out.println(s);
                    if (Character.isAlphabetic(s.charAt(0))) {
                        points.add(new ArrayList<>());
                    } else {
                        String[] ss = s.split("\\s+");
                        double y = Double.parseDouble(ss[0]);
                        double x = Double.parseDouble(ss[1]);
                        points.get(points.size() - 1).add(new Point2D(x, y));
                        System.out.println(x + " : " + y);
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return points;
    }
}