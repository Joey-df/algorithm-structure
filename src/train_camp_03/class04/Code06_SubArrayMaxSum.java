package train_camp_03.class04;

/**
 * 给定一个数组arr，返回子数组的最大累加和。
 * LeetCode 53 最大子数组累加和（E）
 * https://leetcode.com/problems/maximum-subarray/
 */
public class Code06_SubArrayMaxSum {

    public static int process(int[] nums) {
        if (nums == null || nums.length == 0) {
            throw new RuntimeException("nums is null");
        }
        int cur = 0;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            cur += nums[i];
            max = Math.max(max, cur);
            cur = Math.max(cur, 0); //cur加到0以下，恢复为0
        }
        return max;
    }

    public static void main(String[] args) {
        System.out.println(process(new int[]{-9, -5, -3, -1, 100, -10, 12, -1111}));
    }
}
