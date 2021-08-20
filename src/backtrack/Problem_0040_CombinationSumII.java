package backtrack;

import java.util.*;

/**
 * 40. Combination Sum II
 * Given a collection of candidate numbers (candidates) and a target number (target),
 * find all unique combinations in candidates where the candidate numbers sum to target.
 *
 * Each number in candidates may only be used once in the combination.
 *
 * Note: The solution set must not contain duplicate combinations.
 *
 * Example 1:
 * Input: candidates = [10,1,2,7,6,1,5], target = 8
 * Output:
 * [
 * [1,1,6],
 * [1,2,5],
 * [1,7],
 * [2,6]
 * ]
 *
 * Example 2:
 * Input: candidates = [2,5,2,1,2], target = 5
 * Output:
 * [
 * [1,2,2],
 * [5]
 * ]
 */
public class Problem_0040_CombinationSumII {

    //Each number in candidates may only be used once in the combination.
    public static List<List<Integer>> combinationSum2(int[] nums, int target) {
        List<List<Integer>> ans = new ArrayList<>();
        Arrays.sort(nums);
        List<Integer> path = new ArrayList<>();
        process(nums,0,target,path,ans);
        return ans;
    }

    //nums 固定参数
    //[0,index-1]已经做好决定了，所做决定形成的路径，存在path中
    //[index...]自由选择，凑出rest
    //ans 收集答案使用
    private static void process(int[] nums, int index, int rest, List<Integer> path, List<List<Integer>> ans) {
        if (rest==0){
            ans.add(new ArrayList<>(path));
        } else {
            //if (rest<0) return;//如果题目要求nums为正数数组，加上这句
            Set<Integer> set = new HashSet<>();
            for (int i = index; i < nums.length; i++) {
                if (!set.contains(nums[i])) {
                    set.add(nums[i]);
                    path.add(nums[i]);
                    process(nums, i + 1, rest - nums[i], path, ans);
                    path.remove(path.size() - 1);
                }
            }
        }
    }


    public static void main(String[] args) {
        int[] nums = {10,1,-2,7,6,1,5,3};
        int target=8;
        System.out.println(combinationSum2(nums, target));
    }
}
