package dynamic_programming.left_to_right;

import java.util.Arrays;

/**
 * 一辆运输快递的货车，运送的快递均放在大小不等的长方形盒子中，为了能够装载更多的快递，同时不让货车超载，
 * 需要计算最多能装多少个快递。
 * 注：快递的体积不受限制，快递数最多1000个，货车载重最大50000。
 * 输入描述：每个快递的重量weights[], 货车的载重量capacity。
 * 不需要考虑异常输入
 */
public class Express_Delivery {

    //5,10,2,11
    //20
    //递归含义：
    //weights[0...index-1]已经搞定了，不用再操心了
    //weights[index...N-1]自由选择，货车载重量还剩rest，最多能装多少个快递？
    public static int process(int[] weights, int index, int rest) {
        //如果没有货物可以选择了 或者 货车没剩余载重了 最多装0个快递
        if (index == weights.length || rest == 0) {
            return 0;
        }

        //还有货物  或者  还有容量
        int no = process(weights, index + 1, rest);
        //要index的货物
        int yes = -1;
        if (rest >= weights[index]) { //如果剩余容量还能装下第index个货物的情况下
            yes = 1 + process(weights, index + 1, rest - weights[index]);
        }
        return Math.max(yes, no);
    }

    //改动态规划
    //行列模型
    public static int dpWays(int[] weights, int C) {
        if (weights == null || weights.length == 0 || C <= 0) {
            return 0;
        }
        int N = weights.length;
        int[][] dp = new int[N + 1][C + 1];
        //dp[N][...]=0 //最后一行
        //dp[...][0]=0 //第一列
        // dp填充：从左到右，再依次从下往上
        for (int i = N - 1; i >= 0; i--) {
            for (int j = 1; j <= C; j++) {
                int no = dp[i + 1][j];
                int yes = -1;
                if (j - weights[i] >= 0) {
                    yes = 1 + dp[i + 1][j - weights[i]];
                }
                dp[i][j] = Math.max(yes, no);
            }
        }
        return dp[0][C];
    }

    //贪心
    public static int ways2(int[] weights, int capacity) {
        Arrays.sort(weights);
        int ans = 0;
        for (int i = 0; i < weights.length; i++) {
            capacity -= weights[i];
            if (capacity > 0) {
                ans++;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] weights = {5, 10, 2, 11};
        int capa = 20;
        System.out.println(process(weights, 0, capa));
        System.out.println(ways2(weights, capa));
        System.out.println(dpWays(weights, capa));
    }
}
