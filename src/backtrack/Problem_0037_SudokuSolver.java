package backtrack;

/**
 * Write a program to solve a Sudoku puzzle by filling the empty cells.
 * <p>
 * A sudoku solution must satisfy all of the following rules:
 * <p>
 * Each of the digits 1-9 must occur exactly once in each row.
 * Each of the digits 1-9 must occur exactly once in each column.
 * Each of the digits 1-9 must occur exactly once in each of the 9 3x3 sub-boxes of the grid.
 * The '.' character indicates empty cells.
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: board =
 * [["5","3",".",".","7",".",".",".","."],
 * ["6",".",".","1","9","5",".",".","."],
 * [".","9","8",".",".",".",".","6","."],
 * ["8",".",".",".","6",".",".",".","3"],
 * ["4",".",".","8",".","3",".",".","1"],
 * ["7",".",".",".","2",".",".",".","6"],
 * [".","6",".",".",".",".","2","8","."],
 * [".",".",".","4","1","9",".",".","5"],
 * [".",".",".",".","8",".",".","7","9"]]
 * <p>
 * Output:
 * [["5","3","4","6","7","8","9","1","2"],
 * ["6","7","2","1","9","5","3","4","8"],
 * ["1","9","8","3","4","2","5","6","7"],
 * ["8","5","9","7","6","1","4","2","3"],
 * ["4","2","6","8","5","3","7","9","1"],
 * ["7","1","3","9","2","4","8","5","6"],
 * ["9","6","1","5","3","7","2","8","4"],
 * ["2","8","7","4","1","9","6","3","5"],
 * ["3","4","5","2","8","6","1","7","9"]]
 * Explanation: The input board is shown above and the only valid solution is shown below:
 * <p>
 * Constraints:
 * <p>
 * board.length == 9
 * board[i].length == 9
 * board[i][j] is a digit or '.'.
 * It is guaranteed that the input board has only one solution.
 */
public class Problem_0037_SudokuSolver {

    public void solveSudoku(char[][] board) {

        boolean[][] row = new boolean[9][10];//row[i][num]表示第i行上是否有num这个数
        boolean[][] col = new boolean[9][10];//col[j][num]表示第j列上是否有num这个数
        boolean[][] bucket = new boolean[9][10];//bucket[grid][num]表示第grid格子上是否有num这个数
        init(board, row, col, bucket);
        process(board, 0, 0, row, col, bucket);
    }

    private void init(char[][] board, boolean[][] row, boolean[][] col, boolean[][] bucket) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                int grid = 3 * (i / 3) + (j / 3);
                if (board[i][j] != '.') {
                    int num = board[i][j] - '0';
                    row[i][num] = true;
                    col[j][num] = true;
                    bucket[grid][num] = true;
                }
            }
        }
    }

    //当前在[i,j]
    //从[i,j]出发，能否解数独
    private boolean process(char[][] board, int i, int j, boolean[][] row, boolean[][] col, boolean[][] bucket) {
        if (i == 9) {
            return true;
        }
        int nexti = j != 8 ? i : i + 1;
        int nextj = j != 8 ? j + 1 : 0;
        if (board[i][j] != '.') {
            return process(board, nexti, nextj, row, col, bucket);
        }
        //board[i][j] == '.'
        int grid = 3 * (i / 3) + (j / 3);
        for (int num = 1; num <= 9; num++) {
            if (!row[i][num] && !col[j][num] && !bucket[grid][num]) {
                board[i][j] = (char) (num + '0');
                row[i][num] = true;
                col[j][num] = true;
                bucket[grid][num] = true;
                if (process(board, nexti, nextj, row, col, bucket)) {
                    return true;
                }
                board[i][j] = '.';
                row[i][num] = false;
                col[j][num] = false;
                bucket[grid][num] = false;
            }
        }
        return false;
    }
}
