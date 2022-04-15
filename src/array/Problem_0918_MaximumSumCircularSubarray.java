package array;

/**
 * 918. 环形子数组的最大累加和
 * 给定一个长度为 n 的环形整数数组 nums ，返回 nums 的非空 子数组 的最大可能和 。
 * 环形数组 意味着数组的末端将会与开头相连呈环状。形式上， nums[i] 的下一个元素是 nums[(i + 1) % n] ， nums[i] 的前一个元素是 nums[(i - 1 + n) % n] 。
 * 子数组 最多只能包含固定缓冲区 nums 中的每个元素一次。形式上，对于子数组 nums[i], nums[i + 1], ..., nums[j] ，不存在 i <= k1, k2 <= j 其中 k1 % n == k2 % n 。
 * <p>
 * 示例 1：
 * 输入：nums = [1,-2,3,-2]
 * 输出：3
 * 解释：从子数组 [3] 得到最大和 3
 * 示例 2：
 * 输入：nums = [5,-3,5]
 * 输出：10
 * 解释：从子数组 [5,5] 得到最大和 5 + 5 = 10
 * 示例 3：
 * 输入：nums = [3,-2,2,-3]
 * 输出：3
 * 解释：从子数组 [3] 和 [3,-2,2] 都可以得到最大和 3
 * <p>
 * 提示：
 * n == nums.length
 * 1 <= n <= 3 * 10^4
 * -3 * 10^4 <= nums[i] <= 3 * 10^4​​​​​​​
 */
//原型：leetcode53
public class Problem_0918_MaximumSumCircularSubarray {

    //https://leetcode.com/problems/maximum-sum-circular-subarray/discuss/178422/One-Pass
    public static int maxSubarraySumCircular(int[] arr) {
        //前提：arr.len >= 1
        int total = arr[0]; //数组的累加和
        int globalMaxSum = arr[0]; //使用动态规划，求得的最大的子数组累加和
        int curMaxSum = arr[0]; //每一个位置i结尾的最大子数组累加和
        int globalMinSum = arr[0]; //使用动态规划，求得的最小的子数组累加和
        int curMinSum = arr[0]; //每一个位置i结尾的最小子数组累加和
        for (int i = 1; i < arr.length; i++) {
            total += arr[i];
            curMaxSum = Math.max(curMaxSum + arr[i], arr[i]);
            globalMaxSum = Math.max(globalMaxSum, curMaxSum);
            curMinSum = Math.min(curMinSum + arr[i], arr[i]);
            globalMinSum = Math.min(globalMinSum, curMinSum);
        }
        //如果arr整体都是负数，应该返回最大的那个负数，即globalMaxSum
        return globalMaxSum > 0 ? Math.max(globalMaxSum, total - globalMinSum) : globalMaxSum;
    }
}
