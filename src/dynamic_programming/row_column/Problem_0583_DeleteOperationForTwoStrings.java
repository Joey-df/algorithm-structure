package dynamic_programming.row_column;

/**
 * 583. 两个字符串的删除操作
 * 给定两个单词 word1 和 word2，找到使得 word1 和 word2 相同所需的最小步数，每步可以删除任意一个字符串中的一个字符。
 *
 * 示例：
 * 输入: "sea", "eat"
 * 输出: 2
 * 解释: 第一步将"sea"变为"ea"，第二步将"eat"变为"ea"
 *
 * 提示：
 * 给定单词的长度不超过500。
 * 给定单词中的字符只含有小写字母。
 */
public class Problem_0583_DeleteOperationForTwoStrings {

    //只有删除、添加、保留的编辑距离问题
    public static int minDistance(String word1, String word2) {
        if (word1==null && word2==null) return 0;
        int d=1, a=1;
        if (word1==null ^ word2==null) {
            return word1==null ? word2.length()*a : word1.length()*d;
        }
        return fun(word1.toCharArray(), word2.toCharArray(),1,1);
    }

    //每一步，删除代价d、插入代价a，保留代价0
    //将str1变成str2，最小的编辑代价，返回
    public static int fun(char[] str1, char[] str2, int d, int a) {
        int n = str1.length;
        int m = str2.length;
        int[][] dp = new int[n+1][m+1];
        //dp[i][j]含义：
        //str前i个字符，变成str2前j个字符，最小编辑代价是多少
        dp[0][0] = 0;
        for (int i = 1; i <= n; i++) { //第一列
            dp[i][0] = i*d;
        }
        for (int j = 1; j <= m; j++) { //第一行
            dp[0][j] = j*a;
        }
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                //最后一个字符，delete还是add，先pk一下
                dp[i][j] = Math.min(dp[i-1][j] + d, dp[i][j-1] + a);
                //如果最后一个字符相等，才有第3种情况
                if (str1[i-1]==str2[j-1]) {
                    dp[i][j] = Math.min(dp[i][j], dp[i-1][j-1]);
                }
            }
        }
        return dp[n][m];
    }

    public static void main(String[] args) {
        String str1="sea";
        String str2="eat";
        System.out.println(minDistance(str1, str2));
    }
}
