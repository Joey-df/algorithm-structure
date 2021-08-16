package backtrack;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Given a set of distinct integers, nums, return all possible subsets (the power set).
 *
 * Note: The solution set must not contain duplicate subsets.
 *
 * Example:
 *
 * Input: nums = [1,2,3]
 * Output:
 * [
 *      [3],
 *      [1],
 *      [2],
 *      [1,2,3],
 *      [1,3],
 *      [2,3],
 *      [1,2],
 *      []
 * ]
 */
//https://leetcode.com/problems/subsets/
//数组的子序列（不包含重复字符）
public class Problem_0078_Subsets {

    //每个位置都有2种选择：要 or 不要
    //递归含义：
    //nums[index...N-1]上所有的子集 返回
    //nums[0...index-1]上已经搞定了，所形成的路径是path
    //ans 收集答案用
    public static void process(int[] nums, int index, LinkedList<Integer> path, List<List<Integer>> ans) {
        int N = nums.length;
        if (index == N) {
            //代表nums[0...N-1]上已经搞定了，所形成的路径是path
            //所以 此时应该收集答案
            ans.add(new ArrayList<>(path));
            return;
        }
        // 不要nums[index]
        process(nums, index + 1, path, ans);
        // 要num[index]
        path.addLast(nums[index]);
        process(nums, index + 1, path, ans);
        path.pollLast(); //清理现场
    }

    public static List<List<Integer>> ways(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        LinkedList<Integer> path = new LinkedList<>();
        process(nums, 0, path, ans);
        return ans;
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3};
        System.out.println(ways(nums));
    }
}
