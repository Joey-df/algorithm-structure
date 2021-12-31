package array;

//566. 重塑矩阵
//在 MATLAB 中，有一个非常有用的函数 reshape ，
//它可以将一个 m x n 矩阵重塑为另一个大小不同（r x c）的新矩阵，但保留其原始数据。
//给你一个由二维数组 mat 表示的 m x n 矩阵，以及两个正整数 r 和 c ，分别表示想要的重构的矩阵的行数和列数。
//重构后的矩阵需要将原始矩阵的所有元素以相同的 行遍历顺序 填充。
//如果具有给定参数的 reshape 操作是可行且合理的，则输出新的重塑矩阵；否则，输出原始矩阵。
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
