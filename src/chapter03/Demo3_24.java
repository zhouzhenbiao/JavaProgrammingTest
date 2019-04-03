package chapter03;

public class Demo3_24 {
    public static void main(String[] args) {
        int[] card = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13};
        int n = 0;
        int m = 0;
        n = (int) (Math.random() * 13 + 1);
        m = n % 4;

        System.out.print("抽出的牌是：");

        switch (m) {
            case 0:
                System.out.print(" ♣ ");
                break;
            case 1:
                System.out.print(" ♦ ");
                break;
            case 2:
                System.out.print(" 💗 ");
                break;
            case 3:
                System.out.print(" ♠ ");
                break;
        }

        System.out.print(" of ");

        switch (n) {
            case 0:
                System.out.print("A");
                break;
            case 10:
                System.out.print("Jack");
                break;
            case 11:
                System.out.print("Queen");
                break;
            case 12:
                System.out.print("King");
                break;
            default:
                System.out.print(card[n]);
        }
        System.out.println();

    }
}
