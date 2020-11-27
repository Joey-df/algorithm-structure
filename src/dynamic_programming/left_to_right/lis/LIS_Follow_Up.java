package dynamic_programming.left_to_right.lis;

/**
 * 给定一个整型数组arr，有一种操作：一个数可以往前放 或者 往后放
 * 求至少需要做几次这样的操作，让arr整体变有序
 */
public class LIS_Follow_Up {

    //思路：求出最长递增子序列之后，和数组长度做差值即可
    public static int getLIS(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int[] dp = new int[nums.length];
        int[] ends = new int[nums.length];//ends[i]:长度为i+1的递增子序列的最小结尾是啥
        dp[0] = 1;
        ends[0] = nums[0];
        int right = 0;//ends的有效区[0,right]
        int ans = dp[0];
        for (int i = 0; i < nums.length; i++) {
            int l = 0;
            int r = right;
            while (l <= r) {
                int m = (l + r) / 2;
                if (ends[m] >= nums[i]) {
                    r = m - 1;
                } else {
                    l = m + 1;
                }
            }
            right = Math.max(right, l);
            ends[l] = nums[i];
            dp[i] = l + 1;
            ans = Math.max(ans, dp[i]);
        }
        return ans;
    }

    public static int getNum(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int lis = getLIS(nums);
        return nums.length - lis;
    }

    public static void main(String[] args) {
        System.out.println(getNum(new int[]{1, 2, 9, 3, 4, 5, 0}));
    }
}
