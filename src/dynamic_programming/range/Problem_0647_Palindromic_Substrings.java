package dynamic_programming.range;

/**
 * 647. 回文子串
 * 给你一个字符串 s ，请你统计并返回这个字符串中 回文子串 的数目。
 * 具有不同开始位置或结束位置的子串，即使是由相同的字符组成，也会被视作不同的子串。
 * 示例 1：
 * 输入：s = "abc"
 * 输出：3
 * 解释：三个回文子串: "a", "b", "c"
 * 示例 2：
 * 输入：s = "aaa"
 * 输出：6
 * 解释：6个回文子串: "a", "a", "a", "aa", "aa", "aaa"
 */
public class Problem_0647_Palindromic_Substrings {

    public static int process(String s) {
        if ("".equals(s) || s.length() == 0) return 1;
        if (s.length() == 1) return 1;
        boolean[][] dp = getDp(s.toCharArray());
        int ans = 0;
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[0].length; j++) {
                if (dp[i][j])
                    ans++;
            }
        }
        return ans;
    }

    //dp[i][j]的含义：
    //str[i...j] 必须以i开始、j结束组成的子串，是否是回文串？
    //潜台词：str.length>1
    private static boolean[][] getDp(char[] str) {
        int N = str.length;
        boolean[][] dp = new boolean[N][N];
        //对角线
        for (int i = 0; i < N; i++) {
            dp[i][i] = true;
        }
        //倒数第二条对角线
        for (int i = 1; i < N; i++) {
            dp[i - 1][i] = str[i - 1] == str[i];
        }
        //普遍位置
        //每一行从左往右，再依次从下往上
        for (int i = N - 3; i >= 0; i--) {
            for (int j = i + 2; j < N; j++) {
                if (dp[i + 1][j - 1] && str[i] == str[j]) {
                    dp[i][j] = true;
                }
            }
        }
        return dp;
    }


    public static void main(String[] args) {
        String s = "aaa";
        System.out.println(process(s));
    }
}
