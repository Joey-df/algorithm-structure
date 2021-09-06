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
public class Problem_0494_TargetSum {

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

    //from左老师
    public static int findTargetSumWays2(int[] arr, int num) {
        int N = arr.length;
        // index变化范围是0..N
        // 如果arr所有数字的累加和是sum，那么pre的范围是：-sum ~ +sum
        // 所以改成记忆化搜索可能更好，不过这里无所谓了，改成严格表结构吧
        int sum = 0;
        for (int cur : arr) {
            sum += cur;
        }
        if (num > sum) {
            return 0;
        }
        int M = 2 * sum + 1;
        int[][] dp = new int[N + 1][M];
        // 举个例子，如果arr，累加和是10，那么pre有可能的范围是-10到+10
        // 所以我们需要准备21列，去对应pre的变化范围，也就是dp[N+1][2 * sum + 1]
        // 我们规定如何表示pre：用dp[...][sum+pre]来表示，当然也可以有其他的表示。
        // 如何表示pre=0的时候？dp[...][10+0]->用dp[...][10]来表示
        // 如何表示pre=1的时候？dp[...][10+1]->用dp[...][11]来表示
        // 如何表示pre=5的时候？dp[...][10+5]->用dp[...][15]来表示
        // 如何表示pre=10的时候？dp[...][10+10]->用dp[...][20]来表示
        // 如何表示pre=-1的时候？dp[...][10+(-1)]->用dp[...][9]来表示
        // 如何表示pre=-5的时候？dp[...][10+(-5)]->用dp[...][5]来表示
        // 如何表示pre=-10的时候？dp[...][10+(-10)]->用dp[...][0]来表示
        // 所以pre等于多少，都可以表示下
        dp[N][sum + num] = 1;
        for (int index = N - 1; index >= 0; index--) {
            for (int pre = -sum; pre <= sum; pre++) {
                if (sum + pre + arr[index] < M) {
                    dp[index][sum + pre] = dp[index + 1][sum + pre + arr[index]];
                }
                if (sum + pre - arr[index] >= 0) {
                    dp[index][sum + pre] += dp[index + 1][sum + pre - arr[index]];
                }
            }
        }
        return dp[0][sum + 0];
    }

    public static void main(String[] args) {
        int[] nums = {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1};
        int S = 7;
        System.out.println(process(nums, 0, S));
        System.out.println(dpCache(nums, 0, S));
    }
}
