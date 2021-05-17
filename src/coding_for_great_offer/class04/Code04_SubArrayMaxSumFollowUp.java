package coding_for_great_offer.class04;

/**
 * 返回一个数组中，选择的数字不能相邻的情况下，
 * 最大子序列累加和
 * <p>
 * 打家劫舍问题
 * 从左往右的尝试模型
 */
//本题测试链接：https://leetcode-cn.com/problems/house-robber/
public class Code04_SubArrayMaxSumFollowUp {

    //思路：求每一个结尾位置的答案，求整体max
    public static int process(int[] arr) {
        if (arr == null || arr.length == 0) return 0;
        if (arr.length == 1) return arr[0];
        if (arr.length == 2) return Math.max(arr[0], arr[1]);
        //arr.length >= 3
        int N = arr.length;
        int[] dp = new int[N];
        dp[0] = arr[0];
        dp[1] = Math.max(arr[0], arr[1]);
        for (int i = 2; i < N; i++) {
            int p1 = arr[i]; //只要arr[i]
            int p2 = dp[i - 1];//不要arr[i]
            int p3 = arr[i] + dp[i - 2];//要arr[i]
            dp[i] = Math.max(p1, Math.max(p2, p3));
        }
        return dp[N - 1];
    }

}
