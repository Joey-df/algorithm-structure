package dynamic_programming.row_column;

/**
 * 给定两个字符串str1、str2，求两个字符串的最长公共子序列
 * 子序列：保持字符相对顺序，可以不连续
 * 模型：一个样本做行一个样本做列的对应模型
 */
public class Problem_1143Longest_Common_SubSequence {

    //暴力尝试
    //一个样本做行一个样本做列的对应模型
    //str[0...i1] 与 str[0...i2]的最长公共子序列有多长？
    public static int process(char[] str1, char[] str2, int i1, int i2) {
        // i1==0 && i2==0
        if (i1 == 0 && i2 == 0) {
            return str1[i1] == str2[i2] ? 1 : 0;
        }
        // i1==0 && i2!=0
        if (i1 == 0) { //第一行；
//            return Math.max(process(str1, str2, i1, i2 - 1), str1[0]==str2[i2] ? 1 : 0);
            return (str1[i1] == str2[i2] || process(str1, str2, i1, i2 - 1) == 1) ? 1 : 0;
        }
        // i1!=0 && i2==0
        if (i2 == 0) { //第一列；
            return (str1[i1] == str2[i2] || process(str1, str2, i1 - 1, i2) == 1) ? 1 : 0;
        }
        // i1!=0 && i2!=0
        int ans = Integer.MIN_VALUE;
        int p1 = process(str1, str2, i1 - 1, i2 - 1);//最长公共子序列 都不以i1、i2结尾
        ans = Math.max(ans, p1);
        int p2 = process(str1, str2, i1, i2 - 1); //以i1结尾，但不以i2结尾
        ans = Math.max(ans, p2);
        int p3 = process(str1, str2, i1 - 1, i2); //以i2结尾，但不以i1结尾
        ans = Math.max(ans, p3);
        if (str1[i1] == str2[i2]) {
            ans = Math.max(ans, process(str1, str2, i1 - 1, i2 - 1) + 1);
        }
        return ans;
    }

    //dp[i][j]表示 str1[0...i]  str2[0...j]的最长公共子序列有多长
    public static int dp(char[] s1, char[] s2) {
        int N = s1.length; //行
        int M = s2.length; //列
        int[][] dp = new int[N][M];
        dp[0][0] = s1[0] == s2[0] ? 1 : 0;
        for (int i = 1; i < N; i++) { //第一列
            dp[i][0] = (s1[i] == s2[0] || dp[i - 1][0] == 1) ? 1 : 0;
        }
        for (int j = 1; j < M; j++) { //第一行
            dp[0][j] = (s1[0] == s2[j] || dp[0][j - 1] == 1) ? 1 : 0;
        }
        //普通位置
        for (int i = 1; i < N; i++) {
            for (int j = 1; j < M; j++) {
                // 不用考虑dp[i-1][j-1]  因为他下面的格子和右边的格子一定比他大，画图观察法
                dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                if (s1[i] == s2[j]) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - 1] + 1);
                }
            }
        }
        return dp[N - 1][M - 1];
    }

    public static void main(String[] args) {
        String str1 = "A1B5C23Z4";
        String str2 = "1P5T2O3YU4P";
        char[] s1 = str1.toCharArray();
        char[] s2 = str2.toCharArray();
        System.out.println(process(s1, s2, str1.length() - 1, str2.length() - 1));
        System.out.println(dp(s1, s2));
    }
}
