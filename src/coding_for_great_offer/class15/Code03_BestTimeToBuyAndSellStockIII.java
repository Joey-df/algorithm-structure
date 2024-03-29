package coding_for_great_offer.class15;

//leetcode 123
public class Code03_BestTimeToBuyAndSellStockIII {

    public static int maxProfit(int[] prices) {
        if (prices == null || prices.length < 2) {
            return 0;
        }
        int ans = 0;
        int doneOnceMinusBuyMax = -prices[0]; //做一次交易，并且最后一次是buy动作
        int doneOnceMax = 0; //做一次交易，并且最后一次是sell动作
        int min = prices[0]; //当前位置找到的最小值
        for (int i = 1; i < prices.length; i++) {
            min = Math.min(min, prices[i]);
            ans = Math.max(ans, doneOnceMinusBuyMax + prices[i]);
            doneOnceMax = Math.max(doneOnceMax, prices[i] - min);
            doneOnceMinusBuyMax = Math.max(doneOnceMinusBuyMax, doneOnceMax - prices[i]);
        }
        return ans;
    }

}
