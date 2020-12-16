package train_camp_04.class05;

/**
 * 给定一个字符串str，求最长回文子序列长度
 * <p>
 * 两种解法：
 * 1、str逆序得到str2，str与str2求最长公共子序列
 * 2、使用范围上的尝试模型直接求最长回文子序列长度
 */
public class Code01_PalindromeSubsequence {

    //dp[i][j]含义：
    //str[i,j]范围上的最长回文子序列是多长
    public static int process(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        char[] str = s.toCharArray();
        int N = str.length;
        int[][] dp = new int[N][N];//下半区没用
        //对角线
        for (int i = 0; i < N; i++) {
            dp[i][i] = 1; //str[i,i]只有一个字符，最长回文子序列就是1
        }
        //第二条对角线
        for (int i = 1; i < N; i++) {
            dp[i - 1][i] = (str[i - 1] == str[i]) ? 2 : 1;
        }
        //普遍位置
        //从左往右，再以此从下往上
        for (int i = N - 3; i >= 0; i--) {
            for (int j = i + 2; j < N; j++) {
                dp[i][j] = dp[i + 1][j];// 不包括i位置字符 如 a121
                dp[i][j] = Math.max(dp[i][j], dp[i][j - 1]); //不包括j位置字符 如 121a
                //dp[i][j] = Math.max(dp[i][j], dp[i+1][j-1]); //i、j位置都不包括 如 a121b  这句可以去掉
                if (str[i] == str[j]) {
                    dp[i][j] = Math.max(dp[i][j], dp[i + 1][j - 1] + 2); //同时包括i、j位置字符，如a121a
                }
            }
        }
        return dp[0][N - 1];
    }

    public static void main(String[] args) {
        System.out.println(process("a12bf1a"));
        //a121a
    }
}
