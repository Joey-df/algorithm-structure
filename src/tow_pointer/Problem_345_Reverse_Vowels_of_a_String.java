package tow_pointer;

import java.util.HashSet;
import java.util.Set;

/**
 * Write a function that takes a string as input and reverse only the vowels of a string.
 * <p>
 * Example 1:
 * <p>
 * Input: "hello"
 * Output: "holle"
 * Example 2:
 * <p>
 * Input: "leetcode"
 * Output: "leotcede"
 * Note:
 * The vowels does not include the letter "y".
 */
public class Problem_345_Reverse_Vowels_of_a_String {

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
            if (set.contains(str[L]) && set.contains(str[R])) {
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
