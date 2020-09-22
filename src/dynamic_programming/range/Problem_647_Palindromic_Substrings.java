package dynamic_programming.range;

/**
 * Given a string, your task is to count how many palindromic substrings in this string.
 * 
 * The substrings with different start indexes or end indexes are counted as
 * different substrings even they consist of same characters.
 * 
 * Example 1:
 * 
 * Input: "abc"
 * Output: 3
 * Explanation: Three palindromic strings: "a", "b", "c".
 * 
 * 
 * Example 2:
 * 
 * Input: "aaa"
 * Output: 6
 * Explanation: Six palindromic strings: "a", "a", "a", "aa", "aa", "aaa".
 * 
 * 
 * Note:
 * 
 * The input string length won't exceed 1000.
 */
public class Problem_647_Palindromic_Substrings {

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
