package two_pointers;

/**
 * 643. 子数组最大平均数 I
 * 给你一个由 n 个元素组成的整数数组 nums 和一个整数 k 。
 * 请你找出平均数最大且 长度为 k 的连续子数组，并输出该最大平均数。
 * 任何误差小于 10^-5 的答案都将被视为正确答案。
 * <p>
 * 示例 1：
 * 输入：nums = [1,12,-5,-6,50,3], k = 4
 * 输出：12.75
 * 解释：最大平均数 (12-5-6+50)/4 = 51/4 = 12.75
 * <p>
 * 示例 2：
 * 输入：nums = [5], k = 1
 * 输出：5.00000
 * <p>
 * 提示：
 * n == nums.length
 * 1 <= k <= n <= 10^5
 * -10^4 <= nums[i] <= 10^4
 */
public class Problem_0643_MaximumAverageSubarrayI {

    //使用前缀和
    public static double findMaxAverage(int[] nums, int k) {
        if (nums == null || nums.length < k || k < 0) return 0D;
        int n = nums.length;
        int[] preSum = new int[n];
        preSum[0] = nums[0];
        for (int i = 1; i < n; i++) {
            preSum[i] = preSum[i - 1] + nums[i];
        }
        double ans = 0D;
        for (int i = k - 1; i < n; i++) { //枚举每个子数组的结尾位置
            if (i == k - 1) {
                ans = (double) preSum[i] / k;
            } else {
                ans = Math.max(ans, (double) (preSum[i] - preSum[i - k]) / k);
            }
        }
        return ans;
    }


    //滑动窗口
    //转化为求长度固定为k的子数组，最大累加和是多少的问题
    public double findMaxAverage2(int[] nums, int k) {
        int sum = 0;
        for (int i = 0; i < k; i++) {
            sum += nums[i];
        }

        int maxSum = sum;
        for (int l = 0, r = k; r < nums.length; l++, r++) {
            sum = sum - nums[l] + nums[r];
            maxSum = Math.max(sum, maxSum);
        }

        return ((double) maxSum) / ((double) k);
    }

    public double findMaxAverage3(int[] nums, int k) {
        if (nums == null || nums.length < k || k < 0) return 0D;
        int n = nums.length;
        int windowSum = 0;
        //先让窗口涨到k-1的长度
        for (int i = 0; i < k - 1; i++) {
            windowSum += nums[i];
        }
        int maxSum = Integer.MIN_VALUE;
        for (int l = 0, r = k - 1; r < n; l++, r++) { //计算每一个[l,r]的窗口
            windowSum += nums[r]; //右边进一个，形成[l,r]的窗口
            maxSum = Math.max(maxSum, windowSum);
            windowSum -= nums[l]; //l出窗口，即将进入l+1开头的窗口
        }
        return ((double) maxSum) / ((double) k);
    }


    public static void main(String[] args) {
        int[] nums = {1, 12, -5, -6, 50, 3};
        int k = 4;
        System.out.println(findMaxAverage(nums, k));
    }

}
