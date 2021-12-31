package greedy;

/**
 * 152. 子数组最大累乘积
 * 给你一个整数数组 nums ，请你找出数组中乘积最大的连续子数组（该子数组中至少包含一个数字），并返回该子数组所对应的乘积。
 *
 * 示例 1:
 * 输入: [2,3,-2,4]
 * 输出: 6
 * 解释: 子数组 [2,3] 有最大乘积 6。
 * 示例 2:
 * 输入: [-2,0,-1]
 * 输出: 0
 * 解释: 结果不能为 2, 因为 [-2,-1] 不是子数组。
 */
public class Problem_0152_MaximumProductSubarray {

    public int maxProduct(int[] nums) {
        if (nums==null || nums.length==0) return 0;
        int min=nums[0]; //i-1位置的最小值
        int max=nums[0]; //i-1位置的最大值
        int ans=nums[0];
        int n = nums.length;
        for (int i = 1; i < n; i++) {
            //i位置的最小累乘积
            int curmin = Math.min(nums[i], Math.min(nums[i]*max, nums[i]*min));
            //i位置的最大累乘积
            int curmax = Math.max(nums[i], Math.max(nums[i]*max, nums[i]*min));
            ans = Math.max(ans, curmax);
            min = curmin;
            max = curmax;
        }
        return ans;
    }

}
