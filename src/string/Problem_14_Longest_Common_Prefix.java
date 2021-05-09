package string;

/**
 * Write a function to find the longest common prefix string amongst an array of strings.
 * <p>
 * If there is no common prefix, return an empty string "".
 * <p>
 * Example 1:
 * <p>
 * Input: strs = ["flower","flow","flight"]
 * Output: "fl"
 * Example 2:
 * <p>
 * Input: strs = ["dog","racecar","car"]
 * Output: ""
 * Explanation: There is no common prefix among the input strings.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 0 <= strs.length <= 200
 * 0 <= strs[i].length <= 200
 * strs[i] consists of only lower-case English letters.
 */
public class Problem_14_Longest_Common_Prefix {

    public static String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        if (strs.length == 1) return strs[0];
        //strs.length>=2
        char[] base = strs[0].toCharArray();
        int minIndex = Integer.MAX_VALUE;
        for (int i = 1; i < strs.length; i++) {
            int index = 0;
            String str = strs[i];
            char[] cur = str.toCharArray();
            while (index < base.length && index < cur.length) { //都不越界时
                if (base[index] != cur[index]) break;
                index++;
            }
            minIndex = Math.min(minIndex, index);
            if (minIndex == 0) {
                return "";
            }
        }
        return strs[0].substring(0, minIndex);
    }

    public static void main(String[] args) {
        System.out.println(longestCommonPrefix(new String[]{"flow", "flower", "floor"}));
    }
}
