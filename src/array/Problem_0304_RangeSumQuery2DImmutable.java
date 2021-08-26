package array;

//304. 二维区域和检索 - 矩阵不可变
//https://leetcode-cn.com/problems/range-sum-query-2d-immutable/
//矩阵的前缀和，使用预处理结构快速求出任意一个矩形区域的和
//好题
public class Problem_0304_RangeSumQuery2DImmutable {

    class NumMatrix {

        int[][] dp;

        public NumMatrix(int[][] matrix) {
            int n = matrix.length; //行数
            int m = matrix[0].length; //列数
            dp = new int[n][m];
            //dp[i][j]的含义：从(0,0)为左上角 到 以(i,j)为右下角形成的矩形区域 的 累加和是多少
            dp[0][0] = matrix[0][0];
            //第一行
            for (int c = 1; c < m; c++) {
                dp[0][c] = dp[0][c - 1] + matrix[0][c];
            }
            //第一列
            for (int r = 1; r < n; r++) {
                dp[r][0] = dp[r - 1][0] + matrix[r][0];
            }
            //普遍位置
            for (int r = 1; r < n; r++) {
                for (int c = 1; c < m; c++) {
                    dp[r][c] = dp[r - 1][c] + dp[r][c - 1] - dp[r - 1][c - 1] + matrix[r][c];
                }
            }
        }

        public int sumRegion(int row1, int col1, int row2, int col2) {
            int left = (row1 > 0) ? dp[row1 - 1][col2] : 0; //左边
            int up = (col1 > 0) ? dp[row2][col1 - 1] : 0; //上面
            int corner = (row1 > 0 && col1 > 0) ? dp[row1 - 1][col1 - 1] : 0; //左上角
            return dp[row2][col2] - left - up + corner;
        }
    }

    /**
     * Your NumMatrix object will be instantiated and called as such:
     * NumMatrix obj = new NumMatrix(matrix);
     * int param_1 = obj.sumRegion(row1,col1,row2,col2);
     */

}

//https://leetcode-cn.com/problems/range-sum-query-2d-immutable/solution/ru-he-qiu-er-wei-de-qian-zhui-he-yi-ji-y-6c21/