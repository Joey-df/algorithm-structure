package train_camp_03.class01;

/**
 * 括号有效配对是指：
 * 1）任何一个左括号都能找到和其正确配对的右括号
 * 2）任何一个右括号都能找到和其正确配对的左括号
 * 返回一个括号字符串中，最长的括号有效子串的长度
 * https://leetcode.com/problems/longest-valid-parentheses/
 * <p>
 * 子串解决问题思路：枚举每一个位置开头或者结尾的答案，进而使用动态规划求解问题
 * 动态规划
 */
//例如 ()()()()(((())))((((()
public class Code03_ParenthesesDeep {
    //枚举以每个位置结尾的最长有效子串长度，求整体max
    public static int maxValidLen(String s) {
        if (s == null || s.length() < 2) {
            return 0;
        }
        char[] str = s.toCharArray();
        int N = str.length;
        int ans = 0;
        int[] dp = new int[N];
        // dp[0]==0 所以从1开始遍历
        for (int i = 1; i < N; i++) {
            //以左括号结尾的一定非法
            if (str[i] == ')') { //str[i]==')'
                int pre = i - dp[i - 1] - 1;
                if (pre >= 0 && str[pre] == '(') { //()())
                    dp[i] = dp[i - 1] + 2;
                    if (pre > 0) {
                        dp[i] += dp[pre - 1];
                    }
                }
            }
            ans = Math.max(ans, dp[i]);
        }
        return ans;
    }
}
