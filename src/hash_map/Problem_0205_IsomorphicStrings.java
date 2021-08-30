package hash_map;

import java.util.HashMap;
import java.util.Map;

/**
 * 205. 同构字符串
 * 给定两个字符串 s 和 t，判断它们是否是同构的。
 * 如果 s 中的字符可以按某种映射关系替换得到 t ，那么这两个字符串是同构的。
 * 每个出现的字符都应当映射到另一个字符，同时不改变字符的顺序。
 * 不同字符不能映射到同一个字符上，相同字符只能映射到同一个字符上，字符可以映射到自己本身。
 * <p>
 * 示例 1:
 * 输入：s = "egg", t = "add"
 * 输出：true
 * 示例 2：
 * 输入：s = "foo", t = "bar"
 * 输出：false
 * 示例 3：
 * 输入：s = "paper", t = "title"
 * 输出：true
 * <p>
 * 提示：
 * 可以假设 s 和 t 长度相同。
 */
//与290是同一个题
public class Problem_0205_IsomorphicStrings {

    public static boolean isIsomorphic(String s, String t) {
        if (s == null && t == null) return true;
        if (s == null ^ t == null) return false;
        if (s.length() != t.length()) return false;
        char[] str1 = s.toCharArray();
        char[] str2 = t.toCharArray();
        //<str1[i], str2[i]>
        Map<Character, Character> map = new HashMap<>();
        int n = str1.length;
        for (int i = 0; i < n; i++) {
            char k = str1[i];
            char v = str2[i];
            if (map.containsKey(k) && !map.get(k).equals(v)) return false;
            if (!map.containsKey(k) && map.containsValue(v)) return false;
            map.put(k, v);
        }
        return true;
    }


    public static void main(String[] args) {
        System.out.println(isIsomorphic("egg", "add"));
        System.out.println(isIsomorphic("egg", "for"));
    }
}
