package dynamic_programming.row_column;

//576. 出界的路径数
//https://leetcode-cn.com/problems/out-of-boundary-paths/
//三维动态规划
//好题
public class Problem_0576_OutOfBoundaryPaths {


    public static int mod = 1000000007;

    public static int findPaths(int m, int n, int N, int i, int j) {
        if (m == 0 || n == 0 || N < 0) return 0;
        long[][][] dp = new long[m][n][N + 1];
        for (int k = 0; k < m; k++) {
            for (int l = 0; l < n; l++) {
                for (int s = 0; s < N + 1; s++) {
                    //dp[k][l][s]含义
                    //从(k,l)出发，不超过s步，走到界外的方法数
                    dp[k][l][s] = -1; //-1表示没算过
                }
            }
        }
        return (int)(fun(m, n, N, i, j, dp) % mod);
    }

    //矩阵大小为m*n
    //当前来到(row,col)位置,还有rest步可以走
    //不超过rest步的，出界的路径数，返回
    public static long fun(int m, int n, int rest, int row, int col, long[][][] dp) {
        if (row == -1 || row == m || col == -1 || col == n) {
            return rest >= 0 ? 1 : 0; //rest不可能小于0
        }
        if (rest == 0) {//没出界呢！但是步数走完了
            return 0;
        }
        if (dp[row][col][rest] != -1) return (int)dp[row][col][rest];
        long ans = 0;
        //没出界，还有步数可以走，rest>0
        long left = fun(m, n, rest - 1, row, col - 1, dp) % mod;
        long right = fun(m, n, rest - 1, row, col + 1, dp) % mod;
        long up = fun(m, n, rest - 1, row - 1, col, dp) % mod;
        long down = fun(m, n, rest - 1, row + 1, col, dp) % mod;
        ans = (up + left + down + right) % mod;
        dp[row][col][rest] = ans;
        return ans;
    }

    public static void main(String[] args) {
        int ans = findPaths(2, 2, 2, 0, 0);
        int ans1 = findPaths(1, 3, 3, 0, 1);
        System.out.println(ans);
        System.out.println(ans1);
    }
}
