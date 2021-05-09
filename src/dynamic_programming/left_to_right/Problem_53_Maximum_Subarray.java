package dynamic_programming.left_to_right;

/**
 * Given an integer array nums, find the contiguous subarray (containing at least one number) which has the largest sum and return its sum.
 * <p>
 * Follow up: If you have figured out the O(n) solution, try coding another solution using the divide and conquer approach, which is more subtle.
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [-2,1,-3,4,-1,2,1,-5,4]
 * Output: 6
 * Explanation: [4,-1,2,1] has the largest sum = 6.
 * Example 2:
 * <p>
 * Input: nums = [1]
 * Output: 1
 * Example 3:
 * <p>
 * Input: nums = [0]
 * Output: 0
 * Example 4:
 * <p>
 * Input: nums = [-1]
 * Output: -1
 * Example 5:
 * <p>
 * Input: nums = [-2147483647]
 * Output: -2147483647
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= nums.length <= 2 * 104
 * -231 <= nums[i] <= 231 - 1
 */

/**
 * 技巧总结
 * 子数组max/min问题：
 * 以每个i位置结尾的答案都求一遍，把所有答案中的max/min抓出来。
 * 组合、子序列：
 * 定义动态规划时，一般定义0~i范围上的所有情况，不严格规定结不结尾。
 */
//子数组 的最大累加和
public class Problem_53_Maximum_Subarray {
    //思路：必须以i位置结尾的子数组的最大累加和全部求一遍，找最大的
    public int maxSubArray(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int ans = nums[0];
        int pre = nums[0];//i-1位置结尾的子数组中 最大累加和 是多少？
        for (int i = 1; i < nums.length; i++) {
            int cur = nums[i]; //只包含i位置元素；
            cur = Math.max(cur, nums[i] + pre);//包含i位置 及其 i-1 的
            ans = Math.max(ans, cur);
            pre = cur;
        }
        return ans;
    }

    /**
     * 美团原题
     * 给定一个数组nums，选组合，不能选相邻的数，求所选组合的最大累加和
     * 打家劫舍问题
     */
    public int followUp(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0];
        }
        if (nums.length == 2) {
            return Math.max(nums[0], nums[1]);
        }
        //nums.length>=3
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);
        int ans = nums[0];
        for (int i = 2; i < nums.length; i++) {
            dp[i] = nums[i];//只要i位置元素
            dp[i] = Math.max(dp[i], dp[i - 1]);//不要i位置元素
            dp[i] = Math.max(dp[i], nums[i] + dp[i - 2]); //要i位置 同时 不要i-1位置
            ans = Math.max(ans, dp[i]);
        }
        return ans;
    }
}
