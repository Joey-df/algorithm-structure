package dynamic_programming.left_to_right;

/**
 * 188. 买卖股票的最佳时机 IV
 * 给定一个整数数组 prices ，它的第 i 个元素 prices[i] 是一支给定的股票在第 i 天的价格。
 * 设计一个算法来计算你所能获取的最大利润。你最多可以完成 k 笔交易。
 * 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
 *
 * 示例 1：
 * 输入：k = 2, prices = [2,4,1]
 * 输出：2
 * 解释：在第 1 天 (股票价格 = 2) 的时候买入，在第 2 天 (股票价格 = 4) 的时候卖出，这笔交易所能获得利润 = 4-2 = 2 。
 *
 * 示例 2：
 * 输入：k = 2, prices = [3,2,6,5,0,3]
 * 输出：7
 * 解释：在第 2 天 (股票价格 = 2) 的时候买入，在第 3 天 (股票价格 = 6) 的时候卖出, 这笔交易所能获得利润 = 6-2 = 4 。
 *      随后，在第 5 天 (股票价格 = 0) 的时候买入，在第 6 天 (股票价格 = 3) 的时候卖出, 这笔交易所能获得利润 = 3-0 = 3 。
 *
 * 提示：
 *
 * 0 <= k <= 100
 * 0 <= prices.length <= 1000
 * 0 <= prices[i] <= 1000
 */
public class Problem_0188_BestTimeToBuyAndSellStockIV {

    public int maxProfit1(int k, int[] prices) {
        if (prices==null || prices.length==0) {
            return 0;
        }
        int n = prices.length;
        if (k >= (n>>1)) {
            return maxProfitii(prices);
        }
        //k < n/2
        int[][] dp = new int[n][k+1];//dp[i][j]: prices[0,i]上做不超过j次交易获得最大利润
        //第一行：prices[0,0]上做不超过j次交易获得的最大利润，全是0
        //第一列：prices[0,i]上做0次交易，最大利润，全是0
        //普通位置：整体从左往右，每列依次从上往下填
        for (int j = 1; j <= k; j++) {
            for (int i = 1; i < n; i++) {
                int p1 = dp[i-1][j]; // [i]不参与
                //[i]参与最后一次交易的卖出时机，需要枚举买入时机
                int p2 = dp[i][j-1] + prices[i] - prices[i]; //i买i卖
                for (int l = i-1; l >= 0; l--) {
                    p2 = Math.max(p2, dp[l][j-1] + prices[i] - prices[l]);
                }
                dp[i][j] = Math.max(p1, p2);
            }
        }
        return dp[n-1][k];
    }


    //优化后的版本
    public int maxProfit2(int k, int[] prices) {
        if (prices==null || prices.length==0) {
            return 0;
        }
        int n = prices.length;
        if (k >= (n>>1)) {
            return maxProfitii(prices);
        }
        //k < n/2
        int[][] dp = new int[n][k+1];//dp[i][j]: prices[0,i]上做不超过j次交易获得最大利润
        //第一行：prices[0,0]上做不超过j次交易获得的最大利润，全是0
        //第一列：prices[0,i]上做0次交易，最大利润，全是0
        //普通位置：整体从左往右，每列依次从上往下填
        for (int j = 1; j <= k; j++) {
            int p = dp[0][j-1] - prices[0]; //注意
            for (int i = 1; i < n; i++) {
                int p1 = dp[i-1][j]; // [i]不参与
                p = Math.max(p, dp[i][j-1] - prices[i]);
                int p2 = p + prices[i];
                dp[i][j] = Math.max(p1, p2);
            }
        }
        return dp[n-1][k];
    }


    //做无限次交易，获得的最大利润
    public int maxProfitii(int[] prices) {
        if (prices==null || prices.length==0) return 0;
        int ans = 0;
        int n = prices.length;
        for (int i = 1; i < n; i++) {
            ans += Math.max(prices[i]-prices[i-1], 0);
        }
        return ans;
    }

}
