package chapter13;

public class DemoTest implements Cloneable{

    public static void main(String[] args) {
        A b = new A();
        System.out.println(" b.getWidth = " + b.getWidth() + " :  b.getHeight = " + b.getHeight() );
        B b1 = new A(2.0, 3.0);
        System.out.println(" b1.getWidth = " + b1.getWidth() + " :  b1.getHeight = " + b1.getHeight() );

        A b2 = (A) b.clone();
        System.out.println(" b2.getWidth = " + b2.getWidth() + " :  b2.getHeight = " + b2.getHeight() );
        System.out.println(b + "b; " + b2 + "b2;");
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}

class A extends B{

    public A() {
        super();
        System.out.println(" super.getWidth = " + super.getWidth() + " : super.getHeight = " + super.getHeight());
    }

    public A(double width, double height) {
        super(width, height);
        System.out.println(" super.getWidth = " + super.getWidth() + " : super.getHeight = " + super.getHeight());
    }
}

class B implements Cloneable{
    private double width;
    private double height;

    public B() {
        this(1.0, 1.0);
    }

    public B(double width, double height) {
        this.width = width;
        this.height = height;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    @Override
    protected Object clone() {
        try {
            return super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            return null;
        }
    }
}
