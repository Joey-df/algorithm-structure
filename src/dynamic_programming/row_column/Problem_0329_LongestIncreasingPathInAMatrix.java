package dynamic_programming.row_column;

/**
 * Given an integer matrix, find the length of the longest increasing path.
 * From each cell, you can either move to four directions: left, right, up or down.
 * You may NOT move diagonally or move outside of the boundary (i.e. wrap-around is not allowed).
 *
 * Example 1:
 * Input: nums =
 * [
 *   [9,9,4],
 *   [6,6,8],
 *   [2,1,1]
 * ]
 * Output: 4
 * Explanation: The longest increasing path is [1, 2, 6, 9].
 * Example 2:
 *
 * Input: nums =
 * [
 *   [3,4,5],
 *   [3,2,6],
 *   [2,2,1]
 * ]
 * Output: 4
 * Explanation: The longest increasing path is [3, 4, 5, 6]. Moving diagonally is not allowed.
 */
//329. 矩阵中的最长递增路径
public class Problem_0329_LongestIncreasingPathInAMatrix {

    //从每个位置出发，算一个答案，求全局最大值
    public int longestIncreasingPath(int[][] matrix) {
        if (matrix==null || matrix.length==0 || matrix[0].length==0) {
            return 0;
        }
        int n = matrix.length;
        int m = matrix[0].length;
        int[][] dp = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                dp[i][j] = -1; //不设置-1也可以，默认为0，也可以表示没算过。因为但凡算过，每个位置的答案至少是1
            }
        }
        int ans = 1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                ans = Math.max(ans, fun(matrix, i, j, dp));
            }
        }
        return ans;
    }

    //从(i,j)出发，走出的最长递增链有多长，返回
    //因为严格递增，所以客观上，不会走回头路，所以不应设计不走回头路的机制
    //记忆化搜索，就是最优解
    public int fun(int[][] matrix, int i, int j, int[][] dp) {
        if (dp[i][j]!=-1) return dp[i][j];
        int n = matrix.length;
        int m = matrix[0].length;
        /*if (i==-1 || i==n || j==-1 || j==m) { //下游调用决定了这些不可能发生
            return 0;
        }*/
        //没出界，可以正常走
        int up = (i-1 >=0 && matrix[i-1][j] > matrix[i][j]) ? fun(matrix, i-1, j, dp) : 0;
        int down = (i+1 <n && matrix[i+1][j] > matrix[i][j]) ? fun(matrix, i+1, j, dp) : 0;
        int left = (j-1 >=0 && matrix[i][j-1] > matrix[i][j]) ? fun(matrix, i, j-1, dp) : 0;
        int right = (j+1 <m && matrix[i][j+1] > matrix[i][j]) ? fun(matrix, i, j+1, dp) : 0;
        int ans = Math.max(Math.max(up, down), Math.max(left, right)) + 1;
        dp[i][j] = ans;
        return ans;
    }

}
