package two_pointers;

/**
 * 209. 累加和>=target的最短子数组(返回长度)
 * 给定一个含有 n 个正整数的数组和一个正整数 target 。
 * 找出该数组中满足其和 ≥ target 的长度最小的 连续子数组，并返回其长度。
 * 如果不存在符合条件的子数组，返回 0 。
 *
 * 示例 1：
 * 输入：target = 7, nums = [2,3,1,2,4,3]
 * 输出：2
 * 解释：子数组 [4,3] 是该条件下的长度最小的子数组。
 *
 * 示例 2：
 * 输入：target = 4, nums = [1,4,4]
 * 输出：1
 *
 * 示例 3：
 * 输入：target = 11, nums = [1,1,1,1,1,1,1,1]
 * 输出：0
 *
 * 提示：
 * 1 <= target <= 10^9
 * 1 <= nums.length <= 10^5
 * 1 <= nums[i] <= 10^5
 *
 * 进阶：
 * 如果你已经实现 O(n) 时间复杂度的解法, 请尝试设计一个 O(n log(n)) 时间复杂度的解法。
 */
//类似于coding_for_great_offer.class01.Code01_CordCoverMaxPoint
public class Problem_0209_MinimumSizeSubarraySum {

    //target = 7, nums = [2,3,1,2,4,3]
    public static int minSubArrayLen(int target, int[] nums) {
        int n = nums.length;
        int r = 0; //[l,r) r表示即将进入窗口的位置
        int windowSum = 0;
        int ans = Integer.MAX_VALUE;
        for (int l = 0; l < n; l++) { //尝试每一个开头的子数组
            while (r < n && windowSum < target) {
                windowSum += nums[r];
                r++;
            }
            if (windowSum >= target) {
                ans = Math.min(ans, r - l);
                //System.out.println(r + " " + l + " " + ans);
            }
            windowSum -= nums[l];
        }
        return ans==Integer.MAX_VALUE ? 0 :ans;
    }

    public static void main(String[] args) {
        int target = 4;
        int[] nums = {1,4,4};
        System.out.println(minSubArrayLen(target, nums));
    }
}
