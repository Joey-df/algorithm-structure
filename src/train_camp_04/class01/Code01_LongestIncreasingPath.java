package train_camp_04.class01;

/**
 * 给定一个二维数组matrix，可以从任何位置出发，每一步可以走向上、下、左、右，四个方向。返回最大递增链的长度。
 * 例子：
 * matrix =
 * 5  4  3
 * 3  1  2
 * 2  1  3
 * 从最中心的1出发，是可以走出1 2 3 4 5的链的，而且这是最长的递增链。所以返回长度5
 * <p>
 * https://leetcode.com/problems/longest-increasing-path-in-a-matrix/
 */
public class Code01_LongestIncreasingPath {
    //递归含义：
    //从[r,c]出发 走出的递增链有多长？
    public static int process(int[][] matrix, int r, int c, int[][] dp) {
        if (dp[r][c] != 0) {
            return dp[r][c];
        }
        int N = matrix.length;
        int M = matrix[0].length;
        int ans = 1;//那个方向都不走，默认有1个长度
        int up = Integer.MIN_VALUE;
        if (r - 1 >= 0 && matrix[r - 1][c] > matrix[r][c]) { //可以往上走
            up = process(matrix, r - 1, c, dp);
        }
        int down = Integer.MIN_VALUE;
        if (r + 1 < N && matrix[r + 1][c] > matrix[r][c]) { //可以往下走
            down = process(matrix, r + 1, c, dp);
        }
        int left = Integer.MIN_VALUE;
        if (c - 1 >= 0 && matrix[r][c - 1] > matrix[r][c]) { //可以往左走
            left = process(matrix, r, c - 1, dp);
        }
        int right = Integer.MIN_VALUE;
        if (c + 1 < M && matrix[r][c + 1] > matrix[r][c]) { //可以往右走
            right = process(matrix, r, c + 1, dp);
        }

        int next = Math.max(Math.max(up, down), Math.max(left, right));
        if (next > 0) {
            ans += next;
        }
        dp[r][c] = ans;
        return ans;
    }

    //时间复杂度 O(N*M)
    public static int maxIncPathLen(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }
        int N = matrix.length;
        int M = matrix[0].length;
        int ans = -1;
        int[][] dp = new int[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                ans = Math.max(ans, process(matrix, i, j, dp));
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[][] matrix = new int[][]{
                {5, 4, 3},
                {3, 1, 2},
                {2, 1, 3}
        };
        System.out.println(maxIncPathLen(matrix));
    }
}
