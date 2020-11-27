package dynamic_programming.left_to_right.lis;

/**
 * Given an unsorted array of integers, find the length of longest increasing subsequence.
 * <p>
 * Example:
 * <p>
 * Input: [10,9,2,5,3,7,101,18]
 * Output: 4
 * Explanation: The longest increasing subsequence is [2,3,7,101], therefore the length is 4.
 * Note:
 * <p>
 * There may be more than one LIS combination, it is only necessary for you to return the length.
 * Your algorithm should run in O(n2) complexity.
 * Follow up: Could you improve it to O(n log n) time complexity?
 * <p>
 * 重点为：O(N*logN)的解法 ends数组
 * 此题需要反复练习
 */
public class Problem_300_Longest_Increasing_Subsequence {

    //递归含义：
    //nums 以index位置结尾 的最长递增子序列lis是多长？返回
    public static int process(int[] nums, int index) {
        if (index == 0) {
            return 1;
        }
        //index>=1
        int ans = 1;
        //nums[index]前的所有数看一遍
        for (int i = 0; i < index; i++) {
            if (nums[i] < nums[index]) {
                int tmp = process(nums, i);
                ans = Math.max(ans, tmp + 1);
            }
        }
        return ans;
    }

    //lis是每一个位置结尾中 最大值
    public static int ways1(int[] nums) {
        int lis = 1;
        for (int i = 0; i < nums.length; i++) {
            int p = process(nums, i);
            lis = Math.max(lis, p);
        }
        return lis;
    }

    //动态规划
    //dp[i]的含义：以i位置结尾的lis是多长
    public static int dpWays(int[] nums) {
        int[] dp = new int[nums.length];
        dp[0] = 1;
        for (int i = 1; i < nums.length; i++) {
            dp[i] = 1;
            // 每个位置求lis 需要枚举前面的所有位置
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }

        int ans = 1;
        for (int i = 0; i < dp.length; i++) {
            ans = Math.max(ans, dp[i]);
        }
        return ans;
    }


    //O(N*logN)的解法
    //ends数组的含义：ends[i]表示 找到的所有长度为i+1的递增子序列的最小结尾是啥
    public static int[] getDp2(int[] arr) {
        int N = arr.length;
        int[] dp = new int[N];
        int[] ends = new int[N];
        dp[0] = 1;
        ends[0] = arr[0];
        int right = 0; //含义：[0,right]区间为有效区
        for (int i = 1; i < N; i++) {
            int l = 0;
            int r = right;
            while (l <= r) { //在[0,r]范围上找>=arr[i]最左的位置
                int mid = l + ((r - l) >> 1);
                if (ends[mid] >= arr[i]) {
                    r = mid - 1;
                } else {
                    l = mid + 1;
                }
            }
            //如果找到了 那个该位置就是l；如果没找到l会来到 right+1 位置
            right = Math.max(right, l);
            ends[l] = arr[i];
            dp[i] = l + 1;
        }
        return dp;
    }

    public static int lsc(int[] arr) {
        int[] dp = getDp2(arr);
        int ans = dp[0];
        for (int i = 1; i < dp.length; i++) {
            ans = Math.max(ans, dp[i]);
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] nums = {1, 3, 6, 7, 9, 4, 10, 5, 6};

        System.out.println(ways1(nums));
        System.out.println(dpWays(nums));
        System.out.println(lsc(nums));
    }
}
