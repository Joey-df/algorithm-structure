package dynamic_programming.left_to_right;

/**
 * 377. 组合总和 Ⅳ
 * 给你一个由 不同 整数组成的数组 nums ，和一个目标整数 target 。
 * 请你从 nums 中找出并返回总和为 target 的元素组合的个数。
 * 题目数据保证答案符合 32 位整数范围。
 *
 * 示例 1：
 * 输入：nums = [1,2,3], target = 4
 * 输出：7
 * 解释：
 * 所有可能的组合为：
 * (1, 1, 1, 1)
 * (1, 1, 2)
 * (1, 2, 1)
 * (1, 3)
 * (2, 1, 1)
 * (2, 2)
 * (3, 1)
 * 请注意，顺序不同的序列被视作不同的组合。
 *
 * 示例 2：
 * 输入：nums = [9], target = 3
 * 输出：0
 *
 * 提示：
 * 1 <= nums.length <= 200
 * 1 <= nums[i] <= 1000
 * nums 中的所有元素 互不相同
 * 1 <= target <= 1000
 */
//完全背包问题
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
