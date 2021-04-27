package coding_for_great_offer.class01;

/**
 * 给定一个二维数组matrix，
 * 你可以从任何位置出发，走向上下左右四个方向
 * 返回能走出来的最长的递增链长度
 * leetcode 329题
 */
public class Code05_LongestIncreasingPath {

    /**
     * @param m 二维数组matrix
     * @return 你可以从任何位置出发，走向上下左右四个方向
     * 返回能走出来的最长的递增链长度
     */
    public static int process(int[][] m) {
        if (m == null || m.length == 0 || m[0].length == 0) return 0;
        int ans = 1;
        int M = m.length;
        int N = m[0].length;
        int[][] dp = new int[M][N];
        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m[0].length; j++) {
                ans = Math.max(ans, g(m, i, j, dp));
            }
        }
        return ans;
    }

    /**
     * @param m 二维数组matrix
     * @param i 行号
     * @param j 列号
     * @param dp 记忆化搜索
     * @return 从[i][j]出发，走出的最长递增链有多长
     */
    private static int g(int[][] m, int i, int j, int[][] dp) {
        if (i < 0 || i > m.length || j < 0 || j > m[0].length) return 0;
        if (dp[i][j] != 0) return dp[i][j];
        int next = 0;
        int up = 0;
        if (i - 1 >= 0 && m[i - 1][j] > m[i][j]) {
            up = g(m, i - 1, j, dp);
            next = Math.max(next, up);
        }
        int down = 0;
        if (i + 1 <= m.length - 1 && m[i + 1][j] > m[i][j]) {
            down = g(m, i + 1, j, dp);
            next = Math.max(next, down);
        }
        int left = 0;
        if (j - 1 >= 0 && m[i][j - 1] > m[i][j]) {
            left = g(m, i, j - 1, dp);
            next = Math.max(next, left);
        }
        int right = 0;
        if (j + 1 <= m[0].length - 1 && m[i][j + 1] > m[i][j]) {
            right = g(m, i, j + 1, dp);
            next = Math.max(next, right);
        }
        dp[i][j] = 1 + next;
        return 1 + next;
    }

    public static void main(String[] args) {
        int[][] m = new int[][]{
                new int[]{9, 9, 4},
                new int[]{6, 6, 8},
                new int[]{2, 1, 1}
        };
        System.out.println(process(m));
    }
}
