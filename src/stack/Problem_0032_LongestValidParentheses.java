package stack;

/**
 * Given a string containing just the characters '(' and ')',
 * find the length of the longest valid (well-formed) parentheses substring.
 * <p>
 * Example 1:
 * <p>
 * Input: "(()"
 * Output: 2
 * Explanation: The longest valid parentheses substring is "()"
 * Example 2:
 * <p>
 * Input: ")()())"
 * Output: 4
 * Explanation: The longest valid parentheses substring is "()()"
 * 从左往右尝试模型解法
 */
public class Problem_0032_LongestValidParentheses {

    //dp[i]的含义：必须以i位置结尾的串，有效串有多长？
    public static int dpWays(String s) {
        //空串或者长度为1 没有意义
        if ("".equals(s) || s == null || s.length() < 2) return 0;
        int ans = 0;
        //s.length()>=2
        char[] str = s.toCharArray();
        int N = str.length;
        int[] dp = new int[N];
        //dp[0]==0，所以从1开始
        for (int i = 1; i < N; i++) {
            if (str[i] != '(') { //以(结尾的肯定是无效串，不讨论
                //str[i]==')'
                //以i-1位置结尾的有效串的前一个位置
                //这种写法适用于任何位置
                int pre = i - dp[i - 1] - 1;
                if (pre >= 0 && str[pre] == '(') {
                    dp[i] = dp[i - 1] + 2 + (pre > 0 ? dp[pre - 1] : 0);
                }
                ans = Math.max(ans, dp[i]);
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        String s = "()()))))()()(";
        System.out.println(dpWays(s));
    }

}
