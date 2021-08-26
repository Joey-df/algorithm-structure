package array;

/**
 * Given an m x n matrix. If an element is 0, set its entire row and column to 0. Do it in-place.
 * <p>
 * Follow up:
 * <p>
 * A straight forward solution using O(mn) space is probably a bad idea.
 * A simple improvement uses O(m + n) space, but still not the best solution.
 * Could you devise a constant space solution?
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: matrix = [[1,1,1],[1,0,1],[1,1,1]]
 * Output: [[1,0,1],[0,0,0],[1,0,1]]
 * Example 2:
 * <p>
 * <p>
 * Input: matrix = [[0,1,2,0],[3,4,5,2],[1,3,1,5]]
 * Output: [[0,0,0,0],[0,4,5,0],[0,3,1,0]]
 * <p>
 * <p>
 * Constraints:
 * n == matrix.length
 * m == matrix[0].length
 * 1 <= n, m <= 200
 * -2^31 <= matrix[i][j] <= 2^31 - 1
 */
public class Problem_0073_SetMatrixZeroes {

    public void setZeroes(int[][] matrix) {
        int N = matrix.length;
        int M = matrix[0].length;
        if (N == 0 || M == 0) {
            return;
        }
        boolean row0 = false; //0行是否要变0
        boolean col0 = false; //0列是否要变0
        for (int i = 0; i < M; i++) { //第0行
            if (matrix[0][i] == 0) {
                row0 = true;
                break;
            }
        }
        for (int i = 0; i < N; i++) { //第0列
            if (matrix[i][0] == 0) {
                col0 = true;
                break;
            }
        }
        for (int i = 1; i < N; i++) {
            for (int j = 1; j < M; j++) {
                if (matrix[i][j] == 0) {
                    matrix[0][j] = 0;
                    matrix[i][0] = 0;
                }
            }
        }
        for (int col = 1; col < M; col++) {
            if (matrix[0][col] == 0) {//列全变0
                for (int row = 1; row < N; row++) {
                    matrix[row][col] = 0;
                }
            }
        }

        for (int row = 1; row < N; row++) {
            if (matrix[row][0] == 0) { //行全变0
                for (int col = 1; col < M; col++) {
                    matrix[row][col] = 0;
                }
            }
        }

        //最后处理0行0列
        if (col0) {
            for (int i = 0; i < N; i++) {
                matrix[i][0] = 0;
            }
        }
        if (row0) {
            for (int i = 0; i < M; i++) {
                matrix[0][i] = 0;
            }
        }
    }
}
