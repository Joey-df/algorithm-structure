package dynamic_programming.left_to_right;

/**
 * 0-1背包问题
 * 从左往右的尝试模型
 * 给定两个长度都为N的数组weights和values，
 * weights[i]和values[i]分别代表 i号物品的重量和价值。
 * 给定一个正数bag，表示一个载重bag的袋子，
 * 你装的物品不能超过这个重量。
 * 返回你能装下最多的价值是多少?
 */
public class Knapsack {

    //暴力尝试
    //w[], v[] 固定参数
    //index-1及其往前已经决定好了
    //剩余载重空间 rest
    //[index...] 自由选择，得到的最大价值是多少？
    public static int process(int w[], int[] v, int index, int rest) {
        if (index == w.length || rest <=0) {
            return 0; //没货了 或者 没容量了
        }
        //有货有容量
        //不要index位置的货物
        int res = process(w, v, index + 1, rest);
        if (rest >= w[index]) {//剩余容量能装下index位置的货物时
            res = Math.max(res, process(w, v, index + 1, rest - w[index]) + v[index]); // 要index位置的货物
        }
        return res;
    }

    // C 表示背包容量
    // 暴力递归改动态规划
    public static int dp(int w[], int[] v, int C) {
        int N = w.length;
        int[][] dp = new int[N+1][C+1];
        // dp[N][...] = 0 //最后一行
        // dp[...][0] = 0 //第一列
        // dp填充：从左到右，再依次从下往上
        for (int index = N-1; index >= 0 ; index--) {
            for (int rest = 1; rest <= C; rest++) {
                //dp[index][rest] = ?
                int res = dp[index + 1][rest];
                if (rest >= w[index]) {//剩余容量能装下index位置的货物时
                    res = Math.max(res, dp[index + 1][rest - w[index]] + v[index]); // 要index位置的货物
                }
                dp[index][rest] = res;
            }
        }
        return dp[0][C]; //右上角的值
    }


    public static void main(String[] args) {
        int[] weights = { 3, 2, 4, 7 };
        int[] values = { 5, 6, 3, 19 };
        int bag = 11;
        System.out.println(process(weights, values, 0, bag));
        System.out.println(dp(weights, values ,bag));
    }

}
