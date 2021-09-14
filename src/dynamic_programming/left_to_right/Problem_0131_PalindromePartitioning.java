package dynamic_programming.left_to_right;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 131. 分割回文串
 * 给你一个字符串 s，请你将 s 分割成一些子串，使每个子串都是 回文串 。返回 s 所有可能的分割方案。
 * <p>
 * 回文串 是正着读和反着读都一样的字符串。
 * <p>
 * 示例 1：
 * 输入：s = "aab"
 * 输出：[["a","a","b"],["aa","b"]]
 * <p>
 * 示例 2：
 * 输入：s = "a"
 * 输出：[["a"]]
 */
//dfs
//从左往右的尝试模型
//范围上的尝试模型
//多叉树的遍历
public class Problem_0131_PalindromePartitioning {

    public static List<List<String>> partition(String s) {
        List<List<String>> ans = new ArrayList<>();
        if (s==null || s.length()==0) {
            return ans;
        }
        boolean[][] dp = isP(s);
        fun(s,0,new ArrayList<>(), ans, dp);
        return ans;
    }

    //s[0...i-1]已经搞定了，沿途所形成的路径，保存在path中
    //s[i...]，从i位置出发，到字符串结尾这一段，返回所有可能的拆分方案
    //ans 收集答案
    public static void fun(String s, int i, ArrayList<String> path, List<List<String>> ans, boolean[][] dp) {
        int n = s.length();
        if (i==n) {
            //s[0...n-1]已经搞定了，此时path是一种方案，收集答案
            ans.add(new ArrayList<>(path));
        } else {
            for (int end = i; end < n; end++) { //枚举每一个可能的回文串结尾
                if (dp[i][end]) {
                    path.add(s.substring(i,end+1));
                    fun(s, end+1,path,ans,dp);
                    path.remove(path.size()-1);
                }
            }
        }
    }

    private static boolean[][] isP(String s) {
        char[] str = s.toCharArray();
        int n = str.length;
        boolean[][] dp = new boolean[n][n];
        dp[n-1][n-1] = true;
        for (int i = 0; i < n-1; i++) {
            dp[i][i] = true;
            dp[i][i+1] = str[i]==str[i+1];
        }
        for (int l = n-3; l >=0 ; l--) {
            for (int r = l+2; r < n; r++) {
                dp[l][r] = str[l]==str[r] && dp[l+1][r-1];
            }
        }
        return dp;
    }

    public static void main(String[] args) {
        String s = "aa";
        System.out.println(partition(s));
    }
}
