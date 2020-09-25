package dynamic_programming.left_to_right;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Given a string s, partition s such that every substring of the partition is a palindrome.
 * 
 * Return all possible palindrome partitioning of s.
 * 
 * Example:
 * 
 * Input: "aab"
 * Output:
 * [
 * ["aa","b"],
 * ["a","a","b"]
 * ]
 * related topics:
 * dfs
 * 从左往右的尝试模型
 * 范围上的尝试模型
 * 多叉树的遍历
 */
public class Problem_131_Palindrome_Partitioning {

    public static List<List<String>> ways(String s) {
        List<List<String>> ans = new ArrayList<>();
        if (s == null || "".equals(s) || s.length() == 0) {
            ans.add(new ArrayList<>());
            return ans;
        }
        boolean[][] help = isPalindrome(s);
        process(s, 0, new LinkedList<>(), ans, help);
        return ans;
    }

    /**
     * 潜台词：s.length() > 1
     *
     * @param str   原始字符串组成的字符数组
     * @param index 当前来到的位置
     * @param path  [0...index-1]已经形成的路径
     * @param help 判断是否是回文的辅助数组
     * @return 所有可能的回文切分方案
     */
    public static void process(String str, int index, LinkedList<String> path, List<List<String>> ans, boolean[][] help) {
        if (index == str.length()) {
            //当前已经来到终止位置，表示之前已经形成的路径就是一种答案。收集答案
            ans.add(new ArrayList<>(path));
        }
        //枚举以index开头的，每一个结束的位置
        for (int end = index; end < str.length(); end++) { //从左往右尝试  多叉树的遍历
            // index..index
            // index..index+1
            // index..index+2
            // index..end
            if (help[index][end]) {
                String s = str.substring(index, end + 1);
                path.addLast(s);
                process(str, end + 1, path, ans, help);
                path.pollLast(); // 清理现场
            }
        }
    }

    //给定字符串s 长度大于0
    //返回在任意范围上是否是回文
    private static boolean[][] isPalindrome(String s) {
        char[] str = s.toCharArray();
        int N = str.length;
        boolean[][] dp = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            dp[i][i] = true;//对角线
        }
        for (int i = 1; i < N; i++) {
            dp[i - 1][i] = str[i - 1] == str[i];//倒数第二条对角线
        }
        //普遍位置
        for (int row = N - 3; row >= 0; row--) {
            for (int col = row + 2; col < N; col++) {
                dp[row][col] = str[row] == str[col] && dp[row + 1][col - 1];
            }
        }
        return dp;
    }


    public static void main(String[] args) {
        String s = "aab";
        System.out.println(ways(s));
    }
}
