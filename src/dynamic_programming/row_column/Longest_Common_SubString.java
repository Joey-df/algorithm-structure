package dynamic_programming.row_column;

/**
 * 给定两个字符串str1、str2，求两个字符串的最长公共子串
 * 子串：必须连续
 * 模型：一个样本做行一个样本做列的对应模型
 */
public class Longest_Common_SubString {

    //str1、str2必须同时以i1、i2结尾，最长公共子串是多长？
    public static int[][] getDP(char str1[], char[] str2, int i1, int i2) {
        int dp[][] = new int[str1.length][str2.length];
        dp[0][0] = str1[0] == str2[0] ? 1 : 0;
        //第一行
        for (int i = 1; i < str2.length; i++) {
            dp[0][i] = str1[0] == str2[i] ? 1 : 0;
        }
        //第一列
        for (int j = 1; j < str1.length; j++) {
            dp[j][0] = str1[j] == str2[0] ? 1 : 0;
        }
        //普遍位置
        for (int i = 1; i < str1.length; i++) {
            for (int j = 1; j < str2.length; j++) {
                dp[i][j] = str1[i] == str2[j] ? dp[i - 1][j - 1] + 1 : 0;
            }
        }
        return dp;
    }


    public static void main(String[] args) {
        String str1 = "A1B5C0235Z4";
        String str2 = "1P5T0235YU4P";
        char[] s1 = str1.toCharArray();
        char[] s2 = str2.toCharArray();
        int[][] dp = getDP(s1, s2, s1.length - 1, s2.length - 1);
        int maxLen = Integer.MIN_VALUE;
        int endIndex = -1; //最长公共子串在str1中结尾的index
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[0].length; j++) {
                if (dp[i][j] > maxLen) {
                    maxLen = dp[i][j];
                    endIndex = i;
                }
            }
        }
        String res = str1.substring(endIndex - maxLen + 1, endIndex + 1);//最长公共子串
        System.out.println(maxLen);
        System.out.println(endIndex);
        System.out.println(res);
    }

}
