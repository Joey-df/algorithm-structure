package coding_for_great_offer.class14;

/**
 * 给定一个只由左括号和右括号的字符串
 * 返回最长的有效括号子串的长度
 */
// leetcode32
// https://leetcode.com/problems/longest-valid-parentheses/
public class Code01_Parentheses {

    public static int longestValidParentheses(String s) {
        if (s == null || s.length() < 2) {
            return 0;
        }
        char[] str = s.toCharArray();
        int N = str.length;
        int[] dp = new int[N];//dp[i]:以i位置结尾的有效子串有多长
        dp[0] = 0;
        int pre;
        int ans = 0;
        for (int i = 1; i < N; i++) {
            if (str[i] == ')') {
                //pre的含义：当前i位置的) 应该找哪个位置的(
                pre = i - dp[i - 1] - 1;
                if (pre >= 0 && str[pre] == '(') {
                    dp[i] = dp[i - 1] + 2 + (pre > 0 ? dp[pre - 1] : 0);
                }
                ans = Math.max(ans, dp[i]);
            }
        }
        return ans;
    }
}
