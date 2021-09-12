package dynamic_programming.row_column;

/**
 * Given a 2D binary matrix filled with 0's and 1's,
 * find the largest square containing only 1's and return its area.
 *
 * Example:
 *
 * Input:
 *
 * 1 0 1 0 0
 * 1 0 1 1 1
 * 1 1 1 1 1
 * 1 0 0 1 0
 *
 * Output: 4
 */
//221. 最大正方形
public class Problem_0221_MaximalSquare {

    public int maximalSquare(char[][] matrix) {
        if (matrix==null || matrix[0].length==0) {
            return 0;
        }
        int n = matrix.length;
        int m = matrix[0].length;
        int max = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                max = Math.max(max, fun(matrix,i,j));
            }
        }
        return max * max;
    }

    //正方形必须以(i,j)做右下角，最大的正方形变长是多长
    public int fun(char[][] matrix, int i, int j) {
        if (matrix[i][j]=='0') return 0;
        //matrix[i][j]=='1'
        if (i==0 || j==0) { //第一行或第一列
            return 1;
        }
        //i!=0 && j!=0
        int p1 = fun(matrix, i-1,j);
        int p2 = fun(matrix, i,j-1);
        int p3 = fun(matrix, i-1,j-1);
        return Math.min(p1,Math.min(p2,p3)) + 1;
    }

    //dp
    public int maximalSquare2(char[][] matrix) {
        if (matrix==null || matrix[0].length==0) {
            return 0;
        }
        int n = matrix.length;
        int m = matrix[0].length;
        int max = 0; //最大边长
        int[][] dp = new int[n][m]; //dp[i][j] 必须以(i,j)做右下角，最大正方形的边长
        for (int col = 0; col < m; col++) {
            dp[0][col] = matrix[0][col]=='1' ? 1 : 0;
            max = Math.max(max, dp[0][col]);
        }
        for (int row = 1; row < n; row++) {
            dp[row][0] = matrix[row][0]=='1' ? 1 : 0;
            max = Math.max(max, dp[row][0]);
        }
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                if (matrix[i][j]=='1') {
                    int p1 = dp[i - 1][j];
                    int p2 = dp[i][j - 1];
                    int p3 = dp[i - 1][j - 1];
                    dp[i][j] = Math.min(p1, Math.min(p2, p3)) + 1;
                    max = Math.max(max, dp[i][j]);
                }
            }
        }
        return max * max;
    }

}
