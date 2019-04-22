package chapter22;

import org.junit.Test;

import java.util.LinkedList;

/**
 * 最大增序子序列
 * eg：Welcome ： Welo，ASCII码值一个比一个大
 */
public class Demo22_2 {
    private String getMaxSubstring(String str) {
        LinkedList<String> list = new LinkedList<>();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(str.charAt(0));
        for (int i = 1; i < str.length(); i++) {
            for (int j = i; j < str.length(); j++) {
                char charAt = str.charAt(j);
                if (charAt > stringBuilder.charAt(stringBuilder.length() - 1))
                    stringBuilder.append(charAt);

            }
            list.addFirst(stringBuilder.toString());
            stringBuilder.setLength(0);
            stringBuilder.append(str.charAt(i));
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
        for (int i = 0; i < str.length(); i++) {
            char charAt = str.charAt(i);
            if (!((charAt >= 65 && charAt <= 90)
                    || (charAt >= 97 && charAt <= 122)))
                return false;
        }
        return true;
    }
}
