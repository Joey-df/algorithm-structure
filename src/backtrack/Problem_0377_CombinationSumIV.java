package backtrack;

import java.util.ArrayList;
import java.util.List;

/**
 * 377. 组合总和 Ⅳ
 * 给你一个由 不同 正数组成的数组 nums ，和一个目标整数 target 。
 * 请你从 nums 中找出并返回总和为 target 的元素组合的个数。
 *
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
public class Problem_0377_CombinationSumIV {

    public static int combinationSum4(int[] nums, int target) {
        if (nums==null || nums.length==0) {
            return 0;
        }
        return process(nums, target);
    }

    //递归含义
    //nums[0,N-1]自由选择，组成rest的方法数，返回
    private static int process(int[] nums, int rest) {
        if(nums==null || nums.length==0||rest<0) {
            return 0;
        }
        if (rest==0) { //之前的选择，让rest变0，是一种方法
            return 1;
        }
        int ans = 0;
        for (int i = 0; i < nums.length; i++) {
            //枚举第一个数
            ans += process(nums, rest-nums[i]);
        }
        return ans;
    }

    //得到所有方案
    public static void fun(int[] nums, int rest, List<Integer> path, List<List<Integer>> ans)  {
        if(nums==null || nums.length==0||rest<0) {
            return;
        }
        if (rest==0) { //之前的选择，让rest变0，是一种方法
            ans.add(new ArrayList<>(path));
        }
        for (int i = 0; i < nums.length; i++) {
            //枚举第一个数
            path.add(nums[i]);
            fun(nums, rest-nums[i],path,ans);
            path.remove(path.size()-1);
        }
    }

    public static void main(String[] args) {
        int[] nums = {1,2,3};
        int target = 4;
        System.out.println(combinationSum4(nums, target));
        List<List<Integer>> ans = new ArrayList<>();
        fun(nums,target, new ArrayList<>(), ans);
        System.out.println(ans);
    }
}
