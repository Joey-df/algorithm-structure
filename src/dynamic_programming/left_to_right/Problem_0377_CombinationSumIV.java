package dynamic_programming.left_to_right;

/**
 * Given an integer array with all positive numbers and no duplicates,
 * find the number of possible combinations that add up to a positive integer target.
 *
 * Example:
 *
 * nums = [1, 2, 3]
 * target = 4
 *
 * The possible combination ways are:
 * (1, 1, 1, 1)
 * (1, 1, 2)
 * (1, 2, 1)
 * (1, 3)
 * (2, 1, 1)
 * (2, 2)
 * (3, 1)
 *
 * Note that different sequences are counted as different combinations.
 *
 * Therefore the output is 7.
 *
 *
 * Follow up:
 * What if negative numbers are allowed in the given array?
 * How does it change the problem?
 * What limitation we need to add to the question to allow negative numbers?
 *
 * Credits:
 * Special thanks to @pbrother for adding this problem and creating all test cases.
 *
 * 完全背包问题
 */
public class Problem_0377_CombinationSumIV {

    //递归含义: nums[0...nums.length-1] 范围上 自由选择 组成target的方法数
    public static int process(int[] nums, int target) {
        if (nums == null || nums.length == 0 || target < 0) { //组成负数 有0种方法
            return 0;
        }
        if (target == 0) { //组成0有1种方法 这种方法叫什么数也不选
            return 1;
        }
        //rest>0
        int ans = 0;
        //枚举第一个数的取值
        for (int i = 0; i < nums.length; i++) {
            ans += process(nums, target - nums[i]);
        }
        return ans;
    }

    //nums:固定不变
    //变量：target
    //dp[t]的含义：组成t的方法数
    public static int dpWay(int[] nums, int target) {
        if (nums == null || nums.length == 0 || target < 0) { //组成负数 有0种方法
            return 0;
        }
        int[] dp = new int[target + 1];
        dp[0] = 1;
        for (int t = 1; t <= target; t++) {
            for (int j = 0; j < nums.length; j++) {
                if (t - nums[j] >= 0) // 注意这里
                    dp[t] += dp[t - nums[j]];
            }
        }
        return dp[target];
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3};
        int target = 4;
        System.out.println(process(nums, target));
        System.out.println(dpWay(nums, target));
    }
}
