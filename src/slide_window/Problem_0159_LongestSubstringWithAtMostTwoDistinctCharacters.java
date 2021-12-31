package slide_window;

/**
 * 159. 至多包含两个不同字符的最长子串
 * 给定一个字符串 s ，找出 至多 包含两个不同字符的最长子串 t 。
 * 示例 1:
 * 输入: "eceba"
 * 输出: 3
 * 解释: t 是 "ece"，长度为3。
 * 示例 2:
 * 输入: "ccaabbb"
 * 输出: 5
 * 解释: t 是 "aabbb"，长度为5。
 */
public class Problem_0159_LongestSubstringWithAtMostTwoDistinctCharacters {

    public static int process(String s) {
        int L = 0, R = 0;
        int diff = 0;
        int ans = 0;
        char[] str = s.toCharArray();
        int N = str.length;
        int[] map = new int[256];
        for (; L < N; L++) {
            while (R < N && (diff < 2 || (diff == 2 && map[str[R]] > 0))) {
                diff += map[str[R]] == 0 ? 1 : 0;
                map[str[R++]]++;
            }
            //R来到第一个违规的位置
            ans = Math.max(ans, R - L);
            //处理L位置(L即将++)
            diff -= map[str[L]] == 1 ? 1 : 0;
            map[str[L]]--;
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(process("eceba"));
        System.out.println(process("ccaabbb"));
    }
}
