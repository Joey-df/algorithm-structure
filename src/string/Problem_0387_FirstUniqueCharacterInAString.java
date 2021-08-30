package string;

/**
 * Given a string, find the first non-repeating character in it and return its index.
 * If it doesn't exist, return -1.
 * <p>
 * Examples:
 * <p>
 * s = "leetcode"
 * return 0.
 * <p>
 * s = "loveleetcode"
 * return 2.
 * <p>
 * <p>
 * Note: You may assume the string contains only lowercase English letters.
 */
public class Problem_0387_FirstUniqueCharacterInAString {

    public int firstUniqChar(String s) {
        if (s == null || "".equals(s)) return -1;
        int[] map = new int[26];
        char[] str = s.toCharArray();
        for (int i = 0; i < str.length; i++) {
            map[str[i] - 'a']++; //统计每个字符的词频
        }
        for (int i = 0; i < str.length; i++) {
            if (map[str[i] - 'a'] == 1) return i;
        }
        return -1;
    }
}
