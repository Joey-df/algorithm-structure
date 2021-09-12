package dynamic_programming.range;

/**
 * 664. 奇怪的打印机
 * 有台奇怪的打印机有以下两个特殊要求：
 * 打印机每次只能打印由 同一个字符 组成的序列。
 * 每次可以在任意起始和结束位置打印新字符，并且会覆盖掉原来已有的字符。
 * 给你一个字符串 s ，你的任务是计算这个打印机打印它需要的最少打印次数。
 *
 * 示例 1：
 * 输入：s = "aaabbb"
 * 输出：2
 * 解释：首先打印 "aaa" 然后打印 "bbb"。
 * 示例 2：
 * 输入：s = "aba"
 * 输出：2
 * 解释：首先打印 "aaa" 然后在第二个位置打印 "b" 覆盖掉原来的字符 'a'。
 *
 * 提示：
 * 1 <= s.length <= 100
 * s 由小写英文字母组成
 */
public class Problem_0664_StrangePrinter {

    //记忆化搜索
    public static int strangePrinter(String s) {
        if (s==null || s.length()==0) return 0;
        char[] str = s.toCharArray();
        int n = str.length;
        int[][] dp = new int[n][n];
        return fun(str,0,n-1, dp);
    }

    //递归含义
    //刷成str[l,r]范围上样子，至少需要多少转，返回
    public static int fun(char[] str, int l, int r, int[][] dp) {
        if (dp[l][r]!=0) return dp[l][r];
        int ans;
        if (l>r) {
            ans = 0;
        } else if (l==r) {
            ans = 1;
        } else {
            ans = r-l+1; //每次刷一个位置的字符，最大就是str[l,r]的长度
            //枚举所有可能的右部分的第一个位置
            for (int i = l+1; i <= r; i++) {
                int cur = fun(str, l,i-1, dp) + fun(str, i, r, dp) - (str[l]==str[i]?1:0); //所有两部分左边沿的第一个字符一样的话，可以合成一次
                ans = Math.min(ans, cur);
            }
        }
        dp[l][r] = ans;
        return ans;
    }


    //严格位置依赖的dp
    public static int strangePrinter1(String s) {
        if (s==null || s.length()==0) return 0;
        char[] str = s.toCharArray();
        int n = str.length;
        int[][] dp = new int[n][n];
        dp[n-1][n-1] = 1;//右下角
        //两条对角线
        for (int i = 0; i < n-1; i++) {
            dp[i][i] = 1;
            dp[i][i+1] = str[i]==str[i+1]?1:2;
        }
        //普遍位置
        for (int l = n-3; l >= 0; l--) {
            for (int r = l+2; r < n; r++) {
                int ans = r-l+1;
                //枚举所有可能的右部分的第一个位置
                for (int i = l+1; i <= r; i++) {
                    int cur = dp[l][i-1] + dp[i][r] - (str[l]==str[i]?1:0); //所有两部分左边沿的第一个字符一样的话，可以合成一次
                    ans = Math.min(ans, cur);
                }
                dp[l][r] = ans;
            }
        }
        return dp[0][n-1];
    }


    public static void main(String[] args) {
        String s = "aaabbb";
        System.out.println(strangePrinter(s));
        System.out.println(strangePrinter1(s));
    }

}
