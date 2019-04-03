package chapter07;

public class Demo7_24 {
    public static void main(String[] args) {
        //1-13表示黑桃13张，14-26表示红心13张，27-39表示方块13张，40-52表示梅花13张
        //cardNumber / 13 表示黑桃红心方块梅花。 cardNumber % 13 表示 1-13牌
        int[] deck = new int[52];
        String[] ranks = {"Ace", "1", "2", "3", "4", "5","6", "7", "8", "9", "10", "Jack", "Queen", "King"};
        String[] counts = new String[4];
        for (int i = 1; i <= deck.length; i++) {
            deck[i - 1] = i;
        }
        //将扑克牌的顺序打乱
        shufflingDeck(deck);

        int i = 0;
        boolean flag = true;
        do {
            for (int j = 0; j < counts.length; j++) {
                if (counts[j] == null) {
                    counts[(deck[i] - 1) / 13] = ranks[(deck[i] - 1) % 13];
                }
            }
            i++;
            flag = (counts[0] != null && counts[1] != null && counts[2] != null && counts[3] != null) ? false : true;
        } while (flag);

        for (int j = 0; j < counts.length; j++) {
            System.out.println(counts[j] + " of " + getColor(j));
        }

        System.out.println("Numbers of picks:" + i);
    }

    public static int[] shufflingDeck(int[] deck) {
        for (int i = 0; i < deck.length; i++) {
            int index = (int) (Math.random() * deck.length);
            int temp = deck[i];
            deck[i] = deck[index];
            deck[index] = temp;
        }
        return deck;
    }

    public static String getColor(int i) {
        String color = "";
        switch(i){
            case 0: color = "黑桃"; break;
            case 1: color = "红心"; break;
            case 2: color = "方块"; break;
            case 3: color = "梅花"; break;
        }
        return color;
    }
}
