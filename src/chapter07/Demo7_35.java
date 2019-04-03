package chapter07;

import java.util.Scanner;

public class Demo7_35 {
    /**
     * 思考：1、首先有一个存放单词的数组
     * 2、随机选中一个单词后将这个单词显示出来，但是以 " * " 号替代
     * 3、每次从控制台输入一个字母，如果这个字母是单词内的字母，则显示这个字母。否则将这次行为记录下来
     * 不能输入上次已输入的字母---出一个提示信息，错了---也出一个提示信息
     * 4、当一个 " * " 都没有的时候停止输入并显示结果---出一个提示信息，将记录的次数打印出来
     */
    public static void main(String[] args) {
        String[] words = {"byte", "boolean", "char", "short", "int", "long", "float", "double"};
        int index = (int) (Math.random() * words.length);
        char[] chars = words[1].toCharArray();
//        for (char ch : chars) {
//            System.out.println(ch);
//        }
        char[] wordIsExist = getWordIsExistOfString(chars).toCharArray();
        int count = 0;

        Scanner input = new Scanner(System.in);

        while (wordIsExist.length > 0) {
            System.out.print("输入一个小写的字母 ");
            for (int i = 0; i < chars.length; i++) {
                System.out.print(replace(chars[i], wordIsExist) ? "*" : chars[i]);
            }
            System.out.print(" > ");
            String nextLine = input.nextLine();
            if (!replace(nextLine.charAt(0), wordIsExist)) {
                System.out.println(" * 中没有这个字母呢！");
                count++;
            }
            wordIsExist = existOfWord(nextLine, wordIsExist).toCharArray();
        }
        System.out.println("这个单词是：" + words[index] + "; 你错了" + count + "次;");
    }

    public static String getWordIsExistOfString(char[] chars) {
        int[] counts = new int[26];
        String word = "";
        for (int i = 0; i < chars.length; i++) {
            counts[chars[i] - 'a']++;
        }
        for (int i = 0; i < counts.length; i++) {
            if (counts[i] > 0) {
                char ch = (char) (i + 'a');
                word = word + ch;
            }
        }
        return word;
    }

    public static boolean replace(char ch, char[] wordIsExist) {
        for (int i = 0; i < wordIsExist.length; i++) {
            if (ch == wordIsExist[i]) {
                return true;
            }
        }
        return false;
    }

    public static String existOfWord(String nextLine, char[] wordIsExist) {
        char ch = nextLine.charAt(0);
        String word = "";
        for (int i = 0; i < wordIsExist.length; i++) {
            if (!(ch == wordIsExist[i])) {
                word = word + wordIsExist[i];
            }
        }
        return word;
    }
}