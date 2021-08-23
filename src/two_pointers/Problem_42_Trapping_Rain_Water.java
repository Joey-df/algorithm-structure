package two_pointers;

/**
 * Given n non-negative integers representing an elevation map where the width of each bar is 1,
 * compute how much water it can trap after raining.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: height = [0,1,0,2,1,0,1,3,2,1,2,1]
 * Output: 6
 * Explanation: The above elevation map (black section) is represented by array [0,1,0,2,1,0,1,3,2,1,2,1].
 * In this case, 6 units of rain water (blue section) are being trapped.
 * Example 2:
 * <p>
 * Input: height = [4,2,0,3,2,5]
 * Output: 9
 * <p>
 * <p>
 * Constraints:
 * <p>
 * n == height.length
 * 0 <= n <= 3 * 104
 * 0 <= height[i] <= 105
 */
public class Problem_42_Trapping_Rain_Water {
    public static int trap(int[] nums) {
        if (nums == null || nums.length < 3) return 0;
        int leftMax = nums[0]; //l左边的最大值
        int rightMax = nums[nums.length - 1]; //r右边的最大值
        int l = 1, r = nums.length - 2;//0、N-1两个位置没有水量产生
        int ans = 0;
        while (l <= r) {//l>r停
            //谁小结算谁 水量取决于左右的短板
            if (leftMax <= rightMax) {
                ans += Math.max(0, (leftMax - nums[l]));
                leftMax = Math.max(leftMax, nums[l++]);
            } else {
                ans += Math.max(0, (rightMax - nums[r]));
                rightMax = Math.max(rightMax, nums[r--]);
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(trap(new int[]{4, 2, 0, 3, 2, 5}));
        System.out.println(trap(new int[]{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1}));
    }
}
