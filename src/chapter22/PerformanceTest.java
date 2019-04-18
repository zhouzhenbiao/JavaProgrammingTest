package chapter22;

public class PerformanceTest {
    public static void main(String[] args) {
        getTime(1000000L);
        getTime(10000000L);
        getTime(100000000L);
        getTime(1000000000L);
        getTime(10000000000L);

    }

    private static void getTime(long n) {
        long k = 0;
        long time = System.currentTimeMillis();
        for (long i = 0; i < n; i++) {
            k += 5;
        }
        System.out.println("Execution time for n = " + n + " is " + (System.currentTimeMillis() - time));
    }
}
