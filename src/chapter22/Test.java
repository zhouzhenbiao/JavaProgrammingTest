package chapter22;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Test {

    static class Point implements Cloneable {
        private int x;
        private int y;

        public Point() {
            this.x = 0;
            this.y = 0;
        }

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return "Point{" +
                    "x=" + x +
                    ", y=" + y +
                    '}';
        }
    }

    public static Point[] minDistance(Point[] points) {

        Point[] result = new Point[2];

        double dMin = Double.POSITIVE_INFINITY;
        //当点对长度小于5时，暴力求解
        if (points.length < 5) {
            for (int i = 0; i < points.length; i++) {
                for (int j = i + 1; j < points.length; j++) {
                    //求出最小值
                    double tempMin = distance(points[i], points[j]);
                    if (dMin > tempMin) {
                        dMin = tempMin;
                        //存储最近的点对
                        result[0] = points[i];
                        result[1] = points[j];
                    }
                }
            }
            return result;
        }

        //分成两个子集

        int midIndex = (points.length - 1) >> 1;

        ArrayList<Point> leftList = new ArrayList<>();
        ArrayList<Point> rightList = new ArrayList<>();
        for (int i = 0; i < points.length; i++) {
            if (i <= midIndex) {
                leftList.add(points[i]);
            } else {
                rightList.add(points[i]);
            }
        }
        Point[] leftPoints = new Point[leftList.size()];
        Point[] rightPoints = new Point[rightList.size()];
        leftList.toArray(leftPoints);
        rightList.toArray(rightPoints);

        Point[] result1 = minDistance(leftPoints);
        double d1 = distance(result1[0], result1[1]);
        Point[] result2 = minDistance(rightPoints);
        double d2 = distance(result2[0], result2[1]);
        double d;
        if (d1 < d2) {
            result = result1;
            d = d1;
        } else {
            result = result2;
            d = d2;
        }

        ArrayList<Point> stripLiftList = new ArrayList<>();
        ArrayList<Point> stripRightList = new ArrayList<>();
        int midValue = leftPoints[leftPoints.length - 1].x;
        for (Point point : leftPoints) {
            if (midValue - point.x <= d)
                stripLiftList.add(point);
        }
        for (Point point : rightPoints) {
            if (point.x - midValue <= d)
                stripRightList.add(point);
        }
        Point[] stripLift = new Point[stripLiftList.size()];
        stripLiftList.toArray(stripLift);
        Point[] stripRight = new Point[stripRightList.size()];
        stripRightList.toArray(stripRight);
        Arrays.sort(stripLift, (o1, o2) -> {
            if (o1.y < o2.y)
                return -1;
            if (o1.y > o2.y)
                return 1;
            if (o1.x < o2.x)
                return -1;
            if (o1.x > o2.x)
                return 1;
            return 0;
        });
        Arrays.sort(stripRight, (o1, o2) -> {
            if (o1.y < o2.y)
                return -1;
            if (o1.y > o2.y)
                return 1;
            if (o1.x < o2.x)
                return -1;
            if (o1.x > o2.x)
                return 1;
            return 0;
        });

        for (Point leftPoint : stripLift) {
            for (Point rightPoint : stripRight) {
                if (Math.abs(leftPoint.y - rightPoint.y) < d) {
                    double tempMin = distance(leftPoint, rightPoint);
                    if (tempMin < d) {
                        d = tempMin;
                        result[0] = leftPoint;
                        result[1] = rightPoint;
                    }
                }
            }
        }
        return result;
    }

    public static double distance(Point p1, Point p2) {
        if (p1 == p2)
            return 0;
        return Math.sqrt(Math.pow(p1.x - p2.x, 2) + Math.pow(p1.y - p2.y, 2));
    }


    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();//n个点
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = (int) (Math.random() * 1000 + 1);
            int y = (int) (Math.random() * 1000 + 1);
            points[i] = new Point(x, y);
        }

        Arrays.sort(points, (o1, o2) -> {
            if (o1.x < o2.x)
                return -1;
            if (o1.x > o2.x)
                return 1;
            if (o1.y < o2.y)
                return -1;
            if (o1.y > o2.y)
                return 1;
            return 0;
        });

        System.out.println(Arrays.toString(points));

        int midIndex = (points.length - 1) >> 1;

        System.out.println("中点：" + points[midIndex].toString());
        Point[] minPoint = minDistance(points);
        System.out.println("点对最小值： " + distance(minPoint[0], minPoint[1]));
        for (Point point : minPoint) {
            System.out.println(point);
        }
    }
}
