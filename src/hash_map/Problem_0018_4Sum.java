package hash_map;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given an array nums of n integers and an integer target, are there elements a, b, c, and d in nums such that a + b + c + d = target?
 * Find all unique quadruplets in the array which gives the sum of target.
 *
 * Notice that the solution set must not contain duplicate quadruplets.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,0,-1,0,-2,2], target = 0
 * Output: [[-2,-1,1,2],[-2,0,0,2],[-1,0,0,1]]
 * Example 2:
 *
 * Input: nums = [], target = 0
 * Output: []
 *
 *
 * Constraints:
 *
 * 0 <= nums.length <= 200
 * -109 <= nums[i] <= 109
 * -109 <= target <= 109
 */
public class Problem_0018_4Sum {

    //在nums[start...N-1]区间求和为target的不重复的二元祖
    //潜台词：nums有序
    private static List<List<Integer>> twoSum(int[] nums, int start, int target) {
        List<List<Integer>> ans = new ArrayList<>();
        int N = nums.length;
        int L = start, R = N - 1;
        while (L < R) {
            if (nums[L] + nums[R] > target) {
                R--;
            } else if (nums[L] + nums[R] < target) {
                L++;
            } else { //nums[L]+nums[R]==target
                if (L == start || nums[L] != nums[L - 1]) { //nums[L]==nums[L-1]时不收集答案
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

    //在nums[start...N-1]区间求和为target的不重复的三元组
    //潜台词：nums有序
    private static List<List<Integer>> threeSum(int[] nums, int start, int target) {
        List<List<Integer>> ans = new ArrayList<>();
        int N = nums.length;
        for (int i = start; i < N - 2; i++) {
            if (i > start && nums[i] == nums[i - 1]) continue;
            List<List<Integer>> twoSum = twoSum(nums, i + 1, target - nums[i]);
            for (List<Integer> sub : twoSum) {
                sub.add(0, nums[i]);
                ans.add(sub);
            }
        }
        return ans;
    }


    public static List<List<Integer>> fourSum(int[] nums, int target) {
        Arrays.sort(nums);
        List<List<Integer>> ans = new ArrayList<>();
        int N = nums.length;
        for (int i = 0; i < N - 3; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) continue;
            List<List<Integer>> threeSum = threeSum(nums, i + 1, target - nums[i]);
            for (List<Integer> sub : threeSum) {
                sub.add(0, nums[i]);
                ans.add(sub);
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        //[[-2,-1,1,2],[-2,0,0,2],[-1,0,0,1]]
        System.out.println(fourSum(new int[]{1, 0, -1, 0, -2, 2}, 0));
    }
}
