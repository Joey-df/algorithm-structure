package dynamic_programming.left_to_right;

/**
 * 416. 分割等和子集
 * 给你一个 只包含正整数 的 非空 数组 nums 。请你判断是否可以将这个数组分割成两个子集，使得两个子集的元素和相等。
 *
 * 示例 1：
 * 输入：nums = [1,5,11,5]
 * 输出：true
 * 解释：数组可以分割成 [1, 5, 5] 和 [11] 。
 *
 * 示例 2：
 * 输入：nums = [1,2,3,5]
 * 输出：false
 * 解释：数组不能分割成两个元素和相等的子集。
 */
//01背包问题
//https://leetcode.com/problems/partition-equal-subset-sum/discuss/90592/01-knapsack-detailed-explanation
public class Problem_0416_PartitionEqualSubsetSum {

    //arr中包含正数和0时有效
    public boolean canPartition(int[] arr) {
        if (arr==null || arr.length<2) {
            return false;
        }
        int sum = 0;
        for (int n: arr) sum+=n;
        if ((sum & 1) == 1) return false;
        sum = sum >> 1;
        //求arr中是否存在累加和为sum的子集
        int n = arr.length;
        //dp[i][j]: arr前缀长度为i的所有子集，有多少子集的累加和是j？
        int[][] dp = new int[n+1][sum+1];

        dp[0][0] = 1; //空集累加出0，有1种方法
        //dp[0][1...] = 0 //前缀长度0，累加出1，2，3...，0种方法
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= sum; j++) {
                int p1 = dp[i-1][j]; //arr[i-1]不参与
                int p2 = 0; //arr[i-1]参与
                if (j >= arr[i-1]) {
                    p2 = dp[i-1][j-arr[i-1]];
                }
                dp[i][j] = p1+p2;
            }
            if (dp[i][sum]>0) {
                return true;
            }
        }
        return false;
    }


    //arr中包含正数和0时有效
    public boolean canPartition2(int[] arr) {
        if (arr==null || arr.length<2) return false;
        int n = arr.length;
        int sum = 0;
        for (int num: arr) sum += num;
        if ((sum&1) != 0) return false; //累加和不是偶数，直接false
        sum = sum >> 1;
        boolean[][] dp = new boolean[n+1][sum+1];
        //dp[i][j]: arr前缀长度为i的所有子集中，是否有子集的累加和等于j
        for (int i = 0; i <= sum; i++) {
            dp[0][i] = (i==0) ? true : false;
        }
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= sum; j++) {
                boolean p1 = dp[i-1][j];
                boolean p2 = false;
                if (j-arr[i-1] >= 0) {
                    p2 = dp[i-1][j-arr[i-1]];
                }
                dp[i][j] = p1 || p2;
            }
            if (dp[i][sum]) {
                return true;
            }
        }
        return false;
    }

    //arr中仅包含正数时有效（包含0时无效）
    public boolean canPartition3(int[] arr) {
        if (arr==null || arr.length<2) return false;
        int n = arr.length;
        int sum = 0;
        for (int num: arr) {
            sum += num;
        }
        if ((sum&1)==1) return false;
        sum = sum >> 1;
        boolean[][] dp = new boolean[n][sum+1]; //dp[i][j]: arr[0..i]能否累加出j
        dp[0][0] = true; //arr[0..0]可以累加出0，什么也不选
        //第一行
        for (int i = 1; i <= sum; i++) {
            dp[0][i] = (arr[0]==i);
        }
        //第一列
        for (int j = 1; j < n; j++) {
            dp[j][0] = true; //arr[0,j]可以累加出0，什么也不选
        }
        for (int i = 1; i < n; i++) {
            for (int j = 1; j <= sum; j++) {
                dp[i][j] = dp[i-1][j];
                if (j-arr[i] >= 0) {
                    dp[i][j] |= dp[i-1][j-arr[i]];
                }
            }
            if (dp[i][sum]) return true;
        }
        return false;
    }

}
