package hash_table;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given an array nums of n integers, are there elements a, b, c in nums such that a + b + c = 0?
 * Find all unique triplets in the array which gives the sum of zero.
 * <p>
 * Notice that the solution set must not contain duplicate triplets.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [-1,0,1,2,-1,-4]
 * Output: [[-1,-1,2],[-1,0,1]]
 * Example 2:
 * <p>
 * Input: nums = []
 * Output: []
 * Example 3:
 * <p>
 * Input: nums = [0]
 * Output: []
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 0 <= nums.length <= 3000
 * -105 <= nums[i] <= 105
 */
public class Problem_15_3Sum {

    //求nums[begin...N-1]和为target的所有不重复的二元祖
    //潜台词：nums必须有序
    private static List<List<Integer>> twoSum(int[] nums, int begin, int target) {
        //eg: 求[-1,-1,-1,4,4,4]求和为3的tuple
        List<List<Integer>> ans = new ArrayList<>();
        int L = begin, R = nums.length - 1;
        while (L < R) {
            if (nums[L] + nums[R] > target) {
                R--;
            } else if (nums[L] + nums[R] < target) {
                L++;
            } else { //nums[L] + nums[R] == target
                if (L == begin || nums[L] > nums[L - 1]) {
                    List<Integer> sub = new ArrayList<>();
                    sub.add(nums[L]);
                    sub.add(nums[R]);
                    ans.add(sub);
                }
                L++;
            }
        }
        return ans;
    }

    public static List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> ans = new ArrayList<>();
        //尝试[0...N-3]每一个作为第一个元素
        for (int i = 0; i < nums.length - 2; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) continue;
            //nums[i]做第一个元素时，找到的所有二元祖
            List<List<Integer>> twoSum = twoSum(nums, i + 1, -nums[i]);
            for (List<Integer> cur : twoSum) {
                cur.add(0, nums[i]);
                ans.add(cur);
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(threeSum(new int[]{-1, 0, 1, 2, -1, -4}));
    }
}
