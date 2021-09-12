package dynamic_programming.row_column;

/**
 * Given s1, s2, and s3, find whether s3 is formed by the interleaving of s1 and s2.
 *
 * Example 1:
 * Input: s1 = "aabcc", s2 = "dbbca", s3 = "aadbbcbcac"
 * Output: true
 *
 * Example 2:
 * Input: s1 = "aabcc", s2 = "dbbca", s3 = "aadbbbaccc"
 * Output: false
 *
 * Example 3:
 * Input: s1 = "", s2 = "", s3 = ""
 * Output: true
 *
 * Constraints:
 * 0 <= s1.length, s2.length <= 100
 * 0 <= s3.length <= 200
 * s1, s2, and s3 consist of lower-case English letters.
 */
//97. 交错字符串
//coding_for_great_offer.class04.Code07_InterleavingString
public class Problem_0097_InterleavingString {

    public static boolean isInterleave(String s1, String s2, String s3) {
        if (s1.length()==0 && s2.length()==0) {
            return s3.length()==0;
        }
        if (s1.length()==0 ^ s2.length()==0) {
            return s1.length()==0 ? s2.equals(s3) : s1.equals(s3);
        }
        if (s1.length()+s2.length() != s3.length()) return false;
        //s1,s2都不空
        return fun(s1.toCharArray(), s2.toCharArray(), s3.toCharArray());
    }

    //s1 = "aabcc", s2 = "dbbca", s3 = "aadbbcbcac"
    public static boolean fun(char[] str1, char[] str2, char[] str3) {
        int n = str1.length;
        int m = str2.length;
        boolean[][] dp = new boolean[n+1][m+1];
        //dp[i][j]含义：
        //str1的前i个、str2的前j个，能否交错组成str3的前i+j个
        dp[0][0] = true;
        for (int i = 1; i < n + 1; i++) { //第一列
            dp[i][0] = str1[i-1] == str3[i-1] && dp[i - 1][0];
        }
        for (int j = 1; j <= m; j++) { //第一行
            dp[0][j] = str2[j-1]==str3[j-1] && dp[0][j-1];
        }
        //普遍位置
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                boolean ans = str3[i+j-1]==str1[i-1] && dp[i-1][j];
                ans |= str3[i+j-1]==str2[j-1] && dp[i][j-1];
                dp[i][j] = ans;
            }
        }
        return dp[n][m];
    }

    public static void main(String[] args) {
        String s1 = "aabcc", s2 = "dbbca", s3 = "aadbbcbcac";
        System.out.println(isInterleave(s1,s2,s3));
    }

}
