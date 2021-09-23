package slide_window;

/**
 * Given a string, find the length of the longest substring T that contains at most k distinct characters.
 * <p>
 * Example 1:
 * <p>
 * Input: s = "eceba", k = 2
 * Output: 3
 * Explanation: T is "ece" which its length is 3.
 * Example 2:
 * <p>
 * Input: s = "aa", k = 1
 * Output: 2
 * Explanation: T is "aa" which its length is 2.
 * 加锁的题 这个是好题
 * 需要反复练习
 * 24.leetcode高频题目全讲（二十四）讲解
 */
public class Problem_0340_LongestSubstringWithAtMostKDistinctCharacters {

    public static int process(String s, int k) {
        if (s == null || "".equals(s) || s.length() == 0 || k <= 0) {
            return 0;
        }
        int R = 0, diff = 0;
        int ans = 0;
        char[] str = s.toCharArray();
        int N = str.length;
        int[] map = new int[128];//记账表
        for (int l = 0; l < N; l++) { //尝试每一个开头
            while (R < N && (diff < k || (diff == k && map[str[R]] > 0))) {//窗口往右扩，扩不动了 停
                diff += map[str[R]] == 0 ? 1 : 0;
                map[str[R++]]++;
            }
            //R来到下一个违规的位置
            ans = Math.max(ans, R - l);
            //l即将++，l位置的字符出窗口
            if (map[str[l]] == 1) {
                diff--;
            }
            map[str[l]]--;
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(process("aa", 1));
    }
}
