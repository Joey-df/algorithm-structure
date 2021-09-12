package dynamic_programming.row_column;

/**
 * 一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为“Start” ）。
 *
 * 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为“Finish”）。
 *
 * 问总共有多少条不同的路径？
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/unique-paths
 */

//一个样本做行一个样本做列的对应模型
public class Problem_0062_UniquePaths {


    /**
     * 暴力尝试
     * 递归含义：机器人从[0,0]点出发到达[i,j]点，有多少中不同的路径
     */
    public static int process(int i, int j) {
        //base case
        if (i==0 && j==0) {//表示从[0,0]出发到[0,0]点，总共有1中路径，这种路径叫做没动
            return 1;
        }
        //走到这里表示不同时为0
        if (i==0) { //表示只往右走
            return process(i, j-1);
        }
        if (j==0) { //表示只往下走
            return process(i-1, j);
        }
        //走到这里，说明i!=0 && j!=0
        //普遍位置
        return process(i-1, j) + //表示从[i-1,j]往下走一步的方法数
                process(i,j-1);  //表示从[i,j-1]往右走一步的方法数
    }

    /**
     * dp[i][j]的含义：机器人从[0,0]出发到达[i,j]的方法数
     * @param m m行
     * @param n n列
     * @return
     */
    public static int dpWay(int m, int n) {
        int dp[][] = new int[m][n];
        dp[0][0] = 1;
        //第一行
        for (int i = 1; i < n; i++) {
            dp[0][i] = dp[0][i-1];
        }
        //第一列
        for (int i = 1; i < m; i++) {
            dp[i][0] = dp[i-1][0];
        }
        //普遍位置：从左往右，再依次从上往下
        for (int i = 1; i < m; i++) { //m行
            for (int j = 1; j < n; j++) { //n列
                dp[i][j] = dp[i-1][j] + dp[i][j-1];
            }
        }
        return dp[m-1][n-1];
    }


    public static void main(String[] args) {
        int m = 5;
        int n = 8;
        System.out.println("process(m-1,n-1) = " + process(m-1,n-1));
        System.out.println("dpWay(m,n) = " + dpWay(m,n));
    }
}
