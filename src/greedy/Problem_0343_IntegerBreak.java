package greedy;

/**
 * 343. 整数拆分
 * 给定一个正整数 n，将其拆分为至少两个正整数的和，并使这些整数的乘积最大化。 返回你可以获得的最大乘积。
 * <p>
 * 示例 1:
 * 输入: 2
 * 输出: 1
 * 解释: 2 = 1 + 1, 1 × 1 = 1。
 * 示例 2:
 * 输入: 10
 * 输出: 36
 * 解释: 10 = 3 + 3 + 4, 3 × 3 × 4 = 36。
 * 说明: 你可以假设 n 不小于 2 且不大于 58。
 */
public class Problem_0343_IntegerBreak {

    public int integerBreak(int n) {
        if (n == 0) return 0;
        if (n == 1) return 1;
        if (n == 2) return 1;
        int ans = 0;
        for (int i = 1; i < n; i++) {
            ans = Math.max(ans, Math.max(i * (n - i), i * integerBreak(n - i)));
        }
        return ans;
    }

    public int integerBreak2(int n) {
        //dp[i]:i拆分成至少两个整数之和，使得这些数乘积最大，最大的乘积是多少
        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = 1;
        dp[2] = 1;
        for (int i = 3; i <= n; i++) {
            int ansi = 0;
            for (int cur = 1; cur < i; cur++) {
                ansi = Math.max(ansi,
                        Math.max(
                                cur * (i - cur),
                                cur * dp[i - cur]));
            }
            dp[i] = ansi;
        }
        return dp[n];
    }

}
