package leetcode_top_interview_questions;

/**
 * 300. 最长递增子序列
 * 给你一个整数数组 nums ，找到其中最长严格递增子序列的长度。
 */
public class Problem_0300_LongestIncreasingSubsequence {

    public static int lengthOfLIS(int[] nums) {
        if (nums==null || nums.length==0) {
            return 0;
        }
        if (nums.length==1) {
            return 1;
        }
        int ans = 1;
        int N = nums.length;
        int[] dp = new int[N];//dp[i]:必须以i位置结尾的LIS是多长
        int[] ends = new int[N];//ends[i]:找到的所有长度为i+1的LIS中，最小结尾是啥
        dp[0] = 1;
        ends[0] = nums[0];
        int right = 0;//有效区的右边界
        for(int i=1; i<N; i++) {
            int l=0;
            int r=right;
            // 在[0,right]范围上找>=nums[i]最左的位置
            while (l<=r) {
                int m = (l+r)>>1;
                if (ends[m] >= nums[i]) {
                    r = m - 1;
                } else {
                    l = m + 1;
                }
            }
            right = Math.max(right, l);
            ends[l] = nums[i];
            dp[i] = l+1;
            ans = Math.max(ans, dp[i]);
        }
        return ans;
    }
}
