package monotonous_stack;

import java.util.Stack;

/**
 * 456. 132 模式
 * 给你一个整数数组 nums ，数组中共有 n 个整数。
 * 132 模式的子序列 由三个整数 nums[i]、nums[j] 和 nums[k] 组成，并同时满足：i < j < k 和 nums[i] < nums[k] < nums[j] 。
 * 如果 nums 中存在 132 模式的子序列 ，返回 true ；否则，返回 false 。
 *
 * 示例 1：
 * 输入：nums = [1,2,3,4]
 * 输出：false
 * 解释：序列中不存在 132 模式的子序列。
 * 示例 2：
 * 输入：nums = [3,1,4,2]
 * 输出：true
 * 解释：序列中有 1 个 132 模式的子序列： [1, 4, 2] 。
 * 示例 3：
 * 输入：nums = [-1,3,2,0]
 * 输出：true
 * 解释：序列中有 3 个 132 模式的的子序列：[-1, 3, 2]、[-1, 3, 0] 和 [-1, 2, 0] 。
 *
 * 提示：
 * n == nums.length
 * 1 <= n <= 2 * 10^5
 * -10^9 <= nums[i] <= 10^9
 */
//456. 132 模式
//https://leetcode-cn.com/problems/132-pattern/
//https://leetcode.com/problems/132-pattern/discuss/94089/Java-solutions-from-O(n3)-to-O(n)-for-%22132%22-pattern-(updated-with-one-pass-slution)
public class Problem_0456_132Pattern {

    //暴力破解 O(n^3)
    public boolean find132pattern(int[] nums) {
        if (nums==null || nums.length<3) return false;
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                for (int k = j + 1; k < n; k++) {
                    if (nums[i] < nums[k] && nums[k] < nums[j]) return true;
                }
            }
        }
        return false;
    }


    //O(n^2)
    public boolean find132pattern2(int[] nums) {
        int n = nums.length;
        //min: 当前i位置，左边的最小值
        for (int i = 0, min = Integer.MAX_VALUE; i < n; i++) {
            min = Math.min(nums[i], min);
            if (min == nums[i]) continue;

            for (int k = n - 1; k > i; k--) {
                if (min < nums[k] && nums[k] < nums[i]) return true;
            }
        }

        return false;
    }


    //O(n)
    public static boolean find132pattern3(int[] nums) {
        int n = nums.length;
        Stack<Integer> stack = new Stack<>();
        Integer secondMax = null; //从右往左，找到以当前i位置为base的，第二大元素
        for (int i = n-1; i >= 0; i--) {
            if (secondMax!=null && nums[i]<secondMax) {
                System.out.println(secondMax);
                System.out.println(nums[i]);
                return true;
            }
            while (!stack.isEmpty() && stack.peek() < nums[i]) {
                secondMax = stack.pop();
            }
            stack.push(nums[i]);
        }

        return false;
    }


    public static void main(String[] args) {
        int[] arr = {-1,3,12,0,4,5};
        System.out.println(find132pattern3(arr));
    }
}
