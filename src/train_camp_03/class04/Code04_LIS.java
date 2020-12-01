package train_camp_03.class04;

/**
 * 最长递增子序列问题的O(N*logN)的解法
 * <p>
 * https://leetcode.com/problems/longest-increasing-subsequence/
 */
public class Code04_LIS {

    public static int getLis(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int N = nums.length;
        int[] dp = new int[N];
        int[] ends = new int[N];//ends[i]:长度为i+1的递增子序列的最小结尾是啥？
        dp[0] = 1;
        ends[0] = nums[0];
        int ans = dp[0];
        int right = 0;//[0,right]为ends的有效区，有效区必有序
        for (int i = 1; i < N; i++) {
            int l = 0;
            int r = right;
            while (l <= r) {
                int m = l + ((r - l) >> 1);
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

    public static void main(String[] args) {
        System.out.println(getLis(new int[]{10, 9, 2, 5, 3, 7, 101, 18}));
    }
}
