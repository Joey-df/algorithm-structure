package dynamic_programming.left_to_right;

/**
 * Given an integer array nums, find the contiguous subarray within an array (containing at least one number) which has the largest product.
 * <p>
 * Example 1:
 * <p>
 * Input: [2,3,-2,4]
 * Output: 6
 * Explanation: [2,3] has the largest product 6.
 * Example 2:
 * <p>
 * Input: [-2,0,-1]
 * Output: 0
 * Explanation: The result cannot be 2, because [-2,-1] is not a subarray.
 * <p>
 * 子数组的最大累乘积
 * 0～N-1：求出必须以i结尾的子数组的最大累乘积 求max
 */
public class Problem_0152_MaximumProductSubarray {

    // 前提：输入必须有效 nums.length > 0
    public int maxProduct(int[] nums) {
        int ans = nums[0];
        int max = nums[0];//i-1结尾的最大累乘积
        int min = nums[0];//i-1结尾的最小累乘积
        for (int i = 1; i < nums.length; i++) {
            int curMin = Math.min(nums[i], Math.min(nums[i] * min, nums[i] * max));
            int curMax = Math.max(nums[i], Math.max(nums[i] * min, nums[i] * max));
            ans = Math.max(ans, curMax);
            min = curMin;
            max = curMax;
        }
        return ans;
    }
}
