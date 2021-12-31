package dynamic_programming.range;

/**
 * 训练营3期PPT(第8节) 题目六
 * 第26节、经典面试题(十)
 * 给定一个字符串，如果可以在字符串任意位置添加字符，
 * 最少添加几个能让字符串整体都是回文串。
 * 范围上的尝试模型 重点题
 */
public class MinAdd_To_Palindrome {

    //dp[i][j]的含义：
    //s[i,j]范围上变成回文串，至少需要添加几个字符
    public static int process(String s) {
        if (s == null || s.length() == 0) {
            return 0;//空串本身就是回文，无需添加
        }
        char[] str = s.toCharArray();
        int N = str.length;
        int[][] dp = new int[N][N];
        //对角线
        for (int i = 0; i < N; i++) {
            dp[i][i] = 0;//str[i,i]范围上只有用一个字符，本身就是回文串
        }
        //倒数第二条对角线
        for (int i = 1; i < N; i++) {
            dp[i - 1][i] = str[i - 1] == str[i] ? 0 : 1;
        }
        //普遍位置 左到右，下到上
        for (int i = N - 3; i >= 0; i--) {
            for (int j = i + 2; j < N; j++) {
                //最后解决开头
                dp[i][j] = dp[i + 1][j] + 1;//下面的格子
                //最后解决结尾
                dp[i][j] = Math.min(dp[i][j], dp[i][j - 1] + 1);//左边的格子
                //开头结尾字符相等时
                if (str[i] == str[j]) {
                    dp[i][j] = Math.min(dp[i][j], dp[i + 1][j - 1]);//左下角的格子
                }
            }
        }
        return dp[0][N - 1];
    }

    public static void main(String[] args) {
        System.out.println(process("aaabbbaa"));
    }

}
