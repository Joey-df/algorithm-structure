package backtrack;

import java.util.ArrayList;
import java.util.List;

/**
 * Given an array nums of distinct integers, return all the possible permutations.
 * You can return the answer in any order.
 *
 * Example 1:
 * Input: nums = [1,2,3]
 * Output: [[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
 *
 * Example 2:
 * Input: nums = [0,1]
 * Output: [[0,1],[1,0]]
 *
 * Example 3:
 * Input: nums = [1]
 * Output: [[1]]
 */
//https://leetcode.com/problems/permutations/
//求给定数组 的全排列
//不包含重复字符
public class Problem_0046_Permutations {

    public static List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        if (nums==null || nums.length==0) {
            return ans;
        }
        process(nums, 0, ans);
        return ans;
    }

    //[0，index-1]已经做好决定了，不用操心了
    //当前来到index位置做决定
    //[0，index-1]做过的决定，形成的路径，即nums当前的状态
    private static void process(int[] nums, int index, List<List<Integer>> ans) {
        if (index==nums.length) {
            //表示[0,N-1]已经做好决定了，收集答案
            List<Integer> list = new ArrayList<>();
            for (int num: nums) {
                list.add(num);
            }
            ans.add(list);
        } else {
            for (int i = index; i < nums.length; i++) {
                swap(nums, index, i);
                process(nums, index+1, ans);
                swap(nums, index, i); // clear
            }
        }
    }

    private static void swap(int[] nums, int i, int j) {
        int t = nums[i];
        nums[i] = nums[j];
        nums[j] = t;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1,2,3};
        System.out.println(permute(nums));
    }
}
