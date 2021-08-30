package binary_search;

/**
 * 300. 最长递增子序列
 * 给你一个整数数组 nums ，找到其中最长严格递增子序列的长度。
 */
public class Problem_0300_LongestIncreasingSubsequence {

    public static int lengthOfLIS(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int n = nums.length;
        int[] dp = new int[n]; //dp[i]:以i位置结尾的lis是多长
        int[] ends = new int[n];//ends[i]:所有找到的长度为i+1的lis的最小结尾是啥
        dp[0] = 1;
        ends[0] = nums[0];
        int ans = dp[0];
        int right = 0; //[0,right]为有效区，有效区必有序
        for (int i = 1; i < n; i++) {
            int l = 0;
            int r = right;
            //在ends[0,right]上找>=nums[i]最左的位置
            while (l <= r) {
                int m = l + ((r - l) >> 1);
                if (ends[m] >= nums[i]) {
                    r = m - 1;
                } else {
                    l = m + 1;
                }
            }
            right = Math.max(right, l);
            ends[l] = nums[i];
            dp[i] = l + 1;
            ans = Math.max(ans, dp[i]);
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(lengthOfLIS(new int[]{3, 2, 43, 13, 22}));
    }
}
