package array;

import java.util.Arrays;

/**
 * 628. 三个数的最大乘积
 * 给你一个整型数组 nums ，在数组中找出由三个数组成的最大乘积，并输出这个乘积。
 *
 * 示例 1：
 * 输入：nums = [1,2,3]
 * 输出：6
 * 示例 2：
 * 输入：nums = [1,2,3,4]
 * 输出：24
 * 示例 3：
 * 输入：nums = [-1,-2,-3]
 * 输出：-6
 *
 * 提示：
 * 3 <= nums.length <= 10^4
 * -1000 <= nums[i] <= 1000
 */
//进阶：leetcode152 子数组的最大累乘积
public class Problem_0628_MaximumProductOfThreeNumbers {

    public int maximumProduct(int[] nums) {
        if (nums==null || nums.length<3) {
            return 0;
        }
        if (nums.length==3) {
            return nums[0]*nums[1]*nums[2];
        }
        //N>3
        Arrays.sort(nums);
        int N = nums.length;
        int p1=nums[N-1]*nums[N-2]*nums[N-3]; //最大的三个数相乘
        int p2=nums[0]*nums[1]*nums[N-1]; //最小的两个数 * 最大的数
        return Math.max(p1,p2);
    }

}
