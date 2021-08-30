package string;

/**
 * Given two strings s and t , write a function to determine if t is an anagram of s.
 * <p>
 * Example 1:
 * <p>
 * Input: s = "anagram", t = "nagaram"
 * Output: true
 * Example 2:
 * <p>
 * Input: s = "rat", t = "car"
 * Output: false
 * Note:
 * You may assume the string contains only lowercase alphabets.
 * <p>
 * Follow up:
 * What if the inputs contain unicode characters? How would you adapt your solution to such case?
 */
//判断两个字符串是否互为变形词
public class Problem_0242_ValidAnagram {
    public static boolean isAnagram(String s, String t) {
        if ((s == null || s.length() == 0) && (t == null || t.length() == 0)) { //同时为空
            return true;
        }
        if (s == null || s.length() == 0 || t == null || t.length() == 0) {//有一个为空
            return false;
        }
        //都不为空
        if (s.length() != t.length()) {
            return false;
        }
        int[] map = new int[256];//包含所有unicode characters
        char[] str1 = s.toCharArray();
        char[] str2 = t.toCharArray();
        for (char c : str2) map[c]++;
        for (char c : str1) {
            if (map[c]-- == 0) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        String s = ".,*&&&", t = "&3.,&*";
        System.out.println(isAnagram(s, t));
    }

}
