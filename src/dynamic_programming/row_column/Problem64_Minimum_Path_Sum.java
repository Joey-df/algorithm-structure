package dynamic_programming.row_column;

/**
 * Given a m x n grid filled with non-negative numbers,
 * find a path from top left to bottom right which minimizes the sum of all numbers along its path.
 * 从左上角到右下角的最小路径和 (规定每次只能往后 或 往下走)
 * Note: You can only move either down or right at any point in time.
 * <p>
 * Example:
 * <p>
 * Input:
 * [
 * [1,3,1],
 * [1,5,1],
 * [4,2,1]
 * ]
 * Output: 7
 * Explanation: Because the path 1→3→1→1→1 minimizes the sum.
 */
public class Problem64_Minimum_Path_Sum {

    /**
     * 递归含义：从[0,0]出发到达[i,j]位置最小的路径和是多少？
     *
     * @param grid 输入的网格，固定不变
     * @param i
     * @param j    i,j表示要来到的位置的坐标
     * @return
     */
    public static int process(int[][] grid, int i, int j) {
        if (i == 0 && j == 0) {//表示从[0,0]出发到达[0,0]位置最小的路径和是多少？
            return grid[0][0];
        }
        //走到这里说明i,j不同时为0
        if (i == 0) { // 表示在第一行，机器人只向右运动
            return process(grid, i, j - 1) + grid[i][j]; //左边的最小路径和 + 当前位置的值
        }
        if (j == 0) { // 表示在第一列，机器人只向下运动
            return process(grid, i - 1, j) + grid[i][j]; //上边的最小路径和 + 当前位置的值
        }
        //走到这里说明i!=0 && j!=0
        //到达左边 和 到达上边最小路径和 + 当前位置的值
        return Math.min(process(grid, i - 1, j), process(grid, i, j - 1)) + grid[i][j];
    }

    /**
     * dp[i][j]的含义：从[0,0]出发到达[i,j]位置最小的路径和是多少？
     *
     * @param grid 输入的网格，固定不变
     * @return
     */
    public static int dpWay(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] dp = new int[m][n];
        dp[0][0] = grid[0][0];
        //第一行
        for (int j = 1; j < n; j++) {
            dp[0][j] = dp[0][j - 1] + grid[0][j];
        }
        //第一列
        for (int i = 1; i < m; i++) {
            dp[i][0] = dp[i - 1][0] + grid[i][0];
        }

        //普遍位置
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + grid[i][j];
            }
        }
        return dp[m - 1][n - 1];
    }

}
