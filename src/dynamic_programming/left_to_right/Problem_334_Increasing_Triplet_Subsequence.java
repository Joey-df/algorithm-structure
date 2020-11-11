package dynamic_programming.left_to_right;

/**
 * Given an unsorted array return whether an increasing subsequence of length 3 exists or not in the array.
 * <p>
 * Formally the function should:
 * <p>
 * Return true if there exists i, j, k
 * such that arr[i] < arr[j] < arr[k] given 0 ≤ i < j < k ≤ n-1 else return false.
 * Note: Your algorithm should run in O(n) time complexity and O(1) space complexity.
 * <p>
 * Example 1:
 * <p>
 * Input: [1,2,3,4,5]
 * Output: true
 * Example 2:
 * <p>
 * Input: [5,4,3,2,1]
 * Output: false
 * <p>
 * 使用最长递增子序列O(N*logN)的解法
 * ends数组
 */
public class Problem_334_Increasing_Triplet_Subsequence {
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
