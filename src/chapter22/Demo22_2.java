package chapter22;

import org.junit.Test;

import java.util.LinkedList;

/**
 * 最大增序子序列
 * eg：Welcome ： Welo，ASCII码值一个比一个大
 */
public class Demo22_2 {
    private String getMaxSubstring(String str) {
        char[] chars = str.toCharArray();
        int slen = str.length();
        LinkedList<String> list = new LinkedList<>();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(chars[0]);
        for (int i = 1; i < slen; i++) {
            for (int j = i; j < slen; j++) {
                if (chars[j] > stringBuilder.charAt(stringBuilder.length() - 1))
                    stringBuilder.append(chars[j]);
            }
            list.addFirst(stringBuilder.toString());
            stringBuilder.setLength(0);
            stringBuilder.append(chars[i]);
        }

        int maxLength = 0;
        String maxLengthString = "";
        for (String s : list) {
            if (s.length() > maxLength) {
                maxLength = s.length();
                maxLengthString = s;
            }
        }
        return maxLengthString;
    }

    @Test
    public void testGetMaxOrderlySubstring() {
        StringBuilder stringBuilder = new StringBuilder();
        int random;
        int temp = (int) (Math.random() * 2);
        for (int i = 0; i < 10000; i++) {
            random = (int) (Math.random() * 26);
            if (i % 3 == 0)
                temp = (int) (Math.random() * 2);
            random += temp == 0 ? 65 : 97;
            stringBuilder.append((char) random);
        }
        String str = stringBuilder.toString();
        System.out.println(str);
        if (!isAlphabet(str)) {
            System.out.println("输入的不是纯字母");
        } else {
            long time = System.currentTimeMillis();
            String maxOrderlySubstring = getMaxSubstring(str);
            System.out.println(System.currentTimeMillis() - time);
            System.out.println(maxOrderlySubstring);
        }
    }

    //判断是否是由字母组成的65,97
    private boolean isAlphabet(String str) {
        char[] chars = str.toCharArray();
        int length = str.length();
        for (int i = 0; i < length; i++) {
            if (!((chars[i] >= 65 && chars[i] <= 90)
                    || (chars[i] >= 97 && chars[i] <= 122)))
                return false;
        }
        return true;
    }
}
