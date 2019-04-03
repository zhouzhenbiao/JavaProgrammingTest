package chapter18;

/**
 * 使用递归求一个整数各位数之和
 */
public class Demo18_11 {

    public static void main(String[] args) {
        long number = 1234567891234567891L;
        System.out.println(number);
        System.out.println("\n" + sumDigits(number));
    }

    // 正序输出 eg：12345 = 1 + 2 + 3 + 4 + 5 = 15
    //基础情形是什么 base case ：个位数直接 % 10 输入并累加
    private static int sumDigits(long n) {
        if (n >= 0 && n < 10) {
            System.out.print(n + " + ");
            return (int) n;
        } else {
            int temp = 0;
            temp = (int) (sumDigits(n / 10) + (n % 10));
            System.out.print(n % 10 + " + ");
            return temp;
        }
    }
}
