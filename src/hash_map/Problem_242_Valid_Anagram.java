package hash_map;

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
public class Problem_242_Valid_Anagram {
    public static boolean process(String s, String t) {
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
        int[] counts = new int[256];//包含所有unicode characters
        char[] str = s.toCharArray();
        char[] target = t.toCharArray();
        for (int i = 0; i < target.length; i++) {
            counts[target[i]]++;
        }
        int all = target.length;

        for (int i = 0; i < str.length; i++) {
            if (--counts[str[i]] >= 0) all--;
        }
        return all == 0;
    }

    public static void main(String[] args) {
        String s = ".,*&&&", t = "&&.,&*";
        System.out.println(process(s, t));
    }

}
