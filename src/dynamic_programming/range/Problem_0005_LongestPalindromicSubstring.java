package dynamic_programming.range;

/**
 * Given a string s, find the longest palindromic substring in s.
 * You may assume that the maximum length of s is 1000.
 * 
 * Example 1:
 * 
 * Input: "babad"
 * Output: "bab"
 * Note: "aba" is also a valid answer.
 * Example 2:
 * 
 * Input: "cbbd"
 * Output: "bb"
 * 范围上的尝试模型
 * 寻找最长回文子子串
 * manacher
 */
public class Problem_0005_LongestPalindromicSubstring {

    public static String ways(String s) {
        if ("".equals(s) || s.length() == 0) return "";
        if (s.length() == 1) return s;
        // s长度大于1
        int[][] dp = getDp(s.toCharArray());
        int maxLen = 0;
        int start = -1;
        int end = -1;
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp.length; j++) {
                if (dp[i][j] > maxLen) {
                    maxLen = dp[i][j];
                    start = i;
                    end = j;
                }
            }
        }
        return s.substring(start, end + 1);
    }

    //范围上的尝试，正方形表 下半区没用
    //潜台词：str长度大于1
    //dp[i][j]含义：
    //str[i...j]，以i位置开头 && 以j位置结尾 最长回文子串的长度是多少？
    public static int[][] getDp(char[] str) {
        int N = str.length;
        int[][] dp = new int[N][N];
        // 1、先填对角线
        for (int i = 0; i < N; i++) {
            dp[i][i] = 1;
        }
        // 2、倒数第二条对角线
        for (int i = 1; i < N; i++) {
            dp[i - 1][i] = str[i] == str[i - 1] ? 2 : 0;
        }
        // 普遍位置
        // 从左往右，再依次从下往上
        // 因为每一个格子依赖其左下角的位置
        for (int row = N - 3; row >= 0; row--) {
            for (int col = row + 2; col < N; col++) {
                //当且仅当 首尾字符一样，并且str[row+1][col-1]是回文时，才有意义。否则长度就是0
                if (str[row] == str[col] && dp[row + 1][col - 1] > 0) {
                    dp[row][col] = dp[row + 1][col - 1] + 2;
                }
                //System.out.println("dp["+row+"]["+col+"] = " + dp[row][col]);
            }
        }
        return dp;
    }

    public static String ways2(String s) {
        if ("".equals(s) || s.length() == 0) return "";
        if (s.length() == 1) return s;
        char[] str = s.toCharArray();
        int maxLen = 0;
        int start = -1;
        int end = -1;
        int N = str.length;
        int[][] dp = new int[N][N];
        // 1、先填对角线
        for (int i = 0; i < N; i++) {
            dp[i][i] = 1;
            if (i == 0) {
                maxLen = dp[i][i];
                start = i;
                end = i;
            }
        }
        // 2、倒数第二条对角线
        for (int i = 1; i < N; i++) {
            dp[i - 1][i] = str[i] == str[i - 1] ? 2 : 0;
            if (dp[i - 1][i] > 0 && dp[i - 1][i] > maxLen) {
                maxLen = dp[i - 1][i];
                start = i - 1;
                end = i;
            }
        }
        // 普遍位置
        // 从左往右，再依次从下往上
        // 因为每一个格子依赖其左下角的位置
        for (int row = N - 3; row >= 0; row--) {
            for (int col = row + 2; col < N; col++) {
                //当且仅当 首尾字符一样，并且str[row+1][col-1]是回文时，才有意义。否则长度就是0
                if (str[row] == str[col] && dp[row + 1][col - 1] > 0) {
                    dp[row][col] = dp[row + 1][col - 1] + 2;
                }
                if (dp[row][col] > 0 && dp[row][col] > maxLen) {
                    maxLen = dp[row][col];
                    start = row;
                    end = col;
                }
            }
        }
        return s.substring(start, end + 1);
    }

    public static void main(String[] args) {
        String s = "abcccccccbsa";
        System.out.println(ways(s));
        System.out.println(ways2(s));
    }
}
