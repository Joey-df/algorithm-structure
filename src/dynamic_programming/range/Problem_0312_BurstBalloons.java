package dynamic_programming.range;

/**
 * 312. 戳气球
 * 有 n 个气球，编号为0 到 n - 1，每个气球上都标有一个数字，这些数字存在数组 nums 中。
 * 现在要求你戳破所有的气球。戳破第 i 个气球，你可以获得 nums[i - 1] * nums[i] * nums[i + 1] 枚硬币。
 * 这里的 i - 1 和 i + 1 代表和 i 相邻的两个气球的序号。
 * 如果 i - 1或 i + 1 超出了数组的边界，那么就当它是一个数字为 1 的气球。
 * 求所能获得硬币的最大数量。
 * 示例 1：
 * 输入：nums = [3,1,5,8]
 * 输出：167
 * 解释：
 * nums = [3,1,5,8] --> [3,5,8] --> [3,8] --> [8] --> []
 * coins =  3*1*5    +   3*5*8   +  1*3*8  + 1*8*1 = 167
 * 示例 2：
 * 输入：nums = [1,5]
 * 输出：10
 * 提示：
 * n == nums.length
 * 1 <= n <= 500
 * 0 <= nums[i] <= 100
 */
//system_study.class46.Code01_BurstBalloons
public class Problem_0312_BurstBalloons {

    public static int maxCoins(int[] nums) {
        if (nums==null || nums.length==0) return 0;
        int n = nums.length;
        int[] help = new int[n+2];
        for (int i = 0; i < n; i++) {
            help[i+1] = nums[i];
        }
        help[0] = 1;
        help[n+1] = 1;
        int m = help.length-1;
        int[][] dp = new int[m][m];
        return fun(help, 1,n,dp);
    }


    //需要补外部信息的尝试
    //潜台词：必须保证arr[l-1],arr[r+1]的气球没爆
    //返回arr[l,r]范围上的最大得分
    //枚举每个位置的气球最后爆
    public static int fun(int[] arr, int l, int r, int[][] dp) {
        if (dp[l][r]!=0) return dp[l][r];
        int ans;
        if (l>r) {
            ans = 0;
        } else if (l==r) {
            ans = arr[l-1] * arr[l] * arr[r+1];
        } else { //l<r
            //l位置的气球最后爆
            ans = fun(arr, l + 1, r, dp) + arr[l - 1] * arr[l] * arr[r + 1];
            //r位置的气球最后爆
            ans = Math.max(ans, fun(arr, l, r - 1, dp) + arr[l - 1] * arr[r] * arr[r + 1]);
            //枚举[l+1...r-1]每个位置最后爆
            for (int i = l + 1; i < r; i++) {
                int cur = fun(arr, l, i - 1, dp) + fun(arr, i + 1, r, dp) + arr[l - 1] * arr[i] * arr[r + 1];
                ans = Math.max(ans, cur);
            }
        }
        dp[l][r] = ans;
        return ans;
    }

    public static void main(String[] args) {
        int[] arr = {3,1,5,8};
        int ans = maxCoins(arr);
        System.out.println(ans);
    }

}
