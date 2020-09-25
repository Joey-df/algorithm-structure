package dynamic_programming.left_to_right;

/**
 * Given a string s, partition s such that every substring of the partition is a palindrome.
 * 
 * Return the minimum cuts needed for a palindrome partitioning of s.
 * 
 * 
 * 
 * Example 1:
 * 
 * Input: s = "aab"
 * Output: 1
 * Explanation: The palindrome partitioning ["aa","b"] could be produced using 1 cut.
 * Example 2:
 * 
 * Input: s = "a"
 * Output: 0
 * Example 3:
 * 
 * Input: s = "ab"
 * Output: 1
 * 
 * 
 * Constraints:
 * 
 * 1 <= s.length <= 2000
 * s consists of lower-case English letters only.
 * 
 * 给定字符串s，至少切几刀，使得到的部分都是回文串
 */
public class Problem_132_Palindrome_Partitioning_II {

    //s[index...N-1]
    //递归含义：s从index开始到结尾，切分得到的最少回文串有几个？
    //从左往右的尝试模型：尝试每一个开始位置，可以把可能性列全
    public static int process(String s, int index) {
        if (index == s.length()) {
            // s[index...N-1]范围上没有字符，0个回文串
            return 0;
        }
        boolean[][] help = isPalindrome(s);
        int ans = Integer.MAX_VALUE;
        //index<s.length()
        for (int end = index; end < s.length(); end++) {
            //枚举从index位置开始，每一个结束的位置
            //index...index
            //index...index+1
            //index...index+2
            //index...index+3
            // ...
            if (help[index][end]) {
                ans = Math.min(ans, process(s, end + 1) + 1);
            }
        }
        return ans;
    }

    //改动态规划
    public static int dpWay(String s) {
        int N = s.length();
        int[] dp = new int[N + 1];
        //dp[N] = 0;
        boolean[][] help = isPalindrome(s);
        for (int i = N - 1; i >= 0; i--) {
            dp[i] = Integer.MAX_VALUE;
            //index<s.length()
            for (int end = i; end < s.length(); end++) {
                if (help[i][end]) {
                    dp[i] = Math.min(dp[i], dp[end + 1] + 1);
                }
            }
        }
        return dp[0];
    }

    //给定字符串s 长度大于0
    //返回在任意范围上是否是回文
    //范围上的尝试模型，作为一个子过程
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
        String s = "1234567";
        System.out.println(process(s, 0) - 1);
        System.out.println(dpWay(s) - 1);
    }
}
