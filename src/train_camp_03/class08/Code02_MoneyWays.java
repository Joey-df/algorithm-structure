package train_camp_03.class08;

/**
 * 现有n1+n2种面值的硬币，其中前n1种为普通币，可以取任意枚，后n2种为纪念币，
 * 每种最多只能取一枚，每种硬币有一个面值，问能用多少种方法拼出m的面值?
 * <p>
 * 两张动态规划表 好题
 * coinCharge1 <==> leetcode 518
 */
public class Code02_MoneyWays {

    //使用n1种普通币，每种可以取任意枚，拼凑零钱的方法数
    //与Problem_518_Coin_Change_2是同一个问题
    //dp[i][j]的含义：coins[0...i]任意使用，凑出j元的方法数
    public static int[][] coinCharge1(int[] coins, int aim) {
        if (coins == null || coins.length == 0) {
            return null;
        }
        int N = coins.length;
        int[][] dp = new int[N][aim + 1];
        dp[0][0] = 1;// coins[0,0]任意使用,拼出0元有一种方法：什么也不做
        //第一行：coins[0,0]任意使用，拼出j元的方法数
        for (int j = 1; j <= aim; j++) {
            dp[0][j] = (j % coins[0] == 0) ? 1 : 0;
        }
        //第一列：coins[0,i]任意使用，拼出0元的方法数
        for (int i = 1; i < N; i++) {
            dp[i][0] = 1;
        }

        //普通位置
        for (int i = 1; i < N; i++) {
            for (int j = 1; j < aim + 1; j++) {
                dp[i][j] = dp[i - 1][j];//上面的格子
                dp[i][j] += j - coins[i] >= 0 ? dp[i][j - coins[i]] : 0;
            }
        }
        return dp;
    }

    //n2种纪念币，每种最多只能取一枚，每种硬币有一个面值，组成aim有多少种方法
    //dp[i][j]的含义：
    //coins[0,i]上每种最多只能取一枚，组成aim的方法数
    //经典0-1背包问题
    public static int[][] coinCharge2(int[] coins, int aim) {
        if (coins == null || coins.length == 0) {
            return null;
        }
        int N = coins.length;
        int[][] dp = new int[N][aim + 1];
        dp[0][0] = 1;// coins[0,0] 拼出0元有一种方法：不要coins[0]
        //第一行：coins[0]组成j元的方法数
        for (int j = 1; j < aim + 1; j++) {
            dp[0][j] = (j == coins[0]) ? 1 : 0;
        }

        //第一列：coins[0,i] 组成0元的方法数
        for (int i = 1; i < N; i++) {
            dp[i][0] = 1;
        }
        //普通位置
        for (int i = 1; i < N; i++) {
            for (int j = 1; j < aim + 1; j++) {
                dp[i][j] = dp[i - 1][j];//不要coins[i]
                if (j - coins[i] >= 0) {
                    dp[i][j] += dp[i - 1][j - coins[i]];//要coins[i]
                }
            }
        }
        return dp;
    }

    // n1为普通币,每种币可以使用任意张
    // n2为纪念币，每种只可以使用一张，可用可不用
    // 组成m元有几种方法
    public static int process(int[] n1, int[] n2, int m) {
        if (m < 0) {
            return 0;
        }
        if ((n1 == null || n1.length == 0) && (n2 == null || n2.length == 0)) {
            return m == 0 ? 1 : 0; //n1 n2都为空时，组成0元有1种方案
        }
        int[][] dp1 = coinCharge1(n1, m);
        int[][] dp2 = coinCharge2(n2, m);
        if (n1 == null) { //n2不为空
            return dp2[dp2.length - 1][m];
        }
        if (n2 == null) {//n1不为空
            return dp1[dp1.length - 1][m];
        }
        //n1 n2都不为空
        int ans = 0;
        for (int i = 0; i <= m; i++) {
            ans += dp1[dp1.length - 1][i] * dp2[dp2.length - 1][m - i];
        }
        return ans;
    }
}
