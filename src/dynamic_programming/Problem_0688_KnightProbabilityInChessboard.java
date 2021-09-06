package dynamic_programming;

import java.text.DecimalFormat;

/**
 * 688. “马”在棋盘上的概率
 * 已知一个 NxN 的国际象棋棋盘，棋盘的行号和列号都是从 0 开始。即最左上角的格子记为 (0, 0)，最右下角的记为 (N-1, N-1)。
 * 现有一个 “马”（也译作 “骑士”）位于 (r, c) ，并打算进行 K 次移动。
 * 如下图所示，国际象棋的 “马” 每一步先沿水平或垂直方向移动 2 个格子，然后向与之相垂直的方向再移动 1 个格子，共有 8 个可选的位置。
 * <p>
 * 现在 “马” 每一步都从可选的位置（包括棋盘外部的）中独立随机地选择一个进行移动，直到移动了 K 次或跳到了棋盘外面。
 * 求移动结束后，“马” 仍留在棋盘上的概率。
 * <p>
 * 示例：
 * 输入: 3, 2, 0, 0
 * 输出: 0.0625
 * 解释:
 * 输入的数据依次为 N, K, r, c
 * 第 1 步时，有且只有 2 种走法令 “马” 可以留在棋盘上（跳到（1,2）或（2,1））。
 * 对于以上的两种情况，各自在第2步均有且只有2种走法令 “马” 仍然留在棋盘上。
 * 所以 “马” 在结束后仍在棋盘上的概率为 0.0625。
 * <p>
 * 注意：
 * N 的取值范围为 [1, 25]
 * K 的取值范围为 [0, 100]
 * 开始时，“马” 总是位于棋盘上
 */
public class Problem_0688_KnightProbabilityInChessboard {

    //暴力尝试版本
    public double knightProbability1(int n, int k, int row, int col) {
        return (double) ((double) process(row, col, n, k) / (long) Math.pow(8, k));
    }

    //当前在(row,col)位置，还有rest步要走，如果走完了还在棋盘中，就获得1个生存点，返回总的生存点数
    public int process(int row, int col, int n, int rest) {
        if (row < 0 || row >= n || col < 0 || col >= n) {
            return 0; //走出界外了
        }
        //还在棋盘内
        if (rest == 0) {
            return 1; //走完了但还在界内，获得一个生存点
        }
        return process(row - 2, col + 1, n, rest - 1)
                + process(row - 1, col + 2, n, rest - 1)
                + process(row + 1, col + 2, n, rest - 1)
                + process(row + 2, col + 1, n, rest - 1)
                + process(row + 2, col - 1, n, rest - 1)
                + process(row + 1, col - 2, n, rest - 1)
                + process(row - 1, col - 2, n, rest - 1)
                + process(row - 2, col - 1, n, rest - 1);
    }


    //动态规划版本
    public double knightProbability2(int n, int k, int row, int col) {
        //三个可变参数 row col k -> 三维dp
        //dp[i][j][k]的含义：从(i,j)出发，走k步之后，还在棋盘上的生存点数是多少
        double[][][] dp = new double[n][n][k + 1]; //必须用double，不然溢出
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                dp[i][j][0] = 1;
            }
        }
        for (int rest = 1; rest <= k; rest++) {
            for (int r = 0; r < n; r++) {
                for (int c = 0; c < n; c++) {
                    dp[r][c][rest] += pick(r - 2, c + 1, n, rest - 1, dp)
                            + pick(r - 1, c + 2, n, rest - 1, dp)
                            + pick(r + 1, c + 2, n, rest - 1, dp)
                            + pick(r + 2, c + 1, n, rest - 1, dp)
                            + pick(r + 2, c - 1, n, rest - 1, dp)
                            + pick(r + 1, c - 2, n, rest - 1, dp)
                            + pick(r - 1, c - 2, n, rest - 1, dp)
                            + pick(r - 2, c - 1, n, rest - 1, dp);
                }
            }
        }
        return dp[row][col][k] / Math.pow(8, k);
    }

    private double pick(int row, int col, int n, int rest, double[][][] dp) {
        if (row < 0 || row >= n || col < 0 || col >= n) {
            return 0;
        }
        return dp[row][col][rest];
    }

}
