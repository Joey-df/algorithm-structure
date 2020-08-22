package dynamic_programming;

//给定一个数组，它的第 i 个元素是一支给定的股票在第 i 天的价格。
//设计一个算法来计算你所能获取的最大利润。你最多可以完成 两笔 交易。
//注意: 你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-iii

public class Best_Time_To_Buy_And_Sell_Stock_III {

    public static int maxProfit(int[] prices) {
        if(prices==null || prices.length < 2) return 0;
        return process(prices, 2);
    }

    //动态规划
    //一个样本做行，一个样本左列的对应模型
    //dp[i][j] 表示prices[0...i]范围上做k次交易，获取的最大利润是多少？
    public static int process(int prices[], int k){
        int[][] dp = new int[prices.length][k+1];
        //第一行表示：prices[0]上的值做k次交易  全0
        //dp[...][0] = 0;
        //第一列表示：prices[0...i]上的值做0次交易  全0

        //普遍位置：
        //dp[i][j]
        //(1)第i位置的不参与交易：dp[i-1][j]
        //(2)第i位置的参与最后一次交易
        //   枚举最后一次股票的买入时机：
        //   dp[i][j-1] + prices[i] - prices[i] //prices[0..i]上做了k次交易，闲的蛋疼在i位置买再在i位置卖
        //   dp[i-1][j-1] + prices[i] - prices[i-1]
        //   dp[i-2][j-1] + prices[i] - prices[i-2]
        //   dp[0][j-1] + prices[i] - prices[0]

        // 递推方向：dp[i][j] -> dp[i+1][j]
        // 从上往下 再依次从左往右
        int ans = 0;
        for(int j = 1; j <=k; j++) {
            for(int i=1;i<prices.length;i++) {
                dp[i][j] = dp[i-1][j]; //第i位置的不参与交易(prices[0...i-1]上做j次交易)
                int t = dp[i][j-1] - prices[i];
                //枚举行为
                for(int c = i-1; c>=0; c--) {
                    t = Math.max(t, dp[c][j-1]-prices[c]);
                }
                //第i位置 参与交易 （参与最有一次的卖出）
                dp[i][j] = Math.max(dp[i][j], t+prices[i]);
                ans = Math.max(ans, dp[i][j]);
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int prices[] = {3,3,5,0,0,3,1,5,60,4};
        int maxProfit = maxProfit(prices);
        System.out.println("maxProfit = " + maxProfit);
    }
}
