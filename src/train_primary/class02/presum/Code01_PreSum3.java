package train_primary.class02.presum;

/**
 * 数组的前缀和
 * 刷题经常用到的预处理技巧
 * <p>
 * 方法二：使用动态规划思想得到二维数组dp，计算arr[l,r]直接从dp中取值
 */
public class Code01_PreSum3 {

    //dp[i][j]的含义：
    //arr[l,r]范围上的累加和是多少
    public static int[][] preSum(int[] arr) {
        int N = arr.length;
        int[][] dp = new int[N][N];
        //对角线
        for (int i = 0; i < N; i++) {
            dp[i][i] = arr[i];
            System.out.println("dp[" + i + "][" + i + "] = " + dp[i][i]);
        }
        //普遍位置
        for (int i = N - 2; i >= 0; i--) {
            for (int j = i + 1; j < N; j++) {
                dp[i][j] = dp[i][j - 1] + dp[j][j];
                System.out.println("dp[" + i + "][" + j + "] = " + dp[i][j]);
            }
        }
        return dp;
    }

    //得到dp直接O(1)取值得到arr[l,r]范围上的sum
    public static int rangeSum(int[] arr, int l, int r) {
        int[][] dp = preSum(arr);
        return dp[l][r];
    }

    public static void main(String[] args) {
        int[] arr = {2, 3, 6, 7};
        System.out.println(rangeSum(arr, 2, 3));
    }
}
