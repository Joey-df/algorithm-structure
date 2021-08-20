package dynamic_programming.left_to_right;

/**
 * You are given coins of different denominations and a total amount of money.
 * Write a function to compute the number of combinations that make up that amount.
 * You may assume that you have infinite number of each kind of coin.
 *
 * Example 1:
 *
 * Input: amount = 5, coins = [1, 2, 5]
 * Output: 4
 * Explanation: there are four ways to make up the amount:
 * 5=5
 * 5=2+2+1
 * 5=2+1+1+1
 * 5=1+1+1+1+1
 * Example 2:
 *
 * Input: amount = 3, coins = [2]
 * Output: 0
 * Explanation: the amount of 3 cannot be made up just with coins of 2.
 * Example 3:
 *
 * Input: amount = 10, coins = [10]
 * Output: 1
 *
 * Note:
 *
 * You can assume that
 *
 * 0 <= amount <= 5000
 * 1 <= coin <= 5000
 * the number of coins is less than 500
 * the answer is guaranteed to fit into signed 32-bit integer
 * 背包问题
 * 从左往右的尝试模型
 */

public class Problem_518_Coin_Change_2 {

    public static int ways1(int[] coins, int amount) {
        if (coins == null || coins.length == 0) {
            return amount == 0 ? 1 : 0; // 没有硬币，搞定0元有1中方案，否则无方案
        }
        return process(coins, 0, amount);
    }

    //递归含义：
    //coins[index...]自由选择，搞定rest的方案数是多少？
    //[0...index-1]不用操心
    public static int process(int[] coins, int index, int rest) {
//        //rest不可能小于0
//        if (rest < 0) { //搞定负数没有方案，即0种方案
//            return 0;
//        }
        if (index == coins.length) {//没有硬币可以使用了
            //没有硬币可用，搞定0元有1种方案，否则0种方案
            return rest == 0 ? 1 : 0;
        }
        //rest > 0 && index < coins.length
        //普遍位置 每个coins[index]使用的数量k
        int ans = 0;
        for (int k = 0; k * coins[index] <= rest; k++) {
            ans += process(coins, index + 1, rest - k * coins[index]);
        }
        return ans;
    }

    //dp[i][rest]的含义：
    //coins[i...]自由选择，搞定rest的方法数是多少？
    //带有枚举行为 没有优化
    public static int dpWays1(int[] coins, int amount) {
        if (coins == null || coins.length == 0) {
            return amount == 0 ? 1 : 0; // 没有硬币，搞定0元有1中方案，否则无方案
        }

        int N = coins.length;
        int[][] dp = new int[N + 1][amount + 1];
        dp[N][0] = 1; //dp[N][1...amount]=0
        //普遍位置：从左往右，再依次从下往上
        for (int i = N - 1; i >= 0; i--) {
            for (int rest = 0; rest <= amount; rest++) {
                int ans = 0;
                for (int k = 0; k * coins[i] <= rest; k++) {
                    ans += dp[i + 1][rest - k * coins[i]];
                }
                dp[i][rest] = ans;
            }
        }
        return dp[0][amount];
    }


    //dp[i][rest]的含义：
    //coins[i...]自由选择，搞定rest的方法数是多少？
    //优化掉枚举行为之后的
    public static int dpWays2(int[] coins, int amount) {
        if (coins == null || coins.length == 0) {
            return amount == 0 ? 1 : 0; // 没有硬币，搞定0元有1中方案，否则无方案
        }

        int N = coins.length;
        int[][] dp = new int[N + 1][amount + 1];
        dp[N][0] = 1; //dp[N][1...amount]=0
        //普遍位置：从左往右，再依次从下往上
        for (int i = N - 1; i >= 0; i--) {
            for (int rest = 0; rest <= amount; rest++) {
                //[1,2,3,5,8]
                //dp[3][100] = dp[4][100] +
                //             dp[4][95] +
                //             dp[4][90] + ...
                //          =  dp[4][100] + dp[3][95]
                //dp[3][95] = dp[4][95] +
                //             dp[4][90] +
                //             dp[4][85] + ...
                dp[i][rest] = dp[i+1][rest];
                if (rest-coins[i] >= 0)
                    dp[i][rest] += dp[i][rest-coins[i]];
            }
        }
        return dp[0][amount];
    }

    public static void main(String[] args) {
        int[] coins = {1, 2, 3};
        int aim = 4;
        System.out.println(ways1(coins, aim));
        System.out.println(dpWays1(coins, aim));
        System.out.println(dpWays2(coins, aim));
    }
}
