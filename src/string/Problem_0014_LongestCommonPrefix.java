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
public class Problem_0014_LongestCommonPrefix {
    //最长公共前缀
    public static String longestCommonPrefix(String[] arr) {
        if (arr == null || arr.length == 0) return null;
        int n = arr.length;
        char[] first = arr[0].toCharArray();
        int index = first.length;
        for (int i = 1; i < n; i++) { //从第二个字符串开始
            char[] cur = arr[i].toCharArray();
            int j = 0;
            for (; j < cur.length && j < first.length; j++) {
                if (cur[j] != first[j]) {
                    break;
                }
            }
            index = Math.min(index, j);
            System.out.println(index);
            if (index == 0) {
                return "";
            }
        }
        return arr[0].substring(0, index);
    }

    public static void main(String[] args) {
        String[] arr = {"aaa", "aa", "aaa", "aaa"};
        System.out.println(longestCommonPrefix(arr));
    }
}
