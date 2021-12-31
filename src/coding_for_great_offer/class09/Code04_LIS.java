package coding_for_great_offer.class09;

/**
 * 经典算法之最长递增子序列
 * <p>
 * Leetcode 原题：
 * <p>
 * https://leetcode.com/problems/longest-increasing-subsequence
 */
public class Code04_LIS {

    //O(N*logN)
    public static int lengthOfLIS(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int N = arr.length;
        int[] dp = new int[N];//dp[i]:以i位置结尾的lis长度是多少
        int[] ends = new int[N];//ends[i]:所有找到的长度为i+1的lis的最小结尾是啥
        dp[0] = 1;
        ends[0] = arr[0];
        int ans = dp[0];
        int right = 0;//[0...right]为ends的有效区，有效区必有序
        for (int i = 1; i < N; i++) {
            int l = 0;
            int r = right;
            //在[0,right]上找>=arr[i]最左的位置
            while (l <= r) {
                int m = l + ((r - l) >> 1);
                if (ends[m] >= arr[i]) {
                    r = m - 1;
                } else {
                    l = m + 1;
                }
            }
            right = Math.max(l, right); //没找打就扩充有效区，找到了就是l
            ends[l] = arr[i];
            dp[i] = l + 1;
            ans = Math.max(ans, dp[i]);
        }
        return ans;
    }

    //O(N^2)
    public static int right(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int N = arr.length;
        int[] dp = new int[N];//dp[i]:必须以i位置结尾的最长递增子序列有多长
        dp[0] = 1;
        int ans = dp[0];
        for (int i = 1; i < N; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (arr[j] < arr[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            ans = Math.max(ans, dp[i]);
        }
        return ans;
    }
}
