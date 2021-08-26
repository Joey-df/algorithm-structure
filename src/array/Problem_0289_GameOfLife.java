package array;

/**
 * According to the Wikipedia's article: "The Game of Life, also known simply as Life, is a cellular automaton devised by the British mathematician John Horton Conway in 1970."
 * <p>
 * Given a board with m by n cells, each cell has an initial state live (1) or dead (0). Each cell interacts with its eight neighbors (horizontal, vertical, diagonal) using the following four rules (taken from the above Wikipedia article):
 * <p>
 * Any live cell with fewer than two live neighbors dies, as if caused by under-population.
 * Any live cell with two or three live neighbors lives on to the next generation.
 * Any live cell with more than three live neighbors dies, as if by over-population..
 * Any dead cell with exactly three live neighbors becomes a live cell, as if by reproduction.
 * Write a function to compute the next state (after one update) of the board given its current state. The next state is created by applying the above rules simultaneously to every cell in the current state, where births and deaths occur simultaneously.
 * <p>
 * Example:
 * <p>
 * Input:
 * [
 *   [0,1,0],
 *   [0,0,1],
 *   [1,1,1],
 *   [0,0,0]
 * ]
 * Output:
 * [
 *   [0,0,0],
 *   [1,0,1],
 *   [0,1,1],
 *   [0,1,0]
 * ]
 * Follow up:
 * <p>
 * Could you solve it in-place? Remember that the board needs to be updated at the same time: You cannot update some cells first and then use their updated values to update other cells.
 * In this question, we represent the board using a 2D array. In principle, the board is infinite, which would cause problems when the active area encroaches the border of the array. How would you address these problems?
 */
//二维数组原地变换
public class Problem_0289_GameOfLife {

    public static void gameOfLife(int[][] board) {
        int n = board.length;
        int m = board[0].length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                int neighbor = neighbor(board, i, j);
                if (neighbor == 3 || (board[i][j] == 1 && neighbor == 2)) {
                    board[i][j] |= 2;
                }
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                board[i][j] >>= 1;
            }
        }
    }

    //返回(i,j)八个方向上1的数量
    public static int neighbor(int[][] m, int i, int j) {
        return f(m, i + 1, j) +
                f(m, i - 1, j) +
                f(m, i, j - 1) +
                f(m, i, j + 1) +
                f(m, i + 1, j + 1) +
                f(m, i + 1, j - 1) +
                f(m, i - 1, j + 1) +
                f(m, i - 1, j - 1);
    }

    //返回矩阵中(i,j)位置的值
    public static int f(int[][] m, int i, int j) {
        if (i >= 0 && i < m.length && j >= 0 && j < m[0].length && (m[i][j] & 1) == 1) {
            return 1;
        }
        return 0;
    }
}
