package backtrack;

import java.util.*;

/**
 * 90. 子集 II
 * 给你一个整数数组 nums ，其中可能包含重复元素，请你返回该数组所有可能的子集（幂集）。
 * 解集 不能 包含重复的子集。返回的解集中，子集可以按 任意顺序 排列。
 *
 * 示例 1：
 * 输入：nums = [1,2,2]
 * 输出：[[],[1],[1,2],[1,2,2],[2],[2,2]]
 * 示例 2：
 * 输入：nums = [0]
 * 输出：[[],[0]]
 */
//https://leetcode.com/problems/subsets-ii/
//求给定数组的子序列（包含重复字符）
public class Problem_0090_SubsetsII {

    //缺点：最后再去重
    public static void process(int[] nums, int index, List<Integer> path, List<List<Integer>> ans, Set<String> set) {
        int N = nums.length;
        if (index == N) {
            int[] sub = new int[path.size()];
            for (int i = 0; i < path.size(); i++) {
                sub[i] = path.get(i);
            }
            Arrays.sort(sub);
            StringBuilder sb = new StringBuilder();
            for (int n : sub) {
                sb.append(n);
            }
            if (!set.contains(sb.toString())) {
                ans.add(new ArrayList<>(path));
                set.add(sb.toString());
            }
        } else {
            // 不要当前位置num
            process(nums, index + 1, path, ans, set);
            // 要当前位置num
            path.add(nums[index]);
            process(nums, index + 1, path, ans, set);
            path.remove(path.size() - 1);
        }
    }


    public static List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        Set<String> set = new HashSet<>();
        process(nums, 0, path, ans, set);
        return ans;
    }

    public static void main(String[] args) {
        int[] nums = {1,2,2};
        System.out.println(subsetsWithDup(nums));
    }
}
