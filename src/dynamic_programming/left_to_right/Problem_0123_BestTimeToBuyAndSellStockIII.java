package dynamic_programming.left_to_right;

//给定一个数组，它的第 i 个元素是一支给定的股票在第 i 天的价格。
//设计一个算法来计算你所能获取的最大利润。你最多可以完成 两笔 交易。
//注意: 你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
//https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-iii
/**带枚举优化的动态规划**/

public class Problem_0123_BestTimeToBuyAndSellStockIII {

    public static int maxProfit(int[] prices, int k) {
        if (prices == null || prices.length < 2) return 0;
        return process(prices, k);
    }

    //动态规划
    //一个样本做行，一个样本左列的对应模型
    //dp[i][j] 表示prices[0...i]范围上做k次交易，获取的最大利润是多少？
    public static int process(int[] prices, int k) {
        int n = prices.length;
        int[][] dp = new int[n][k + 1];
        //第一行表示：prices[0]上的值做k次交易  全0
        //dp[...][0] = 0;
        //第一列表示：prices[0...i]上的值做0次交易  全0
        //普遍位置
        // 递推方向：dp[i][j] -> dp[i+1][j]
        // 从上往下 再依次从左往右
        int ans = 0;
        for (int j = 1; j <= k; j++) {
            for (int i = 1; i < n; i++) {
                //(1)第i位置的不参与交易：dp[i-1][j]
                dp[i][j] = dp[i - 1][j];
                //(2)第i位置的参与最后一次交易
                //   枚举最后一次股票的买入时机：
                int p = dp[i][j - 1] - prices[i];
                //枚举行为
                for (int c = i - 1; c >= 0; c--) {
                    p = Math.max(p, dp[c][j - 1] - prices[c]);
                }
                //第i位置 参与交易 （参与最后一次的卖出）
                dp[i][j] = Math.max(dp[i][j], p + prices[i]);
                ans = Math.max(ans, dp[i][j]);
            }
        }
        return ans;
    }

    //动态规划
    //一个样本做行，一个样本左列的对应模型
    //dp[i][j] 表示prices[0...i]范围上做k次交易，获取的最大利润是多少？
    public static int bestWay(int[] prices, int k) {
        int[][] dp = new int[prices.length][k + 1];
        //一个N长度的数组中上坡的个数最大为N/2。  如1212  有两个上坡
        if (k >=prices.length/2) { //Leetcode stock II
            int ans = 0;
            for (int i = 1; i < prices.length; i++) {
                ans += prices[i]>prices[i-1] ? prices[i]-prices[i-1] : 0;
            }
            return ans;
        }
        int ans = 0;
        for (int j = 1; j <= k; j++) {
            int t = dp[0][j-1] - prices[0]; //注意
            for (int i = 1; i < prices.length; i++) {
                dp[i][j] = dp[i - 1][j]; //第i位置的不参与交易(prices[0...i-1]上做j次交易)
                t = Math.max(t, dp[i][j - 1] - prices[i]);
                dp[i][j] = Math.max(dp[i][j], t + prices[i]);
                ans = Math.max(ans, dp[i][j]);
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] prices = {7,60,124,3,100,199};
        int k=1;
        int maxProfit = maxProfit(prices, k);
        System.out.println("maxProfit = " + maxProfit);
        int bestWay = bestWay(prices, k);
        System.out.println("bestWay = " + bestWay);
    }
}
