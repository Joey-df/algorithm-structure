package coding_for_great_offer.class11;

/**
 * 一个字符串str，至少切几刀，让切出来的部分全是回文串。
 * 如："12321abakfk"，至少切2刀变成12321、aba、kfk三个回文串。
 * <p>
 * 从左往右尝试 + 范围上的尝试(作为一个子过程)
 */
// 本题测试链接 :
// leetcode 132
// https://leetcode.com/problems/palindrome-partitioning-ii/
public class Code02_PalindromePartitioningII {

    //从左往右的尝试模型
    //暴力尝试:
    //递归含义：从s[index...N-1]切分，让切出来的部分都是回文串，最少的回文串数量，返回
    public static int process(char[] str, int index) {
        if (index == str.length) {
            return 0; //s[N...N-1]没有字符，0个回文串
        }
        int N = str.length;
        int parts = Integer.MAX_VALUE; //部分数
        boolean[][] isP = isP(str);
        for (int end = index; end < N; end++) {
            if (isP[index][end]) {
                parts = Math.min(parts, process(str, end + 1) + 1);
            }
        }
        return parts;
    }

    public static boolean[][] isP(char[] str) {
        int N = str.length;
        boolean[][] dp = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            dp[i][i] = true;
        }
        for (int i = 1; i < N; i++) {
            dp[i - 1][i] = str[i - 1] == str[i];
        }
        for (int i = N - 3; i >= 0; i--) {
            for (int j = i + 2; j < N; j++) {
                dp[i][j] = str[i] == str[j] && dp[i + 1][j - 1];
            }
        }
        return dp;
    }

    public static int minCut(String s) {
        if (s == null || s.equals("") || s.length() < 2) {
            return 0;
        }
//        return process(s.toCharArray(), 0);
        return dpWay(s.toCharArray()) - 1;
    }

    public static int dpWay(char[] str) {
        int N = str.length;
        int[] dp = new int[N + 1];
        dp[N] = 0;
        boolean[][] isP = isP(str);
        for (int i = N - 1; i >= 0; i--) {
            int parts = Integer.MAX_VALUE; //部分数
            for (int end = i; end < N; end++) {
                if (isP[i][end]) {
                    parts = Math.min(parts, dp[end + 1] + 1);
                }
            }
            dp[i] = parts;
        }
        return dp[0];
    }


    public static void main(String[] args) {
        System.out.println(minCut("aba"));
    }
}
