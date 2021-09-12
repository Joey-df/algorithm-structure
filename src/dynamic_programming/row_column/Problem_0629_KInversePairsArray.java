package dynamic_programming.row_column;

/**
 * 629. K个逆序对数组
 * 给出两个整数 n 和 k，找出所有包含从 1 到 n 的数字，且恰好拥有 k 个逆序对的不同的数组的个数。
 * 逆序对的定义如下：对于数组的第i个和第 j个元素，如果满足 i < j 且 a[i] > a[j]，则其为一个逆序对；否则不是。
 * 由于答案可能很大，只需要返回 答案 mod 10^9 + 7 的值。
 * <p>
 * 示例 1:
 * 输入: n = 3, k = 0
 * 输出: 1
 * 解释:
 * 只有数组 [1,2,3] 包含了从1到3的整数并且正好拥有 0 个逆序对。
 * <p>
 * 示例 2:
 * 输入: n = 3, k = 1
 * 输出: 2
 * 解释:
 * 数组 [1,3,2] 和 [2,1,3] 都有 1 个逆序对。
 * <p>
 * Constraints:
 * 1 <= n <= 1000
 * 0 <= k <= 1000
 */
//coding_for_great_offer.class10.Code03_KInversePairs
public class Problem_0629_KInversePairsArray {

    //带有枚举行为优化的dp
    public static int kInversePairs(int n, int k) {
        if (n < 1 || k < 0) {
            return 0;
        }
        int mod = (int) (Math.pow(10, 9) + 7);
        long[][] dp = new long[n + 1][k + 1];
        dp[0][0] = 1;
        for (int j = 1; j <= n; j++) {
            dp[j][0] = 1; //[1...j]上，0个逆序对有1个，如1 2 3严格递增的序列
        }
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= k; j++) {
                if (j < i) {
                    dp[i][j] = (dp[i - 1][j] + dp[i][j - 1]) % mod;
                } else { //j >= i
                    dp[i][j] = (dp[i - 1][j] + dp[i][j - 1] - dp[i - 1][j - i] + mod) % mod;
                }
            }
        }
        return (int) dp[n][k];
    }

}
