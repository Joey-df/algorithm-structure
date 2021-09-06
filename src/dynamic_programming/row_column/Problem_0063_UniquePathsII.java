package dynamic_programming.row_column;

/**
 * A robot is located at the top-left corner of a m x n grid (marked 'Start' in the diagram below).
 * <p>
 * The robot can only move either down or right at any point in time. The robot is trying to reach the bottom-right corner of the grid (marked 'Finish' in the diagram below).
 * <p>
 * Now consider if some obstacles are added to the grids. How many unique paths would there be?
 * <p>
 * 带障碍的网格
 */

//一个样本做行一个样本做列的对应模型
public class Problem_0063_UniquePathsII {


    /**
     * 递归含义：机器人从[0,0]出发，到达[i,j]的方法数
     *
     * @param grid 给定的网格，固定不变
     * @param i,j  [i,j]表示机器人要到达的位置
     * @return
     */
    public static int process(int[][] grid, int i, int j) {
        if (i == 0 && j == 0) {
            return grid[i][j] == 0 ? 1 : 0; // 表示从[0,0]出发，到达[0,0]的方法数
        }
        //走到这里表示i、j不同时为0
        if (i == 0) { //表示第一行，机器人只往右走
            // 前面只要出现过1就为0
            // 前一个位置可达并且当前位置为0，[i,j]可达，否则不可达
            return (process(grid, i, j - 1) == 1 && grid[i][j] == 0) ? 1 : 0;
        }
        if (j == 0) { //表示第一列，机器人只向下运动
            // 上一个位置可达并且当前位置为0，[i,j]可达，否则不可达
            return (process(grid, i - 1, j) == 1 && grid[i][j] == 0) ? 1 : 0;
        }
        //走到这里说明 i != 0 && j != 0  普遍位置
        if (grid[i][j] == 1) { //当前位置为1不可达
            return 0;
        }
        //当前位置为0，分情况考虑
        int fromUp = grid[i - 1][j] == 0 ? process(grid, i - 1, j) : 0;
        int fromLeft = grid[i][j - 1] == 0 ? process(grid, i, j - 1) : 0;
        return fromUp + fromLeft;
    }

    /**
     * dp[i][j]的含义：机器人从[0,0]出发，到达[i,j]位置的方法数是多少？
     *
     * @param grid 输入的网格，固定不变
     * @return
     */
    public static int dpWay(int grid[][]) {
        int m = grid.length; //行数
        int n = grid[0].length; //列数
        int[][] dp = new int[m][n];
        dp[0][0] = grid[0][0] == 0 ? 1 : 0;
        //第一行
        for (int j = 1; j < n; j++) {
            dp[0][j] = (dp[0][j - 1] == 1 && grid[0][j] == 0) ? 1 : 0;
        }
        //第一列
        for (int i = 1; i < m; i++) {
            dp[i][0] = (dp[i - 1][0] == 1 && grid[i][0] == 0) ? 1 : 0;
        }
        //普遍位置
        //从左往右，再依次从上往下
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (grid[i][j] == 0) {
                    int fromUp = grid[i - 1][j] == 0 ? dp[i - 1][j] : 0;
                    int fromLeft = grid[i][j - 1] == 0 ? dp[i][j - 1] : 0;
                    dp[i][j] = fromUp + fromLeft;
                }
                //grid[i][j] == 1 时， dp[i][j] = 0
                //因为java中数组默认值就是0，所以不用再单独写
            }
        }
        return dp[m - 1][n - 1];
    }

}
