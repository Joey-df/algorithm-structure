package train_camp_03.class03;

/**
 * 给定两个字符串str1和str2，求两个字符串的最长公共子串
 * <p>
 * 请注意区分子串和子序列的不同
 * <p>
 * 动态规划的空间压缩技巧！
 */
public class Code05_LCSubstring {

    //dp[i][i]的含义：
    //最长公共子串必须 同时以str1[i]、str2[j]结尾 的 最大长度是多少。
    public static String lcSubstr(String s1, String s2) {
        if (s1.length() == 0 || s2.length() == 0) {
            return "";
        }
        char[] str1 = s1.toCharArray();
        char[] str2 = s2.toCharArray();
        int N = str1.length;
        int M = str2.length;
        int[][] dp = new int[N][M];
        int max = 0;
        int end = -1;
        dp[0][0] = str1[0] == str2[0] ? 1 : 0;
        //第一行
        for (int i = 1; i < M; i++) {
            dp[0][i] = str1[0] == str2[i] ? 1 : 0;
        }
        //第一列
        for (int i = 1; i < N; i++) {
            dp[i][0] = str1[i] == str2[0] ? 1 : 0;
        }
        //普遍位置
        for (int i = 1; i < N; i++) {
            for (int j = 1; j < M; j++) {
                dp[i][j] = str1[i] == str2[j] ? dp[i - 1][j - 1] + 1 : 0;
                if (dp[i][j] > max) {
                    max = Math.max(max, dp[i][j]);
                    end = i;
                }
            }
        }
        return s1.substring(end - max + 1, end + 1);
    }



    public static void main(String[] args) {
        System.out.println(lcSubstr("12abc1234qwe", "12xyb1234789"));
    }
}
