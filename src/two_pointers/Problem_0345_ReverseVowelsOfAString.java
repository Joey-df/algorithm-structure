package two_pointers;

import java.util.HashSet;
import java.util.Set;

/**
 * 345. 反转字符串中的元音字母
 * 给你一个字符串 s ，仅反转字符串中的所有元音字母，并返回结果字符串。
 * 元音字母包括 'a'、'e'、'i'、'o'、'u'，且可能以大小写两种形式出现。
 *
 * 示例 1：
 * 输入：s = "hello"
 * 输出："holle"
 * 示例 2：
 * 输入：s = "leetcode"
 * 输出："leotcede"
 */
public class Problem_0345_ReverseVowelsOfAString {

    private static Set<Character> getVowels() {
        Set<Character> set = new HashSet<>();
        set.add('a');
        set.add('e');
        set.add('i');
        set.add('o');
        set.add('u');
        set.add('A');
        set.add('E');
        set.add('I');
        set.add('O');
        set.add('U');
        return set;
    }

    private static void swap(char[] str, int i, int j) {
        char t = str[i];
        str[i] = str[j];
        str[j] = t;
    }

    public static String reverseVowels(String s) {
        if ("".equals(s) || s == null || s.length() == 0) return s;
        Set<Character> set = getVowels();
        char[] str = s.toCharArray();
        int L = 0;
        int R = str.length - 1;
        while (L < R) {
            if (set.contains(str[L]) && set.contains(str[R])) { //L R同时是元音字母
                if (str[L] != str[R]) {
                    swap(str, L, R);
                }
                L++;
                R--;
            } else if (set.contains(str[L])) {
                R--;
            } else {
                L++;
            }
        }
        return String.valueOf(str);
    }

    public static void main(String[] args) {
        String s = "aA";
        System.out.println(reverseVowels(s));
    }
}
