package dfs;

/**
 * Given an integer matrix, find the length of the longest increasing path.
 * <p>
 * From each cell, you can either move to four directions: left, right, up or down.
 * You may NOT move diagonally or move outside of the boundary (i.e. wrap-around is not allowed).
 * <p>
 * Example 1:
 * <p>
 * Input: nums =
 * [
 * [9,9,4],
 * [6,6,8],
 * [2,1,1]
 * ]
 * Output: 4
 * Explanation: The longest increasing path is [1, 2, 6, 9].
 * Example 2:
 * <p>
 * Input: nums =
 * [
 * [3,4,5],
 * [3,2,6],
 * [2,2,1]
 * ]
 * Output: 4
 * Explanation: The longest increasing path is [3, 4, 5, 6]. Moving diagonally is not allowed.
 * <p>
 * 求矩阵中的最长递增链有多长？
 */
public class Problem_329_Longest_Increasing_Path_in_a_Matrix {
    //思路：从矩阵中的每个位置出发的最长递增链有多长，求max
    public int longestIncreasingPath(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }
        int ans = 0;
        int[][] dp = new int[matrix.length][matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                ans = Math.max(ans, process(matrix, i, j, dp));
            }
        }
        return ans;
    }

    //从[i,j]位置出发 走出的最长递增链有多长
    private int process(int[][] matrix, int i, int j, int[][] dp) {
        if (i < 0 || i > matrix.length || j < 0 || j > matrix[0].length) {
            return 0;
        }
        if (dp[i][j] > 0) {
            return dp[i][j];
        }
        int cur = matrix[i][j];
        int next = 0;
        //to up
        if (i > 0 && matrix[i - 1][j] > cur) {
            next = Math.max(next, process(matrix, i - 1, j, dp));
        }

        //to down
        if (i < matrix.length - 1 && matrix[i + 1][j] > cur) {
            next = Math.max(next, process(matrix, i + 1, j, dp));
        }

        //to left
        if (j > 0 && matrix[i][j - 1] > cur) {
            next = Math.max(next, process(matrix, i, j - 1, dp));
        }

        //to right
        if (j < matrix[0].length - 1 && matrix[i][j + 1] > cur) {
            next = Math.max(next, process(matrix, i, j + 1, dp));
        }

        dp[i][j] = next + 1;
        return dp[i][j];
    }
}
