package dynamic_programming.row_column;

import java.util.List;

/**
 * 120. 三角形最小路径和
 * 给定一个三角形 triangle ，找出自顶向下的最小路径和。
 * 每一步只能移动到下一行中相邻的结点上。
 * 相邻的结点 在这里指的是 下标 与 上一层结点下标 相同或者等于 上一层结点下标 + 1 的两个结点。
 * 也就是说，如果正位于当前行的下标 i ，那么下一步可以移动到下一行的下标 i 或 i + 1 。
 * 示例 1：
 * 输入：triangle = [[2],[3,4],[6,5,7],[4,1,8,3]]
 * 输出：11
 * 解释：如下面简图所示：
 *    2
 *   3 4
 *  6 5 7
 * 4 1 8 3
 * 自顶向下的最小路径和为 11（即，2 + 3 + 5 + 1 = 11）。
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
