package dynamic_programming.left_to_right;

/**
 * 343. 整数拆分
 * 给定一个正整数 n，将其拆分为至少两个正整数的和，并使这些整数的乘积最大化。 返回你可以获得的最大乘积。
 * <p>
 * 示例 1:
 * 输入: 2
 * 输出: 1
 * 解释: 2 = 1 + 1, 1 × 1 = 1。
 * <p>
 * 示例 2:
 * 输入: 10
 * 输出: 36
 * 解释: 10 = 3 + 3 + 4, 3 × 3 × 4 = 36。
 * 说明: 你可以假设 n 不小于 2 且不大于 58。
 */
public class Problem_0343_IntegerBreak {

    //将 n 进行分割得到的乘积最大值
    public static int integerBreak(int n) {
        if (n == 0) return 0;
        if (n == 1) return 1;
        if (n == 2) return 1;
        int max = 0;
        for (int i = 1; i < n; i++) {
            max = Math.max(max,
                    Math.max(
                            i * integerBreak(n - i),
                            i * (n - i)
                    )
            );
        }
        return max;
    }

    public static int dp(int n) {
        if (n == 0) return 0;
        if (n == 1) return 1;
        if (n == 2) return 1;
        int[] dp = new int[n+1];
        for (int i = 3; i <= n; i++) {
            int max = 0;
            for (int cur = 1; cur < i; cur++) {
                max = Math.max(max,
                        Math.max(
                                cur * dp[i - cur],
                                cur * (i - cur)
                        )
                );
            }
            dp[i] = max;
        }
        return dp[n];
    }

    public static void main(String[] args) {
        System.out.println(integerBreak(10));
    }

}
