package dynamic_programming.range;

/**
 * Given a string s, find the longest palindromic subsequence's length in s.
 * You may assume that the maximum length of s is 1000.
 * <p>
 * Example 1:
 * Input:
 * <p>
 * "bbbab"
 * Output:
 * 4
 * One possible longest palindromic subsequence is "bbbb".
 * <p>
 * <p>
 * Example 2:
 * Input:
 * <p>
 * "cbbd"
 * Output:
 * 2
 * One possible longest palindromic subsequence is "bb".
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= s.length <= 1000
 * s consists only of lowercase English letters.
 * <p>
 * 最长回文子序列
 * 求的是最长回文子序列的长度
 * 范围上的尝试模型
 */
public class Problem_516_Longest_Palindromic_Subsequence {

    public static int ways1(String s) {
        if ("".equals(s) || s.length() == 0) return 0;
        char[] str = s.toCharArray();
        return process(str, 0, str.length - 1);
    }

    //范围上的尝试
    //递归含义：str[L...R]范围上最长回文子序列有多长？
    //潜台词：1、str.length>1   2、L<=R
    public static int process(char[] str, int L, int R) {
        //base case1:字符串[L...R]范围上只有1个字符
        if (L == R) return 1;
        //base case1:字符串[L...R]范围上只有2个字符
        if (L + 1 == R) {
            return str[L] == str[R] ? 2 : 1;
        }
        //走到这里说明[L...R]范围上至少有3个字符
        //最长回文子串，以下统称为lps
        int ans = Integer.MIN_VALUE;
        //1、lps以str[L]开头，不以str[R]结尾，如abac
        int p1 = process(str, L, R - 1);
        ans = Math.max(ans, p1);
        //2、lps不以str[L]开头，但以str[R]结尾，如saba
        int p2 = process(str, L + 1, R);
        ans = Math.max(ans, p2);
        //3、lps不以str[L]开头，也不以str[R]结尾，如sabav
        int p3 = process(str, L + 1, R - 1);
        ans = Math.max(ans, p3);
        //4、lps以str[L]开头，同时以str[R]结尾，前提是str[L]==str[R] 如sabas
        if (str[L] == str[R]) {
            int p4 = process(str, L + 1, R - 1) + 2;
            ans = Math.max(ans, p4);
        }
        return ans;
    }

    public static int ways2(String s) {
        if ("".equals(s) || s.length() == 0) return 0;
        if (s.length() == 1) return 1;
        char[] str = s.toCharArray();
        return dpWay(str);
    }

    // dp[i][j]的含义：
    // str[i...j]范围上最长回文子序列有多长？
    // 潜台词：str长度大于1
    public static int dpWay(char[] str) {
        int N = str.length;
        int[][] dp = new int[N][N];
        // 对角线
//        for (int i = 0; i < N; i++) {
//            dp[i][i] = 1;
//        }
//        // 第二条对角线
//        for (int i = 1; i < N; i++) {
//            dp[i - 1][i] = str[i - 1] == str[i] ? 2 : 1;
//        }

        dp[N - 1][N - 1] = 1;
        for (int i = 0; i < N - 1; i++) {
            dp[i][i] = 1;
            dp[i][i + 1] = str[i] == str[i + 1] ? 2 : 1;
        }

        //普遍位置
        //从左往右，再依次从下往上
        for (int row = N - 3; row >= 0; row--) {
            for (int col = row + 2; col < N; col++) {
                int ans = Math.max(dp[row][col - 1], dp[row + 1][col]);
                //画图观察，这句可以去掉
                //ans = Math.max(ans, dp[row + 1][col - 1]);//左下角
                if (str[row] == str[col]) {
                    ans = Math.max(ans, dp[row + 1][col - 1] + 2);
                }
                dp[row][col] = ans;
            }
        }

        return dp[0][N - 1];
    }

    public static void main(String[] args) {
        String s = "obbbababbboo";
        System.out.println(ways1(s));
        System.out.println(ways2(s));
    }

}
