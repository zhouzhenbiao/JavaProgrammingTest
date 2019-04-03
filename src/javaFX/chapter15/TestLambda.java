package javaFX.chapter15;

public class TestLambda {

    public static void main(String[] args) {
        B b = new B();
        System.out.println(b.jiafa(12, 5));

        A a = (int c, int d) -> {
            return c + d;
        };

        System.out.println(a.jiafa(12, 4));

    }


}
interface A {
    int jiafa(int a, int b);
}

class B implements A{

    @Override
    public int jiafa(int a, int b) {
        return 0;
    }
}


