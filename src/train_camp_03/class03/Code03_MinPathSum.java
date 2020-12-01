package train_camp_03.class03;

/**
 * 给定一个二维数组matrix，其中每个数都是正数，要求从左上到右下
 * 每一步只能向右或者向下，沿途经过的数字要累加起来
 * 最后请返回最小的路径和
 * https://leetcode.com/problems/minimum-path-sum/
 * 动态规划的空间压缩技巧！
 */
public class Code03_MinPathSum {

    //dp[i][j]含义：从左上角出发，走到[i][j]的最小路径和是多少
    public static int minPathSum(int[][] matrix) {
        int N = matrix.length;
        int M = matrix[0].length;
        int[][] dp = new int[N][M];
        dp[0][0] = matrix[0][0];
        //第一行
        for (int i = 1; i < M; i++) {
            dp[0][i] = dp[0][i - 1] + matrix[0][i];
        }
        //第一列
        for (int i = 1; i < N; i++) {
            dp[i][0] = dp[i - 1][0] + matrix[i][0];
        }
        //普遍位置
        for (int i = 1; i < N; i++) {
            for (int j = 1; j < M; j++) {
                dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + matrix[i][j];
            }
        }
        return dp[N - 1][M - 1];
    }
}
