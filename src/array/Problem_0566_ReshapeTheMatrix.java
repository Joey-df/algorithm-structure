package array;

//566. 重塑矩阵
//https://leetcode-cn.com/problems/reshape-the-matrix/
public class Problem_0566_ReshapeTheMatrix {
    //数组与矩阵之间的变换
    public int[][] matrixReshape(int[][] mat, int r, int c) {
        assert (mat != null && mat.length > 0 && mat[0] != null && mat[0].length > 0);
        int n = mat.length;
        int m = mat[0].length;
        if (m * n != r * c) {
            return mat;
        }
        int[][] ans = new int[r][c];
        //n*m的矩阵matrix变成一维数组，matrix中任意一个坐标点(i,j)对应到数组中的下标为i*m+j
        //将n*m的矩阵撸成一维数组nums，数据范围是[0,m*n-1]
        //nums中下标为 x 映射回n*m的矩阵中，对应的坐标为(x/m,x%m)
        for (int i = 0; i < n * m; i++) {
            ans[i / c][i % c] = mat[i / m][i % m];
        }
        return ans;
    }

}
