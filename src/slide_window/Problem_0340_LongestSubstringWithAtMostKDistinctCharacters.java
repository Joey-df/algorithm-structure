package slide_window;

/**
 * 340.至多包含K个不同字符的最长子串
 * 给定一个字符串 s ，找出 至多 包含 k 个不同字符的最长子串 T。
 * 示例 1:
 * 输入: s = "eceba", k = 2
 * 输出: 3
 * 解释: 则 T 为 "ece"，所以长度为 3。
 * 示例 2:
 * 输入: s = "aa", k = 1
 * 输出: 2
 * 解释: 则 T 为 "aa"，所以长度为 2。
 * ————————————————
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
