package leetcode_top_interview_questions;

/**
 * 编写一个函数来查找字符串数组中的最长公共前缀。
 * <p>
 * 如果不存在公共前缀，返回空字符串 ""。
 * <p>
 * 示例 1：
 * <p>
 * 输入：strs = ["flower","flow","flight"]
 * 输出："fl"
 * 示例 2：
 * <p>
 * 输入：strs = ["dog","racecar","car"]
 * 输出：""
 * 解释：输入不存在公共前缀。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-common-prefix
 */
public class Problem_0014_LongestCommonPrefix {

    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        String str0 = strs[0];
        char[] base = str0.toCharArray();
        int min = base.length;
        for (int i = 1; i < strs.length; i++) {
            char[] cur = strs[i].toCharArray();
            int index = 0;
            while (index < base.length && index < cur.length) {
                if (cur[index] != base[index]) {
                    break;
                }
                index++;
            }
            min = Math.min(min, index);
            if (min==0) return "";
        }
        return str0.substring(0, min);
    }
}
