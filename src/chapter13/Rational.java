package chapter13;

public class Rational extends Number implements Comparable<Rational> {

    //分子
    private long numerator = 0;

    //分母
    private long denominator = 1;

    //默认使用分子为 0 ，分母为 1 创建一个有理数
    public Rational() {
        this(0, 1);
    }

    public Rational(long numerator, long denominator) {
        int gcd = gcd(numerator, denominator);
        this.numerator = (denominator > 0 ? 1 : -1) * numerator / gcd;
        this.denominator = Math.abs(denominator) / gcd;
    }

    //求出最大公约数的方法,且是静态的，不能依赖任何一个特定的Rational对象
    private static int gcd(long n, long d) {
        long n1 = Math.abs(n);
        long d1 = Math.abs(d);
        //因为是最大公约数不会特别大，默认值不能为零
        int gcd = 1;

        for (int i = 1; i <= n1 && i <= d1; i++) {
            if (n1 % i == 0 && d1 % i == 0) {
                gcd = i;
            }
        }
        return gcd;
    }

    public long getNumerator() {
        return numerator;
    }

    public long getDenominator() {
        return denominator;
    }

    //加法
    public Rational add(Rational secondRational) {
        long n = numerator * secondRational.getDenominator()
                + denominator * secondRational.getNumerator();
        long d = denominator * secondRational.getDenominator();
        //这里直接 new Rational 可以直接消除 GCD （最大公约数）
        return new Rational(n, d);
    }

    //减法
    public Rational subtract(Rational secondRational) {
        long n = numerator * secondRational.getDenominator()
                - denominator * secondRational.getNumerator();
        long d = denominator * secondRational.getDenominator();
        return new Rational(n, d);
    }

    //乘法
    public Rational multiply(Rational secondRational) {
        long n = numerator * secondRational.getNumerator();
        long d = denominator * secondRational.getDenominator();
        return new Rational(n, d);
    }

    //除法
    public Rational divide(Rational secondRational) {
        long n = numerator * secondRational.getDenominator();
        long d = denominator * secondRational.getNumerator();
        return new Rational(n, d);
    }

    @Override
    public String toString() {
        if (denominator == 1)
            return numerator + "";
        else
            return numerator + " / " + denominator;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Rational rational = (Rational) o;

        if (numerator != rational.getNumerator()) return false;
        return denominator == rational.getDenominator();
    }

    @Override
    public int intValue() {
        return (int) doubleValue();
    }

    @Override
    public long longValue() {
        return (long) doubleValue();
    }

    @Override
    public float floatValue() {
        return (float) doubleValue();
    }

    @Override
    public double doubleValue() {
        return numerator * 1.0 / denominator;
    }

    @Override
    public int compareTo(Rational o) {
        if (this.subtract(o).getNumerator() > 0)
            return 1;
        if (this.subtract(o).getNumerator() == 0)
            return 0;
        else
            return -1;
    }
}
