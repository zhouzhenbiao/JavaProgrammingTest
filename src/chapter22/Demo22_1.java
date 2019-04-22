package chapter22;

import org.junit.Test;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * 最大的连续递增的有序子串，纯字母，最简单的做法就是遍历整个字符串，然后把每个增序放入list中，然后遍历list中的string.length
 * 时间复杂度最坏是2n
 */
public class Demo22_1 {

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

    @Test
    public void testGetMaxOrderlySubstring() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < 100000; i++) {
            int random = (int) (Math.random() * 26);
            random += 97;
            stringBuilder.append((char) random);
        }
        String str = stringBuilder.toString();
        System.out.println(str);
        if (!isAlphabet(str)) {
            System.out.println("输入的不是纯字母");
        } else {
            long time = System.currentTimeMillis();
            String maxOrderlySubstring = getMaxOrderlySubstring(str);
            System.out.println(System.currentTimeMillis() - time);
            System.out.println(maxOrderlySubstring);
        }
    }

    private String getMaxOrderlySubstring(String str) {
        LinkedList<String> list = new LinkedList<>();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(str.charAt(0));
        for (int i = 1; i < str.length(); i++) {
            char charAt = str.charAt(i);
            if (charAt > stringBuilder.charAt(stringBuilder.length() - 1))
                stringBuilder.append(charAt);
            else {
                list.addFirst(stringBuilder.toString());
                stringBuilder.setLength(0);
                stringBuilder.append(charAt);
            }
        }
        int length = 0;
        String lengthMax = "";
        for (String s : list) {
            if (s.length() > length) {
                length = s.length();
                lengthMax = s;
            }
        }
        return lengthMax;
    }
}
