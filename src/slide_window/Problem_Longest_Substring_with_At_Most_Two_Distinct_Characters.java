package slide_window;

/**
 * Given a string s , find the length of the longest substring t  that contains at most 2 distinct characters.
 * <p>
 * Example 1:
 * <p>
 * Input: "eceba"
 * Output: 3
 * Explanation: tis "ece" which its length is 3.
 * Example 2:
 * <p>
 * Input: "ccaabbb"
 * Output: 5
 * Explanation: tis "aabbb" which its length is 5.
 */
public class Problem_Longest_Substring_with_At_Most_Two_Distinct_Characters {

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
