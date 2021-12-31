package dynamic_programming.left_to_right;

/**
 * 91. 解码方法
 * 一条包含字母 A-Z 的消息通过以下映射进行了 编码 ：
 * 'A' -> 1
 * 'B' -> 2
 * ...
 * 'Z' -> 26
 * 要 解码 已编码的消息，所有数字必须基于上述映射的方法，反向映射回字母（可能有多种方法）。例如，"11106" 可以映射为：
 * <p>
 * "AAJF" ，将消息分组为 (1 1 10 6)
 * "KJF" ，将消息分组为 (11 10 6)
 * 注意，消息不能分组为  (1 11 06) ，因为 "06" 不能映射为 "F" ，这是由于 "6" 和 "06" 在映射中并不等价。
 * 给你一个只含数字的 非空 字符串 s ，请计算并返回 解码 方法的 总数 。
 * 题目数据保证答案肯定是一个 32 位 的整数。
 * <p>
 * 示例 1：
 * 输入：s = "12"
 * 输出：2
 * 解释：它可以解码为 "AB"（1 2）或者 "L"（12）。
 * 示例 2：
 * 输入：s = "226"
 * 输出：3
 * 解释：它可以解码为 "BZ" (2 26), "VF" (22 6), 或者 "BBF" (2 2 6) 。
 * 示例 3：
 * 输入：s = "0"
 * 输出：0
 * 解释：没有字符映射到以 0 开头的数字。
 * 含有 0 的有效映射是 'J' -> "10" 和 'T'-> "20" 。
 * 由于没有字符，因此没有有效的方法对此进行解码，因为所有数字都需要映射。
 * 示例 4：
 * 输入：s = "06"
 * 输出：0
 * 解释："06" 不能映射到 "F" ，因为字符串含有前导 0（"6" 和 "06" 在映射中并不等价）。
 * <p>
 * 提示：
 * 1 <= s.length <= 100
 * s 只包含数字，并且可能包含前导零。
 */
//system_study.class18_23.Class19_Code02_ConvertToLetterString
public class Problem_0091_DecodeWays {

    public int numDecodings(String s) {
        if (s == null || s.length() == 0) return 0;
        return process(s.toCharArray(), 0);
    }

    //递归含义
    //当前来到index位置，str[0...index-1]已经搞定了，不用操心了，index...解法的方法数返回
    public int process(char[] str, int index) {
        if (index == str.length) {
            return 1; //表示[0...str.length-1]已经搞定了，此时找到了一种方法
        }
        //index位置还有字符
        if (str[index] == '0') {
            return 0; //如果当前位置字符是'0',那么此前所做的决定不合法
        }
        //str[index]单转
        int p1 = process(str, index + 1);
        int p2 = -1; //index index+1合在一起转
        if (index + 1 < str.length && (str[index] - '0') * 10 + (str[index + 1] - '0') <= 26) {
            p2 = process(str, index + 2);
        }
        return p1 + (p2 != -1 ? p2 : 0);
    }

    //动态规划
    public int numDecodings2(String s) {
        if (s == null || s.length() == 0) return 0;
        char[] str = s.toCharArray();
        int n = str.length;
        int[] dp = new int[n + 1];
        dp[n] = 1;
        for (int i = n - 1; i >= 0; i--) {
            if (str[i] != '0') {
                //str[index]单转
                int p1 = dp[i + 1];
                int p2 = -1; //i i+1合在一起转
                if (i + 1 < n && (str[i] - '0') * 10 + (str[i + 1] - '0') <= 26) {
                    p2 = dp[i + 2];
                }
                dp[i] = p1 + (p2 != -1 ? p2 : 0);
            }
        }
        return dp[0];
    }

}
