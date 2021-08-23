package matrix;

import java.util.ArrayList;
import java.util.List;

/**
 * Given an m x n matrix, return all elements of the matrix in spiral order.
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: matrix = [[1,2,3],[4,5,6],[7,8,9]]
 * Output: [1,2,3,6,9,8,7,4,5]
 * Example 2:
 * <p>
 * <p>
 * Input: matrix = [[1,2,3,4],[5,6,7,8],[9,10,11,12]]
 * Output: [1,2,3,4,8,12,11,10,9,5,6,7]
 * <p>
 * <p>
 * Constraints:
 * <p>
 * m == matrix.length
 * n == matrix[i].length
 * 1 <= m, n <= 10
 * -100 <= matrix[i][j] <= 100
 */
//顺时针转圈打印矩阵
public class Problem_0054_SpiralMatrix {
    public static List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> ans = new ArrayList<>();
        if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) {
            return ans;
        }
        int a = 0;//左上角的行号
        int b = 0;//左上角的列号
        int c = matrix.length - 1;//右下角的行号
        int d = matrix[0].length - 1;//右下角的列号
        while (a <= c && b <= d) { //等于的时候是一条线 或 一个点 需要处理
            processCircle(matrix, a++, b++, c--, d--, ans);
        }
        return ans;
    }

    private static void processCircle(int[][] matrix, int a, int b, int c, int d, List<Integer> ans) {
        if (a == c) {//一条横线时 或一个点时也走这个分支
            for (int i = 0; i < d - b + 1; i++) {
                ans.add(matrix[a][b + i]);
            }
        } else if (b == d) { //一条竖线时
            for (int i = 0; i < c - a + 1; i++) {
                ans.add(matrix[a + i][b]);
            }
        } else { //是一个圈
            int curRow = a;
            int curCol = b;
            while (curCol < d) {
                ans.add(matrix[a][curCol]);
                curCol++;
            }
            //curCol==d
            while (curRow < c) {
                ans.add(matrix[curRow][d]);
                curRow++;
            }
            //curRow==c
            while (curCol > b) {
                ans.add(matrix[c][curCol]);
                curCol--;
            }
            //curCol==b
            while (curRow > a) {
                ans.add(matrix[curRow][b]);
                curRow--;
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(spiralOrder(new int[][]{
                new int[]{1, 2, 3},
                new int[]{4, 5, 6},
                new int[]{7, 8, 9}
        }));
    }
}
