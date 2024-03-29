package dynamic_programming.left_to_right;

/**
 * 714. 买卖股票的最佳时机含手续费
 * 给定一个整数数组 prices，其中第 i 个元素代表了第 i 天的股票价格 ；整数 fee 代表了交易股票的手续费用。
 * 你可以无限次地完成交易，但是你每笔交易都需要付手续费。如果你已经购买了一个股票，在卖出它之前你就不能再继续购买股票了。
 * 返回获得利润的最大值。
 * 注意：这里的一笔交易指买入持有并卖出股票的整个过程，每笔交易你只需要为支付一次手续费。
 *
 * 示例 1：
 * 输入：prices = [1, 3, 2, 8, 4, 9], fee = 2
 * 输出：8
 * 解释：能够达到的最大利润:
 * 在此处买入 prices[0] = 1
 * 在此处卖出 prices[3] = 8
 * 在此处买入 prices[4] = 4
 * 在此处卖出 prices[5] = 9
 * 总利润: ((8 - 1) - 2) + ((9 - 4) - 2) = 8
 * 示例 2：
 * 输入：prices = [1,3,7,5,10,3], fee = 3
 * 输出：6
 *
 * 提示：
 * 1 <= prices.length <= 5 * 10^4
 * 1 <= prices[i] < 5 * 10^4
 * 0 <= fee < 5 * 10^4
 */
public class Problem_0714_BestTimeToBuyAndSellStockWithTransactionFee {

    //无限次交易，带手续费
    public static int maxProfit(int[] prices, int fee) {
        if (prices==null || prices.length < 2) {
            return 0;
        }
        //买的时候扣fee，和卖的时候扣fee是一样的
        int bestbuy = -prices[0] - fee;//[0,cur]做无限次交易，最后一次必须是buy动作，最大收益
        int bestsell = 0; //[0,cur]做无限次交易，最后一次必须是sell动作，最大收益
        int n = prices.length;
        for (int i = 1; i < n; i++) {
            bestbuy = Math.max(bestbuy, bestsell - prices[i] - fee);
            bestsell = Math.max(bestsell, bestbuy + prices[i]);
        }
        return bestsell;
    }

    public static void main(String[] args) {
        int[] prices = {1,3,7,5,10,3};
        int fee = 3;
        System.out.println(maxProfit(prices, fee));
    }

}
