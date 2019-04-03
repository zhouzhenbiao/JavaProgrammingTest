package chapter11;

import java.util.Date;

public class GeometricObject {
    private String color = "white";
    private boolean filler;
    private Date dateCreate;

    public GeometricObject() {
        dateCreate = new Date();
    }

    public GeometricObject(String color, boolean filler) {
        //两种方法二选一
        this();
//        dateCreate = new Date();
        this.color = color;
        this.filler = filler;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public boolean isFiller() {
        return filler;
    }

    public void setFiller(boolean filler) {
        this.filler = filler;
    }

    public Date getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(Date dateCreate) {
        this.dateCreate = dateCreate;
    }

    @Override
    public String toString() {
        return "created on : " + dateCreate + "\n color : " + color + "and filled : " + filler;
    }
}
