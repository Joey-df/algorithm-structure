package backtrack;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 47. 全排列 II
 * 给定一个可包含重复数字的序列 nums ，按任意顺序 返回所有不重复的全排列。
 *
 * 示例 1：
 * 输入：nums = [1,1,2]
 * 输出：
 * [[1,1,2],
 *  [1,2,1],
 *  [2,1,1]]
 * 示例 2：
 * 输入：nums = [1,2,3]
 * 输出：[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
 */
//https://leetcode.com/problems/permutations-ii/
//求给定数组 的全排列
//包含重复字符
public class Problem_0047_PermutationsII {

    public static List<List<Integer>> permuteUnique(int[] nums) {
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
            Set<Integer> set = new HashSet<>(); // 每个index位置一个set，用于在当前位置去重
            for (int i = index; i < nums.length; i++) {
                if (!set.contains(nums[i])) {
                    set.add(nums[i]);
                    swap(nums, index, i);
                    process(nums, index + 1, ans);
                    swap(nums, index, i); // clear
                }
            }
        }
    }

    private static void swap(int[] nums, int i, int j) {
        int t = nums[i];
        nums[i] = nums[j];
        nums[j] = t;
    }

    public static void main(String[] args) {
        int[] nums = {1,3,2};
        System.out.println(permuteUnique(nums));
    }
}
