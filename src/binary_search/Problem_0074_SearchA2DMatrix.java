package binary_search;

/**
 * 74. 搜索二维矩阵
 * 编写一个高效的算法来判断 m x n 矩阵中，是否存在一个目标值。该矩阵具有如下特性：
 * 每行中的整数从左到右按升序排列。
 * 每行的第一个整数大于前一行的最后一个整数。
 * 示例 1：
 * 输入：matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 3
 * 输出：true
 * 示例 2：
 * 输入：matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 13
 * 输出：false
 */
public class Problem_0074_SearchA2DMatrix {
    //Use binary search.
    //n * m matrix convert to an array => matrix[x][y] => a[x * m + y]
    //an array convert to n * m matrix => a[x] =>matrix[x / m][x % m];
    public boolean searchMatrix(int[][] matrix, int target) {
        int n = matrix.length;
        int m = matrix[0].length;
        int l = 0;
        int r = m * n - 1;
        while (l <= r) {
            int mid = l + ((r - l) >> 1);
            if (matrix[mid / m][mid % m] < target) {
                l = mid + 1;
            } else if (matrix[mid / m][mid % m] == target) {
                return true;
            } else {
                r = mid - 1;
            }
        }
        return false;
    }
}
