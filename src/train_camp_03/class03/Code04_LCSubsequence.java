package train_camp_03.class03;

/**
 * 给定两个字符串str1和str2，求两个字符的最长公共子序列
 * <p>
 * 请注意区分子串和子序列的不同
 * https://leetcode.com/problems/longest-common-subsequence/
 * 动态规划的空间压缩技巧！
 */
public class Code04_LCSubsequence {
    //dp[i][j]含义: str1[0,i] str2[0,j]最长公共子序列有多长
    public static int lcs(String s1, String s2) {
        if (s1.length() == 0 || s2.length() == 0) {
            return 0;
        }
        char[] str1 = s1.toCharArray();
        char[] str2 = s2.toCharArray();
        int N = str1.length;
        int M = str2.length;
        int[][] dp = new int[N][M];
        dp[0][0] = str1[0] == str2[0] ? 1 : 0;
        //第一行
        //str1[0,0]与str2[0,col]最长公共子序列有多长
        for (int col = 1; col < M; col++) {
            dp[0][col] = (str1[0] == str2[col]) ? 1 : dp[0][col - 1];
        }

        //第一列
        //str1[0,row]与str2[0,0]最长公共子序列有多长
        for (int row = 1; row < N; row++) {
            dp[row][0] = (str1[row] == str2[0]) ? 1 : dp[row - 1][0];
        }
        //普遍位置
        for (int row = 1; row < N; row++) {
            for (int col = 1; col < M; col++) {
                dp[row][col] = Math.max(dp[row - 1][col], dp[row][col - 1]);
                if (str1[row] == str2[col]) {
                    dp[row][col] = Math.max(dp[row][col], dp[row - 1][col - 1] + 1);
                }
            }
        }
        return dp[N - 1][M - 1];
    }

    public static void main(String[] args) {
        System.out.println(lcs("a1b2c3d", "x1y2z3p"));
    }
}
