package matrix;

/**
 * You are given an n x n 2D matrix representing an image, rotate the image by 90 degrees (clockwise).
 * <p>
 * You have to rotate the image in-place, which means you have to modify the input 2D matrix directly.
 * DO NOT allocate another 2D matrix and do the rotation.
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: matrix = [[1,2,3],[4,5,6],[7,8,9]]
 * Output: [[7,4,1],[8,5,2],[9,6,3]]
 * Example 2:
 * <p>
 * <p>
 * Input: matrix = [[5,1,9,11],[2,4,8,10],[13,3,6,7],[15,14,12,16]]
 * Output: [[15,13,2,5],[14,3,4,1],[12,6,8,9],[16,7,10,11]]
 * Example 3:
 * <p>
 * Input: matrix = [[1]]
 * Output: [[1]]
 * Example 4:
 * <p>
 * Input: matrix = [[1,2],[3,4]]
 * Output: [[3,1],[4,2]]
 * <p>
 * <p>
 * Constraints:
 * <p>
 * matrix.length == n
 * matrix[i].length == n
 * 1 <= n <= 20
 * -1000 <= matrix[i][j] <= 1000
 */
//原地旋转正方形矩阵
public class Problem_0048_RotateImage {

    public void rotate(int[][] matrix) {
        int leftTopRow = 0;//左上角的行号
        int leftTopCol = 0;//左上角的列号
        int rightDownRow = matrix.length - 1;//右下角的行号
        int rightDownCol = matrix[0].length - 1;//右下角的列号
        while (leftTopRow < rightDownRow) { //相等时是中心点，停
            printCircle(matrix, leftTopRow++, leftTopCol++, rightDownRow--, rightDownCol--);
        }
    }

    //打印一圈
    private void printCircle(int[][] matrix, int a, int b, int c, int d) {
        for (int i = 0; i < d - b; i++) {//组数
            int temp = matrix[a][b + i];
            matrix[a][b + i] = matrix[c - i][b];
            matrix[c - i][b] = matrix[c][d - i];
            matrix[c][d - i] = matrix[a + i][d];
            matrix[a + i][d] = temp;
        }
    }
}
