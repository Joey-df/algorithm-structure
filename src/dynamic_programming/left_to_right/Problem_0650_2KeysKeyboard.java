package dynamic_programming.left_to_right;

/**
 * 650. 只有两个键的键盘
 * 最初记事本上只有一个字符 'A' 。你每次可以对这个记事本进行两种操作：
 *
 * Copy All（复制全部）：复制这个记事本中的所有字符（不允许仅复制部分字符）。
 * Paste（粘贴）：粘贴 上一次 复制的字符。
 * 给你一个数字 n ，你需要使用最少的操作次数，在记事本上输出 恰好 n 个 'A' 。返回能够打印出 n 个 'A' 的最少操作次数。
 *
 * 示例 1：
 * 输入：3
 * 输出：3
 * 解释：
 * 最初, 只有一个字符 'A'。
 * 第 1 步, 使用 Copy All 操作。
 * 第 2 步, 使用 Paste 操作来获得 'AA'。
 * 第 3 步, 使用 Paste 操作来获得 'AAA'。
 *
 * 示例 2：
 * 输入：n = 1
 * 输出：0
 *
 * 提示：
 * 1 <= n <= 1000
 */
public class Problem_0650_2KeysKeyboard {

    //Elegant solution.
    //Allow me to explain what is being done here.
    //As per the keyboard operations:
    //to get AA from A we need 2 additional steps (copy-all and then paste)
    //to get AAA from A we need 3 additional steps (copy-all, then paste, then again paste)
    //
    //For generating AAAA we need 2 additional steps from AA.
    //however, to get AAAAAAAA, the most optimal way would be from AAAA, with 2 additional steps (copy-all then paste)
    //Essentially, we find the next smaller length sequence (than the one under consideration) which can be copied and then pasted over multiple times to generate the desired sequence.
    //The moment we find a length that divides our required sequence length perfectly, then we don't need to check for any smaller length sequences.
    public int minSteps(int n) {
        int[] dp = new int[n+1];

        for (int i = 2; i <= n; i++) {
            dp[i] = i;
            for (int j = i-1; j > 1; j--) {
                // if sequence of length 'j' can be pasted multiple times to get length 'i' sequence
                if (i % j == 0) {
                    // we just need to paste sequence j (i/j - 1) times,
                    // hence additional (i/j) times since we need to copy it first as well.
                    // we don't need checking any smaller length sequences
                    dp[i] = dp[j] + (i/j);
                    break;
                }
            }
        }
        return dp[n];
    }
}
