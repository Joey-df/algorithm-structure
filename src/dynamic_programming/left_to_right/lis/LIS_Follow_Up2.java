package dynamic_programming.left_to_right.lis;

import java.util.Arrays;

/**
 * 给定一个整型数组arr，去掉几个数可以做到arr整体先上升后下降的样子
 * 例如：arr=[0,1,6,3,4,3,2,1]
 * 去掉6就可以做到，返回1
 */
public class LIS_Follow_Up2 {

    public static int[] getLeftToRightDp(int[] nums) {
        if (nums == null || nums.length == 0) {
            return new int[0];
        }
        int[] dp = new int[nums.length];
        int[] ends = new int[nums.length];
        dp[0] = 1;
        ends[0] = nums[0];
        int right = 0;//ends的有效区 [0,right]
        for (int i = 1; i < nums.length; i++) {
            int l = 0;
            int r = right;
            while (l <= r) {
                int mid = (l + r) / 2;
                if (ends[mid] >= nums[i]) {
                    r = mid - 1;
                } else {
                    l = mid + 1;
                }
            }
            right = Math.max(right, l);
            ends[l] = nums[i];
            dp[i] = l + 1;
        }
        return dp;
    }

    public static int[] reverse(int[] nums) {
        if (nums == null || nums.length == 0) {
            return nums;
        }
        int[] res = Arrays.copyOf(nums, nums.length);
        int l = 0;
        int r = res.length - 1;
        while (l < r) {
            int t = res[l];
            res[l++] = res[r];
            res[r--] = t;
        }
        return res;
    }

    public static int process(int[] nums) {
        int[] lTor = getLeftToRightDp(nums);
        for (int i = 0; i < lTor.length; i++) {
            System.out.print(lTor[i] + " ");
        }
        System.out.println();
        int[] rTol = reverse(getLeftToRightDp(reverse(nums)));
        for (int i = 0; i < rTol.length; i++) {
            System.out.print(rTol[i] + " ");
        }
        System.out.println();
        int max = 0;
        for (int i = 0; i < nums.length; i++) {
            max = Math.max(max, lTor[i] + rTol[i] - 1);
        }
        return nums.length - max;
    }

    public static void main(String[] args) {
        int[] nums = {0, 1, 3, 6, 4, 3, 2, 1, 10};
        System.out.println(process(nums));
    }
}
