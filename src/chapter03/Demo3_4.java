package chapter03;

public class Demo3_4 {
    public static void main(String[] args) {
        int month;
        for (int i = 0; i < 24; i++) {
            month = (int) (Math.random() * 12) + 1;
            System.out.print("月份：" + month);
            switch (month) {
                case 1:
                    System.out.println("现在正是一月");
                    break;
                case 2:
                    System.out.println("现在正是二月");
                    break;
                case 3:
                    System.out.println("现在正是三月");
                    break;
                case 4:
                    System.out.println("现在正是四月");
                    break;
                case 5:
                    System.out.println("现在正是五月");
                    break;
                case 6:
                    System.out.println("现在正是六月");
                    break;
                case 7:
                    System.out.println("现在正是七月");
                    break;
                case 8:
                    System.out.println("现在正是八月");
                    break;
                case 9:
                    System.out.println("现在正是九月");
                    break;
                case 10:
                    System.out.println("现在正是十月");
                    break;
                case 11:
                    System.out.println("现在正是十一月");
                    break;
                case 12:
                    System.out.println("现在正是十二月");
                    break;
            }
        }
    }
}
