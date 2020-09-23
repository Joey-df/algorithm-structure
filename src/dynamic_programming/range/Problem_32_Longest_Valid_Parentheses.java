package dynamic_programming.range;

/**
 * Given a string containing just the characters '(' and ')',
 * find the length of the longest valid (well-formed) parentheses substring.
 * 
 * Example 1:
 * 
 * Input: "(()"
 * Output: 2
 * Explanation: The longest valid parentheses substring is "()"
 * Example 2:
 * 
 * Input: ")()())"
 * Output: 4
 * Explanation: The longest valid parentheses substring is "()()"
 *
 * 范围上的尝试模型解法
 * 时间复杂度 O(N^2)
 * 空间复杂度 O(N^2)
 */
public class Problem_32_Longest_Valid_Parentheses {

    public static int ways1(String s) {
        if ("".equals(s) || s == null || s.length() == 0) {
            return 0;
        }
        //s.length()>0才有讨论的意义
        char[] str = s.toCharArray();
        return process(str, 0, str.length - 1);
    }

    //使用范围上的尝试模型解
    //该递归函数被调用的潜台词：str.length>1
    //递归含义：
    //str[L...R]范围上最长有效括号有多长？
    public static int process(char[] str, int L, int R) {
        //str[L...R]范围上只有一个字符
        if (L == R) return 0;
        //str[L...R]范围上只有2个字符时，只有"()"有效长度为2，否则都为0
        if (L + 1 == R) return (str[L] == '(' && str[R] == ')') ? 2 : 0;
        //str[L...R]范围上的字符数量 >= 3
        int ans = Integer.MIN_VALUE;
        //1、最长有效括号lvp 以L开头 & 但不以R结尾，如"(())))))"
        int p1 = process(str, L, R - 1);
        ans = Math.max(ans, p1);
        //2、最长有效括号lvp 不以L开头 & 但以R结尾，如"((((())"
        int p2 = process(str, L + 1, R);
        ans = Math.max(ans, p2);
        //3、最长有效括号lvp 不以L开头 & 也不以R结尾，如")(((()("
        int p3 = process(str, L + 1, R - 1);
        ans = Math.max(ans, p3);
        //4、最长有效括号lvp 以L开头 & 同时以R结尾，如"((())())"
        if (
                (str[L] == '(' && str[R] == ')')
                        && (isValid(str, L + 1, R - 1) || isValid(str, L, R))
        ) {
            int p4 = R - L + 1;
            ans = Math.max(ans, p4);
        }
        return ans;
    }

    /**
     * 判断给定str[L,R]范围上是否有效
     *
     * @param str 只由'('和')'组成的字符串 如"((()))()"
     * @return
     */
    private static boolean isValid(char[] str, int L, int R) {
        int count = 0;
        for (int i = L; i <= R; i++) {
            if (str[i] == '(') {
                count++;
            } else { //str[i]==')'
                count--;
            }
            if (count < 0) return false;
        }
        return count == 0;
    }


    public static int ways2(String s) {
        if ("".equals(s) || s == null || s.length() == 0) {
            return 0;
        }
        return dpWays(s.toCharArray());
    }

    //改动态规划
    //dp[i][j]的含义：
    //str[i...j]范围上，最长有效括号字符串有多长
    public static int dpWays(char[] str) {
        int N = str.length;
        int[][] dp = new int[N][N];

        //第一条对角线全是0，不用填，默认就是0
        //填第二条对角线
        for (int i = 1; i < N; i++) {
            dp[i - 1][i] = str[i - 1] == '(' && str[i] == ')' ? 2 : 0;
        }
        //普遍位置，依赖左、下、右下角
        //所以从左往右，再依次从下往上
        for (int i = N - 3; i >= 0; i--) {
            for (int j = i + 2; j < N; j++) {
                dp[i][j] = Math.max(dp[i][j - 1], dp[i + 1][j]); //左、下
                //因为 当前>=下 && 下>=左 ==> 当前>=左下，所以下面代码可以省略
                //dp[i][j] = Math.max(dp[i][j],dp[i+1][j-1]);
                if ((str[i] == '(' && str[j] == ')')
                        && (isValid(str, i + 1, j - 1) || isValid(str, i, j))) {
                    dp[i][j] = Math.max(dp[i][j], j - i + 1);
                }
            }
        }
        return dp[0][N - 1];
    }
    

    public static void main(String[] args) {
        String s = "(((())))";
        System.out.println(ways1(s));
        System.out.println(ways2(s));
    }
}
