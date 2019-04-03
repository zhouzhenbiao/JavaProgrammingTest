package chapter20;

import java.util.*;

public class Demo20_4 {
    public static void main(String[] args) {
        Point[] points = new Point[100];
        for (int i = 0; i < points.length; i++) {
            int random1 = (int) (Math.random() * 100);
            int random2 = (int) (Math.random() * 100);

            Point point = new Point(random1, random2);
            points[i] = point;
        }
        int i = 0;
        for (Point point : points) {
            System.out.print(point.toString() + "  ");
            if (i % 6 == 0)
                System.out.println();
            i++;
        }
        Arrays.sort(points);
        System.out.println();
        System.out.println("------------");
        i = 0;
        for (Point point : points) {
            System.out.print(point.toString() + "  ");
            if (i % 6 == 0)
                System.out.println();
            i++;
        }

    }

    public static class Point implements Comparable<Point> {

        private double x;
        private double y;

        public Point(double x, double y) {
            this.x = x;
            this.y = y;
        }

        public void setX(double x) {
            this.x = x;
        }

        public void setY(double y) {
            this.y = y;
        }

        public double getX() {
            return x;
        }

        public double getY() {
            return y;
        }

        @Override
        public String toString() {
            return "Point{" +
                    "x=" + x +
                    ", y=" + y +
                    '}';
        }

        @Override
        public int compareTo(Point o) {
            if (x > o.getX())
                return 1;
            else if (x < o.getX())
                return -1;
            else {
                if (y > o.getY())
                    return 1;
                else if (y < o.getY())
                    return -1;
                else
                    return 0;
            }
        }
    }

    public static class ComparatorY implements Comparator<Point> {

        @Override
        public int compare(Point o1, Point o2) {
            if (o1.getY() > o2.getY())
                return 1;
            else if (o1.getY() < o2.getY())
                return -1;
            else {
                if (o1.getX() > o2.getX())
                    return 1;
                else if (o1.getX() < o2.getX())
                    return -1;
                else
                    return 0;
            }
        }
    }
}
