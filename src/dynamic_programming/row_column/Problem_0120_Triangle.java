package dynamic_programming.row_column;

import java.util.List;

/**
 * Given a triangle, find the minimum path sum from top to bottom.
 * Each step you may move to adjacent numbers on the row below.
 * <p>
 * For example, given the following triangle
 * <p>
 * [
 * [2],
 * [3,4],
 * [6,5,7],
 * [4,1,8,3]
 * ]
 * The minimum path sum from top to bottom is 11 (i.e., 2 + 3 + 5 + 1 = 11).
 */
public class Problem_0120_Triangle {

    //dp[i][j]的含义：从顶点出发，到三角形中[i][j]位置的最小路径和是多少？
    private static int dpWay(List<List<Integer>> triangle) {
        if (triangle.size() == 0) return 0;
        if (triangle.size() == 1) return triangle.get(0).get(0);
        int n = triangle.size();
        //正方形矩阵的左下半区
        int[][] dp = new int[n][n];
        dp[0][0] = triangle.get(0).get(0);
        for (int i = 1; i < n; i++) { //行
            dp[i][0] = dp[i - 1][0] + triangle.get(i).get(0); //左腰线（第一列）
            dp[i][i] = dp[i - 1][i - 1] + triangle.get(i).get(i); //右腰线（对角线）
        }
        //普遍位置
        for (int i = 2; i < n; i++) {
            for (int j = 1; j < i; j++) {
                //上一层两个相邻位置（左上和上）的最小值 + 当前位置的值
                dp[i][j] = Math.min(dp[i - 1][j], dp[i - 1][j - 1]) + triangle.get(i).get(j);
            }
        }
        int ans = Integer.MAX_VALUE;
        //dp的最后一层收集答案
        for (int i = 0; i < dp[n - 1].length; i++) {
            ans = Math.min(ans, dp[n - 1][i]);
        }
        return ans;
    }
}
