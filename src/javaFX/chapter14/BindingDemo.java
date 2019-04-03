package javaFX.chapter14;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class BindingDemo {
    public static void main(String[] args) {
        DoubleProperty d1 = new SimpleDoubleProperty(1);
        DoubleProperty d2 = new SimpleDoubleProperty(2);
        SimpleIntegerProperty i1 = new SimpleIntegerProperty(5);

        d1.bindBidirectional(d2);

        System.out.println("d1 is " + d1.getValue() + " and d2 is " + d2.getValue());

        d2.setValue(72.00);

        System.out.println("d1 is " + d1.getValue() + " and d2 is " + d2.getValue());

        d1.setValue(88.00);

        System.out.println("d1 is " + d1.getValue() + " and d2 is " + d2.getValue());

        i1.bind(d2);

        System.out.println("i1 is " + i1.getValue() + " and d2 is " + d2.getValue());
    }
}

