package tow_pointer;

/**
 * Given an unsorted integer array nums, find the smallest missing positive integer.
 * <p>
 * Follow up: Could you implement an algorithm that runs in O(n) time and uses constant extra space.?
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [1,2,0]
 * Output: 3
 * Example 2:
 * <p>
 * Input: nums = [3,4,-1,1]
 * Output: 2
 * Example 3:
 * <p>
 * Input: nums = [7,8,9,11,12]
 * Output: 1
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 0 <= nums.length <= 300
 * -231 <= nums[i] <= 231 - 1
 */

//寻找缺失的最小正数
public class Problem_41_First_Missing_Positive {
    //初始L=0；R=越界位置
    //表示：[0,L-1]已经放好了i+1的数
    //R表示预期的是收集1~R的数
    public int firstMissingPositive(int[] nums) {
        int L = 0;
        int R = nums.length;
        while (L < R) { //L==R时停
            if (nums[L] == L + 1) {
                L++;
            } else if (nums[L] < L || nums[L] > R || nums[nums[L] - 1] == nums[L]) {
                swap(nums, L, --R);
            } else {
                swap(nums, L, nums[L] - 1); //把i放到i-1位置
            }
        }
        return L + 1;
    }

    private static void swap(int[] arr, int i, int j) {
        int t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }
}
