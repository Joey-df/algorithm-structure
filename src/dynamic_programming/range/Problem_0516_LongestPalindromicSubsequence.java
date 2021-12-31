package dynamic_programming.range;

/**
 * 516. 最长回文子序列
 * 给你一个字符串 s ，找出其中最长的回文子序列，并返回该序列的长度。
 * 子序列定义为：不改变剩余字符顺序的情况下，删除某些字符或者不删除任何字符形成的一个序列。
 * 示例 1：
 * 输入：s = "bbbab"
 * 输出：4
 * 解释：一个可能的最长回文子序列为 "bbbb" 。
 * 示例 2：
 * 输入：s = "cbbd"
 * 输出：2
 * 解释：一个可能的最长回文子序列为 "bb" 。
 * 提示：
 * 1 <= s.length <= 1000
 * s 仅由小写英文字母组成
 */
//最长回文子序列
//求的是最长回文子序列的长度
//范围上的尝试模型
public class Problem_0516_LongestPalindromicSubsequence {

    public static int longestPalindromeSubseq(String s) {
        if (s==null || s.length()==0) return 0;
        char[] str = s.toCharArray();
        return process(str, 0, str.length - 1);
    }

    //范围上的尝试
    //递归含义：str[L...R]范围上最长回文子序列有多长？
    //潜台词：1、str.length>1   2、L<=R
    public static int process(char[] str, int L, int R) {
        //base case1:字符串[L...R]范围上只有1个字符
        if (L == R) return 1;
        //base case2:字符串[L...R]范围上只有2个字符
        if (L + 1 == R) {
            return str[L] == str[R] ? 2 : 1;
        }
        //走到这里说明[L...R]范围上至少有3个字符
        //最长回文子序列，以下统称为lps
        //1、lps以str[L]开头，不以str[R]结尾，如abac
        int p1 = process(str, L, R - 1);
        //2、lps不以str[L]开头，但以str[R]结尾，如saba
        int p2 = process(str, L + 1, R);
        //3、lps不以str[L]开头，也不以str[R]结尾，如sabav
        int p3 = process(str, L + 1, R - 1);
        int p4 = Integer.MIN_VALUE;
        //4、lps以str[L]开头，同时以str[R]结尾，前提是str[L]==str[R] 如sabas
        if (str[L] == str[R]) {
            p4 = process(str, L + 1, R - 1) + 2;
        }
        return Math.max(Math.max(p1, p2), Math.max(p3, p4));
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
        // 以下for循环完成: 对角线 + 倒数第二条对角线
        dp[N - 1][N - 1] = 1; //右下角
        for (int i = 0; i < N - 1; i++) {
            dp[i][i] = 1;
            dp[i][i + 1] = str[i] == str[i + 1] ? 2 : 1;
        }

        //普遍位置
        //从左往右，再依次从下往上
        for (int l = N - 3; l >= 0; l--) {
            for (int r = l + 2; r < N; r++) {
                int ans = Math.max(dp[l][r - 1], dp[l + 1][r]);
                //画图观察，这句可以去掉
                //ans = Math.max(ans, dp[l + 1][r - 1]);//左下角
                if (str[l] == str[r]) {
                    ans = Math.max(ans, dp[l + 1][r - 1] + 2);
                }
                dp[l][r] = ans;
            }
        }

        return dp[0][N - 1];
    }

    public static void main(String[] args) {
        String s = "obbbababbboo";
        System.out.println(longestPalindromeSubseq(s));
        System.out.println(ways2(s));
    }

}
