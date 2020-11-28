package train_camp_03.class02;

/**
 * 在行也有序、列也有序的二维数组中，找target，找到返回true，否则false
 * https://leetcode.com/problems/search-a-2d-matrix-ii/
 */
public class Code01_FindNumInSortedMatrix {

    public static boolean find(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }
        int N = matrix.length;
        int M = matrix[0].length;
        //从右上角出发开始搜索
        int row = 0;
        int col = M - 1;
        while (row < N && col > -1) {
            if (matrix[row][col] == target) {
                return true;
            } else if (target > matrix[row][col]) {
                row++;
            } else { //target<matrix[row][col]
                col--;
            }
        }
        return false;
    }

}

