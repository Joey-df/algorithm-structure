package array;

import java.util.HashSet;
import java.util.Set;

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
public class Problem_0041_FirstMissingPositive {

    //时间复杂度：O(n)，
    //额外空间复杂度：O(1)
    //初始l=0；r=越界位置
    //表示：[0,l-1]范围上每一个位置i上已经放好了i+1的数
    //r表示预期的是收集1~r的数
    public static int firstMissingPositive(int[] nums) {
        int l = 0;
        int r = nums.length;
        while (l < r) { //l==r时停
            if (nums[l] == l + 1) {
                l++;
            } else if (nums[l] <= l || nums[l] > r || nums[nums[l] - 1] == nums[l]) {
                swap(nums, l, --r);
            } else {
                swap(nums, l, nums[l] - 1); //把i放到i-1位置
            }
        }
        //[0,l-1]范围上每一个位置i上已经放好了i+1的数，即已经收集好[0,l]，所以返回l+1
        return l + 1;
    }

    private static void swap(int[] arr, int i, int j) {
        int t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }

    //方法2
    public static int firstMissingPositive2(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 1;
        }
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            if (num > 0) {
                set.add(num);
            }
        }
        int ans = 1;
        while (set.contains(ans)) {
            ans++;
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(firstMissingPositive2(new int[]{-1,3,5,6,7,8,9}));
    }
}
