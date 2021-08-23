package hash_map;

import java.util.HashMap;
import java.util.Map;

/**
 * 205. 同构字符串
 * 给定两个字符串 s 和 t，判断它们是否是同构的。
 * 如果 s 中的字符可以按某种映射关系替换得到 t ，那么这两个字符串是同构的。
 * 每个出现的字符都应当映射到另一个字符，同时不改变字符的顺序。不同字符不能映射到同一个字符上，相同字符只能映射到同一个字符上，字符可以映射到自己本身。
 *
 * 示例 1:
 * 输入：s = "egg", t = "add"
 * 输出：true
 * 示例 2：
 * 输入：s = "foo", t = "bar"
 * 输出：false
 * 示例 3：
 * 输入：s = "paper", t = "title"
 * 输出：true
 *
 * 提示：
 * 可以假设 s 和 t 长度相同。
 */
public class Problem_0205_IsomorphicStrings {

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
