package dynamic_programming.row_column;

/**
 * 174. 地下城游戏
 * 一些恶魔抓住了公主（P）并将她关在了地下城的右下角。地下城是由 M x N 个房间组成的二维网格。
 * 我们英勇的骑士（K）最初被安置在左上角的房间里，他必须穿过地下城并通过对抗恶魔来拯救公主。
 * 骑士的初始健康点数为一个正整数。如果他的健康点数在某一时刻降至 0 或以下，他会立即死亡。
 * 有些房间由恶魔守卫，因此骑士在进入这些房间时会失去健康点数（若房间里的值为负整数，则表示骑士将损失健康点数）；
 * 其他房间要么是空的（房间里的值为 0），要么包含增加骑士健康点数的魔法球（若房间里的值为正整数，则表示骑士将增加健康点数）。
 * 为了尽快到达公主，骑士决定每次只向右或向下移动一步。
 * <p>
 * 编写一个函数来计算确保骑士能够拯救到公主所需的最低初始健康点数。
 * 例如，考虑到如下布局的地下城，如果骑士遵循最佳路径 右 -> 右 -> 下 -> 下，则骑士的初始健康点数至少为 7。
 * -2 (K)	-3	 3
 * -5	   -10	 1
 * 10	    30	-5 (P)
 * <p>
 * 说明:
 * 骑士的健康点数没有上限。
 * 任何房间都可能对骑士的健康点数造成威胁，也可能增加骑士的健康点数，包括骑士进入的左上角房间以及公主被监禁的右下角房间。
 */
//train_camp_04.class02.Code05_DungeonGame
public class Problem_0174_DungeonGame {

    public int calculateMinimumHP(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return 0;
        return fun(matrix, 0, 0);
    }

    //递归含义：
    //骑士即将登上[row,col]位置，出发到达右下角，至少的初始血量是多少？
    public static int fun(int[][] matrix, int row, int col) {
        int N = matrix.length;
        int M = matrix[0].length;
        //base case
        if (row == N - 1 && col == M - 1) {
            return (matrix[row][col] < 0 ? -matrix[row][col] : 0) + 1;
        }
        //还没有到右下角
        //到达最后一行，只往右走
        if (row == N - 1) {
            int rightNeed = fun(matrix, row, col + 1); // 3
            if (matrix[row][col] < 0) {
                return -matrix[row][col] + rightNeed; // -5 3
            } else if (matrix[row][col] < rightNeed) {//1 3
                return rightNeed - matrix[row][col];
            } else { //matrix[row][col] >= rightNeed // 3 3 or 4 3
                return 1;//保证有1滴血不死能登上[row][col]即可
            }
        }

        //到达最后一列，只往下走
        if (col == M - 1) {
            int downNeed = fun(matrix, row + 1, col); // 3
            if (matrix[row][col] < 0) {
                return -matrix[row][col] + downNeed; // -5 3
            } else if (matrix[row][col] < downNeed) {//1 3
                return downNeed - matrix[row][col];
            } else { //matrix[row][col] >= downNeed // 3 3 or 4 3
                return 1;//保证有1滴血不死能登上[row][col]即可
            }
        }

        //普通位置，可以往下走 也可以往右走
        int rightNeed = fun(matrix, row, col + 1); // 3
        int downNeed = fun(matrix, row + 1, col); // 3
        int minNeed = Math.min(rightNeed, downNeed);
        if (matrix[row][col] < 0) {
            return -matrix[row][col] + minNeed; // -5 3
        } else if (matrix[row][col] < minNeed) {//1 3
            return minNeed - matrix[row][col];
        } else { //matrix[row][col] >= downNeed // 3 3 or 4 3
            return 1;//保证有1滴血不死能登上[row][col]即可
        }
    }

    //改动态规划
    //标准的行列模型
    public static int dp(int[][] matrix) {
        if (matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }
        int N = matrix.length;
        int M = matrix[0].length;
        int[][] dp = new int[N][M];
        dp[N - 1][M - 1] = (matrix[N - 1][M - 1] < 0) ? (-matrix[N - 1][M - 1] + 1) : 1;

        //最后一行
        for (int col = M - 2; col >= 0; col--) {
            dp[N - 1][col] = 1;
            if (matrix[N - 1][col] < 0) {
                dp[N - 1][col] = -matrix[N - 1][col] + dp[N - 1][col + 1];
            } else if (matrix[N - 1][col] < dp[N - 1][col + 1]) {
                dp[N - 1][col] = dp[N - 1][col + 1] - matrix[N - 1][col];
            }
        }

        //最后一列
        for (int row = N - 2; row >= 0; row--) {
            dp[row][M - 1] = 1;
            if (matrix[row][M - 1] < 0) {
                dp[row][M - 1] = -matrix[row][M - 1] + dp[row + 1][M - 1];
            } else if (matrix[row][M - 1] < dp[row + 1][M - 1]) {
                dp[row][M - 1] = dp[row + 1][M - 1] - matrix[row][M - 1];
            }
        }

        //普通位置
        for (int row = N - 2; row >= 0; row--) {
            for (int col = M - 2; col >= 0; col--) {
                int rightNeed = dp[row][col + 1]; // 3
                int downNeed = dp[row + 1][col]; // 3
                int minNeed = Math.min(rightNeed, downNeed);
                dp[row][col] = 1;
                if (matrix[row][col] < 0) {
                    dp[row][col] = -matrix[row][col] + minNeed; // -5 3
                } else if (matrix[row][col] < minNeed) {//1 3
                    dp[row][col] = minNeed - matrix[row][col];
                }
            }
        }
        return dp[0][0];
    }

}
