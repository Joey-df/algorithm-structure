package string;

import java.util.HashMap;
import java.util.Map;

/**
 * Given two strings s and t, determine if they are isomorphic.
 *
 * Two strings are isomorphic if the characters in s can be replaced to get t.
 *
 * All occurrences of a character must be replaced with another character while preserving the order of characters.
 * No two characters may map to the same character but a character may map to itself.
 *
 * Example 1:
 *
 * Input: s = "egg", t = "add"
 * Output: true
 * Example 2:
 *
 * Input: s = "foo", t = "bar"
 * Output: false
 * Example 3:
 *
 * Input: s = "paper", t = "title"
 * Output: true
 * Note:
 * You may assume both s and t have the same length.
 */
public class Problem_205_Isomorphic_Strings {

    private static class Wrap {
        StringBuilder index;
        int count;

        public Wrap() {
        }

        public Wrap(StringBuilder i, int c) {
            index = i;
            count = c;
        }
    }

    public static boolean isIsomorphic(String s, String t) {
        if (s == null || t == null) return false;
        if (s.length() != t.length()) return false;
        return process(s.toCharArray(), t.toCharArray());
    }

    private static boolean process(char[] s, char[] t) {
        Map<Character, Wrap> map1 = new HashMap<>();
        Wrap tmp = null;
        for (int i = 0; i < s.length; i++) {
            if (map1.get(s[i]) != null) {
                tmp = map1.get(s[i]);
                map1.put(s[i], new Wrap(tmp.index.append(i), tmp.count + 1));
            } else {
                map1.put(s[i], new Wrap(new StringBuilder(i), 1));
            }

        }
        Map<Character, Wrap> map2 = new HashMap<>();
        for (int i = 0; i < t.length; i++) {
            if (map2.containsKey(t[i])) {
                tmp = map2.get(t[i]);
                map2.put(t[i], new Wrap(tmp.index.append(i), tmp.count + 1));
            } else {
                map2.put(t[i], new Wrap(new StringBuilder(i), 1));
            }
        }
        for (int i = 0; i < s.length; i++) {
            if (map1.get(s[i]).count != map2.get(t[i]).count
                    || !map1.get(s[i]).index.toString().equals(map2.get(t[i]).index.toString())) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(isIsomorphic("abba", "cbbc"));
    }
}
