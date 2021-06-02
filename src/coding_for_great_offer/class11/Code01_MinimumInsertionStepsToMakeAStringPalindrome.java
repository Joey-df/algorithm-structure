package coding_for_great_offer.class11;

/**
 * 给定一个字符串，如果可以在字符串任意位置添加字符，最少添加几个能让字符串整体都是回文串。
 */
// 本题测试链接 :
// https://leetcode.com/problems/minimum-insertion-steps-to-make-a-string-palindrome/
public class Code01_MinimumInsertionStepsToMakeAStringPalindrome {
    //范围上的尝试模型
    public static int minInsert(String s) {
        if (s == null || "".equals(s) || s.length() == 0) {
            return 0;
        }
        char[] str = s.toCharArray();
        int N = str.length;
        int[][] dp = new int[N][N];
        //dp[i][j]含义：str[i...j]范围上最少添加几个字符让其整体是回文串
        for (int i = 0; i < N; i++) {
            dp[i][i] = 0;
        }
        for (int i = 0; i < N - 1; i++) {
            dp[i][i + 1] = str[i] == str[i + 1] ? 0 : 1;
        }
        for (int i = N - 3; i >= 0; i--) {
            for (int j = i + 2; j < N; j++) {
                dp[i][j] = Math.min(dp[i][j - 1], dp[i + 1][j]) + 1;
                if (str[i] == str[j]) {
                    dp[i][j] = Math.min(dp[i][j], dp[i + 1][j - 1]);
                }
            }
        }
        return dp[0][N - 1];
    }
}
