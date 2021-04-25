package routine;

/**
 * You are given coins of different denominations and a total amount of money amount.
 * Write a function to compute the fewest number of coins that you need to make up that amount.
 * If that amount of money cannot be made up by any combination of the coins, return -1.
 * <p>
 * You may assume that you have an infinite number of each kind of coin.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: coins = [1,2,5], amount = 11
 * Output: 3
 * Explanation: 11 = 5 + 5 + 1
 * Example 2:
 * <p>
 * Input: coins = [2], amount = 3
 * Output: -1
 * Example 3:
 * <p>
 * Input: coins = [1], amount = 0
 * Output: 0
 * Example 4:
 * <p>
 * Input: coins = [1], amount = 1
 * Output: 1
 * Example 5:
 * <p>
 * Input: coins = [1], amount = 2
 * Output: 2
 */
public class Problem_322_Coin_Change {

    //dp[i][j]的含义：coins[0,i]自由选择组成aim，至少需要多少个硬币
    public static int coinChange(int[] coins, int aim) {
        if (coins == null || coins.length == 0 || aim <= 0) {
            return 0;
        }
        int[][] dp = new int[coins.length][aim + 1];
        //第一行：coins[0,0]自由选择，组成x元至少需要多少个硬币
        for (int x = 1; x <= aim; x++) {
            dp[0][x] = (x % coins[0] == 0) ? (x / coins[0]) : -1;
        }
        //第一列；coins[0,i]自由选择组成0元需要0个硬币，默认就是0不用单独设置
        //普遍位置
        for (int i = 1; i < coins.length; i++) {
            for (int j = 1; j < aim + 1; j++) {
                dp[i][j] = Integer.MAX_VALUE;
                if (dp[i - 1][j] != -1) {
                    dp[i][j] = dp[i - 1][j];
                }
                if (j - coins[i] >= 0 && dp[i][j - coins[i]] != -1) {
                    dp[i][j] = Math.min(dp[i][j], dp[i][j - coins[i]] + 1);
                }
                if (dp[i][j] == Integer.MAX_VALUE) {
                    dp[i][j] = -1;
                }
            }
        }
        return dp[coins.length - 1][aim];
    }

    public static void main(String[] args) {
        System.out.println(coinChange(new int[]{1,2,5},11));
    }
}
