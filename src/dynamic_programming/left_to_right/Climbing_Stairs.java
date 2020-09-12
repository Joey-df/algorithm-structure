package dynamic_programming.left_to_right;

/**
 * 70.爬楼梯
 * 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
 * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
 */
public class Climbing_Stairs {

    //暴力递归
    public static int process(int n) {
        if (n <=0 ) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        if (n == 2) {
            return 2;
        }
        return process(n-1) //爬完n-1阶台阶的方法数 再跨1阶
                + process(n-2); //爬完n-2阶台阶的方法数 再跨2阶
    }


    public static int dp(int n) {
        if (n <= 0) return 0;
        if (n==1) return 1;
        if (n==2) return 2;
        //n>=3
        int[] dp = new int[n+1];
        // dp[0] = 0;
        dp[1] = 1;
        dp[2] = 2;
        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i-1] + dp[i-2];
        }
        return dp[n];
    }

    public static void main(String[] args) {
        System.out.println(process(19));
        System.out.println(dp(19));
    }
}
