package coding_for_great_offer.class04;

/**
 * 字符串交错组成
 * <p>
 * leetcode97
 * https://leetcode.com/problems/interleaving-string/
 */
public class Code07_InterleavingString {
    //给定s1、s2
    //能否交错组成s3
    //前提：s1/s2/s3都非空
    public static boolean process(String s1, String s2, String s3) {
        char[] str1 = s1.toCharArray();
        char[] str2 = s2.toCharArray();
        char[] str3 = s3.toCharArray();
        int N = str1.length; //行
        int M = str2.length; //列
        int len = str3.length;
        if (N + M != len) return false;
        // dp[i][j]的含义：
        // s1的前i个字符、s2前j个字符，能否交错组成s3的前i+j个字符
        // 行列样本对应模型
        boolean[][] dp = new boolean[N + 1][M + 1];
        dp[0][0] = true;
        //第一行:s1的前0个字符，s2的前[j]个字符能够交错组成s3前j个字符
        for (int i = 1; i <= M; i++) {
            dp[0][i] = (str2[i - 1] == str3[i - 1]) && dp[0][i - 1];
        }
        //第一列
        for (int i = 1; i <= N; i++) {
            dp[i][0] = dp[i - 1][0] && (str1[i - 1] == str3[i - 1]);
        }

        //普通位置
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                dp[i][j] = false;
                if (str3[i + j - 1] == str1[i - 1]) {
                    dp[i][j] |= dp[i - 1][j];
                }
                if (str3[i + j - 1] == str2[j - 1]) {
                    dp[i][j] |= dp[i][j - 1];
                }
            }
        }
        return dp[N][M];
    }

    public static void main(String[] args) {
        System.out.println(process("aabcc",
                "dbbca",
                "aadbbcbcac"));
    }
}
