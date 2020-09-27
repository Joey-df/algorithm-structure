package dynamic_programming;

/**
 * Given an unsorted array of integers, find the length of longest increasing subsequence.
 * 
 * Example:
 * 
 * Input: [10,9,2,5,3,7,101,18]
 * Output: 4
 * Explanation: The longest increasing subsequence is [2,3,7,101], therefore the length is 4.
 * Note:
 * 
 * There may be more than one LIS combination, it is only necessary for you to return the length.
 * Your algorithm should run in O(n2) complexity.
 * Follow up: Could you improve it to O(n log n) time complexity?
 */
public class Problem_300_Longest_Increasing_Subsequence {

    //递归含义：
    //nums 以index位置结尾 的最长递增子序列lis是多长？返回
    public static int process(int[] nums, int index) {
        if (index == 0) {
            return 1;
        }
        //index>=1
        int ans = 1;
        //nums[index]前的所有数看一遍
        for (int i = 0; i < index; i++) {
            if (nums[i] < nums[index]) {
                int tmp = process(nums, i);
                ans = Math.max(ans, tmp + 1);
            }
        }
        return ans;
    }

    //lis是每一个位置结尾中 最大值
    public static int ways1(int[] nums) {
        int lis = 1;
        for (int i = 0; i < nums.length; i++) {
            int p = process(nums, i);
            lis = Math.max(lis, p);
        }
        return lis;
    }

    //动态规划
    //dp[i]的含义：以i位置结尾的lis是多长
    public static int dpWays(int[] nums) {
        int[] dp = new int[nums.length];
        dp[0] = 1;
        for (int i = 1; i < nums.length; i++) {
            dp[i] = 1;
            // 每个位置求lis 需要枚举前面的所有位置
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }

        int ans = 1;
        for (int i = 0; i < dp.length; i++) {
            ans = Math.max(ans, dp[i]);
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] nums = {1, 3, 6, 7, 9, 4, 10, 5, 6};

        System.out.println(ways1(nums));
        System.out.println(dpWays(nums));
    }
}
