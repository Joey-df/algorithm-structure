package dynamic_programming.left_to_right;

/**
 * 334. 递增的三元子序列
 * 给你一个整数数组 nums ，判断这个数组中是否存在长度为 3 的递增子序列。
 * 如果存在这样的三元组下标 (i, j, k) 且满足 i < j < k ，使得 nums[i] < nums[j] < nums[k] ，返回 true ；
 * 否则，返回 false 。
 * 示例 1：
 * 输入：nums = [1,2,3,4,5]
 * 输出：true
 * 解释：任何 i < j < k 的三元组都满足题意
 * 示例 2：
 * 输入：nums = [5,4,3,2,1]
 * 输出：false
 * 解释：不存在满足题意的三元组
 * 使用最长递增子序列O(N*logN)的解法
 * ends数组
 */
public class Problem_0334_IncreasingTripletSubsequence {
    //转化为求长度为3的递增子序列问题
    public boolean increasingTriplet(int[] nums) {
        if (nums == null || nums.length < 3) return false;
        int N = nums.length;
        int[] ends = new int[N]; //ends[i]表示找到的所有的长度为i+1的递增子序列的最小结尾是啥
        ends[0] = nums[0];
        int right = 0; //表示[0,right]为有效区
        for (int i = 1; i < N; i++) {
            int l = 0;
            int r = right;
            //在[l,r]范围上找>=nums[i]最左侧的位置
            while (l <= r) {
                int mid = (l + r) / 2;
                if (ends[mid] >= nums[i]) {
                    r = mid - 1;
                } else {
                    l = mid + 1;
                }
            }
            //如果找到了 就是l，如果没找到 l会来到right+1的位置
            right = Math.max(right, l);
            if (right == 2) {
                return true; //有效区只要扩到3个就返回true
            }
            ends[l] = nums[i];
        }
        return false;
    }
}
