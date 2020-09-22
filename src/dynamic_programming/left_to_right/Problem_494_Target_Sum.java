package dynamic_programming.left_to_right;

import java.util.HashMap;
import java.util.Map;

/**
 * You are given a list of non-negative integers, a1, a2, ..., an, and a target, S.
 * Now you have 2 symbols + and -. For each integer, you should choose one from + and - as its new symbol.
 *
 * Find out how many ways to assign symbols to make sum of integers equal to target S.
 *
 * Example 1:
 *
 * Input: nums is [1, 1, 1, 1, 1], S is 3.
 * Output: 5
 * Explanation:
 *
 * -1+1+1+1+1 = 3
 * +1-1+1+1+1 = 3
 * +1+1-1+1+1 = 3
 * +1+1+1-1+1 = 3
 * +1+1+1+1-1 = 3
 *
 * There are 5 ways to assign symbols to make the sum of nums be target 3.
 *
 * Constraints:
 *
 * The length of the given array is positive and will not exceed 20.
 * The sum of elements in the given array will not exceed 1000.
 * Your output answer is guaranteed to be fitted in a 32-bit integer.
 *
 * 从左往后的尝试模型
 */
public class Problem_494_Target_Sum {

    //nums中的元素均非负
    //S：可正 可负 可0
    //递归含义：
    //[index...]自由选择，搞定S的方法数是多少？
    public static int process(int[] nums, int index, int S) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        if (index == nums.length) { //没有数可以选择了
            //已经没有数可以选择了，如果S==0有1种方案，否则没有方案。
            return S == 0 ? 1 : 0;
        }
        //index < nums.length
        //index前加'-' 的方法数
        int p1 = process(nums, index + 1, S + nums[index]);
        //index前加'+' 的方法数
        int p2 = process(nums, index + 1, S - nums[index]);
        return p1 + p2;
    }

    //含义：[index...]自由选择，搞定target的方法数是多少？
    //The sum of elements in the given array will not exceed 1000.
    //记忆化搜索
    public static int dpCache(int[] nums, int index, int target) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        Map<String, Integer> cache = new HashMap<>();
        if (cache.containsKey(target + "_" + nums[index])) {
            return cache.get(target + "_" + nums[index]);
        }
        if (index == nums.length) { //没有数可以选择了
            //已经没有数可以选择了，如果S==0有1种方案，否则没有方案。
            cache.put(target + "_" + nums[index], target == 0 ? 1 : 0);
            return cache.get(target + "_" + nums[index]);
        }
        //index < nums.length
        //index前加'-' 的方法数
        int p1 = process(nums, index + 1, target + nums[index]);
        //index前加'+' 的方法数
        int p2 = process(nums, index + 1, target - nums[index]);
        cache.put(target + "_" + nums[index], p1 + p2);
        return cache.get(target + "_" + nums[index]);
    }


    public static void main(String[] args) {
        int[] nums = {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1};
        int S = 7;
        System.out.println(process(nums, 0, S));
        System.out.println(dpCache(nums, 0, S));
    }
}
