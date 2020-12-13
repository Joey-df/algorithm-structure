package train_camp_04.class02;

/**
 * 给定两个字符串S和T，返回S子序列等于T的不同子序列个数有多少个?
 * 如果得到子序列A删除的位置与得到子序列B删除的位置不同，那么认为A和B就是不同的。
 * 【例子】
 * S = "rabbbit", T = "rabbit"
 * 返回: 3
 * 是以下三个S的不同子序列，没有^的位置表示删除的位置，因为删除的位置不同，所以这三 个子序列是不一样的
 * (上箭头符号 ^ 表示选取的字母)
 * <p>
 * rabbbit
 * ^^^^ ^^
 * rabbbit
 * ^^ ^^^^
 * rabbbit
 * ^^^ ^^^
 * <p>
 * https://leetcode.com/problems/distinct-subsequences/
 */
public class Code04_DistinctSubsequences {

    //dp[i][j]含义：
    //s[0,i]删除得到t[0,j]的方法数是多少
    //行列模型方法论：讨论结尾字符如何归属
    public static int process(String S, String T) {
        char[] s = S.toCharArray();
        char[] t = T.toCharArray();
        int[][] dp = new int[s.length][t.length];
        dp[0][0] = s[0] == t[0] ? 1 : 0; //s[0] 删除得到 t[0]
        //第一行 s[0]经过删除变成t[0,j]
        for (int j = 1; j < t.length; j++) {
            dp[0][j] = 0;
        }
        //第一列 s[0,i]经过删除变成t[0]
        for (int i = 1; i < s.length; i++) {
            dp[i][0] = dp[i - 1][0] + (s[i] == t[0] ? 1 : 0);
        }

        //普遍位置
        for (int i = 1; i < s.length; i++) {
            for (int j = 1; j < t.length; j++) {
                dp[i][j] = dp[i - 1][j];//不考虑i位置字符s[i]
                //考虑i位置字符s[i]
                dp[i][j] += (s[i] == t[j]) ? dp[i - 1][j - 1] : 0;
            }
        }
        return dp[s.length - 1][t.length - 1];
    }

    public static int ways(String s, String t) {
        if ((s == null || s.length() == 0)) {
            if ((t == null || t.length() == 0)) {
                return 1;
            } else {
                return -1;
            }
        } else {
            if ((t == null || t.length() == 0)) {
                return 1;
            }
        }
        return process(s, t);
    }

    public static void main(String[] args) {
        System.out.println(ways("rabbbit", "rabbit"));
    }
}
