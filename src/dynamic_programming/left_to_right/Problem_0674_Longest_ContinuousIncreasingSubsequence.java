package dynamic_programming.left_to_right;

//674. Longest Continuous Increasing Subsequence
/**
 * 674. 最长连续递增序列
 * 给定一个未经排序的整数数组，找到最长且 连续递增的子序列，并返回该序列的长度。
 * 连续递增的子序列 可以由两个下标 l 和 r（l < r）确定，
 * 如果对于每个 l <= i < r，都有 nums[i] < nums[i + 1] ，那么子序列 [nums[l], nums[l + 1], ..., nums[r - 1], nums[r]] 就是连续递增子序列。
 *
 * 示例 1：
 * 输入：nums = [1,3,5,4,7]
 * 输出：3
 * 解释：最长连续递增序列是 [1,3,5], 长度为3。
 * 尽管 [1,3,5,7] 也是升序的子序列, 但它不是连续的，因为 5 和 7 在原数组里被 4 隔开。
 *
 * 示例 2：
 * 输入：nums = [2,2,2,2,2]
 * 输出：1
 * 解释：最长连续递增序列是 [2], 长度为1。
 *
 * 提示：
 * 1 <= nums.length <= 10^4
 * -10^9 <= nums[i] <= 10^9
 */
//最长递增子数组
public class Problem_0674_Longest_ContinuousIncreasingSubsequence {

    public int findLengthOfLCIS(int[] nums) {
        if (nums==null || nums.length==0) return 0;
        int n = nums.length;
        int[] dp = new int[n]; //dp[i]: 子数组必须以i开头，递增子数组有多长
        dp[n-1] = 1;
        int ans = dp[n-1];
        for (int i = n-2; i >= 0; i--) {
            dp[i] = nums[i]<nums[i+1] ? dp[i+1]+1 : 1;
            ans = Math.max(ans, dp[i]);
        }
        return ans;
    }

    //使用空间压缩，额外空间复杂度O(1)
    public int findLengthOfLCIS2(int[] nums) {
        if (nums==null || nums.length==0) return 0;
        int n = nums.length;
        int last = 1; //子数组必须以i+1位置开头，最长递增子数组是多长
        int ans = last;
        for (int i = n-2; i >= 0; i--) {
            int cur = nums[i]<nums[i+1] ? last+1 : 1;
            ans = Math.max(ans, cur);
            last = cur;
        }
        return ans;
    }

    //给定整体无序的整形数组nums
    //返回最长连续子数组的起始位置
    //如{7,8,1,2,3,4,5,4,3}, 返回2
    //解释：最长连续子数组为{1,2,3,4,5},起始位置是2
    public static int fun(int[] nums) {
        if(nums==null||nums.length==0) return -1;
        int n=nums.length;
        int[] dp = new int[n]; //dp[i]:必须以i位置结尾的最长连续子数组的长度是多长？
        dp[0]=1;
        int max=1; //最长连续子数组的长度
        int ansIndex=0;
        for(int i=1;i<n;i++) {
            dp[i]=nums[i]-nums[i-1]==1 ? dp[i-1]+1 : 1;
            if (dp[i] > max) {
                max=dp[i];
                ansIndex=i-max+1;
            }
        }
        return ansIndex;
    }

    public static void main(String[] args) {
        int[] arr = {7,8,10,11,4,3};
        int index = fun(arr);
        System.out.println(index);
    }

}
