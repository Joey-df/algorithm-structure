package train_camp_03.class06;

/**
 * 给定三个字符串str1、str2和aim，如果aim包含且仅包含来自str1和str2的所有字符，
 * 而且在aim中属于str1的字符之间保持原来在str1中的顺序，属于str2的字符之间保持 原来在str2中的顺序，
 * 那么称aim是str1和str2的交错组成。实现一个函数，判断aim是 否是str1和str2交错组成
 * 【举例】 str1="AB"，str2="12"。
 * 那么"AB12"、"A1B2"、"A12B"、"1A2B"和"1AB2"等都是 str1 和 str2 的 交错组成
 * <p>
 * https://leetcode.com/problems/interleaving-string/description/
 * 需要多复习
 */
public class Code06_StringCross {

    //一个样本做行一个样本做列的对应模型
    //dp[i][j]含义：
    //str1[0,i-1] str2[0,j-1]能否交错组成出aim[0,i+j-1]的部分
    //str1的前i个 和 str2的前j个 能否组成aim的前i+j个
    public static boolean stringCross(String s1, String s2, String aim) {
        if ("".equals(s1) && "".equals(s2) && "".equals(aim)) {
            return true;
        }
        if (s1 == null || s2 == null || aim == null) {
            return false;
        }
        int N = s1.length();
        int M = s2.length();

        if ((N + M) != aim.length()) {
            return false;
        }
        boolean[][] dp = new boolean[N + 1][M + 1];
        char[] str1 = s1.toCharArray();
        char[] str2 = s2.toCharArray();
        char[] str3 = aim.toCharArray();

        dp[0][0] = true; //空和空组成空
        //第一行：s1取0个字符 s2取前i个 能否组成aim的前i个
        for (int i = 1; i <= M; i++) {
            if (str2[i - 1] != str3[i - 1]) break;
            dp[0][i] = true;
        }
        //第一列：s1取前i个 s2取0个字符 能否组成aim的前i个
        for (int i = 1; i <= N; i++) {
            if (str1[i - 1] != str3[i - 1]) break;
            dp[i][0] = true;
        }

        //普遍位置：看str3的结尾字符
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                dp[i][j] = (str1[i - 1] == str3[i + j - 1] && dp[i - 1][j])
                        || (str2[j - 1] == str3[i + j - 1] && dp[i][j - 1]);
            }
        }
        return dp[N][M];
    }

    public static void main(String[] args) {
        System.out.println(stringCross("AB", "12", "A1B2"));
    }
}
