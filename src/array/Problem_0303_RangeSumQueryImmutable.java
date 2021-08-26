package array;

//303. 区域和检索 - 数组不可变
//https://leetcode-cn.com/problems/range-sum-query-immutable/
//使用预处理结构（前缀和数组），快速求出任意一个子数组的和
public class Problem_0303_RangeSumQueryImmutable {

    static class NumArray {

        int[] preSum;//preSum[i]表示arr[0,i]的累加和

        public NumArray(int[] nums) {
            preSum = new int[nums.length];
            preSum[0] = nums[0];
            for (int i = 1; i < nums.length; i++) {
                preSum[i] = preSum[i-1] + nums[i];
            }
        }

        public int sumRange(int left, int right) {
            assert (left >= 0 && right >= left);
            return left == 0 ? preSum[right] : preSum[right] - preSum[left - 1];
        }
    }

    /**
     * Your NumArray object will be instantiated and called as such:
     * NumArray obj = new NumArray(nums);
     * int param_1 = obj.sumRange(left,right);
     */
}
