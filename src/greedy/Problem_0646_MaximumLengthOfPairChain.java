package greedy;

import java.util.Arrays;

/**
 * 646. 最长数对链
 * 给出 n 个数对。 在每一个数对中，第一个数字总是比第二个数字小。
 * 现在，我们定义一种跟随关系，当且仅当 b < c 时，数对(c, d) 才可以跟在 (a, b) 后面。我们用这种形式来构造一个数对链。
 * 给定一个数对集合，找出能够形成的最长数对链的长度。你不需要用到所有的数对，你可以以任何顺序选择其中的一些数对来构造。
 *
 * 示例：
 * 输入：[[1,2], [2,3], [3,4]]
 * 输出：2
 * 解释：最长的数对链是 [1,2] -> [3,4]
 *
 * 提示：
 * 给出数对的个数在 [1, 1000] 范围内。
 */
public class Problem_0646_MaximumLengthOfPairChain {

    //最长递增区间子序列
    //类似与最长递增子序列O(n^2)的求法
    public static int findLongestChain(int[][] pairs) {
        if (pairs==null || pairs.length==0) return 0;
        Arrays.sort(pairs, (a,b) -> a[0]!=b[0] ? a[0]-b[0] : a[1]-b[1]);
        int n = pairs.length;
        int ans = 1;
        //每个位置求一个答案
        int[] dp = new int[n];
        dp[0] = 1;
        for (int i = 1; i < n; i++) {
            int[] cur = pairs[i];
            int curStart = cur[0];
            for (int j = 0; j < i; j++) { //看当前i位置之前所有位置
                dp[i] = Math.max(dp[i], curStart > pairs[j][1]? dp[j] + 1 : 1);
            }
            ans = Math.max(ans, dp[i]);
        }
        return ans;
    }


    //最优解
    //O(nlogn)
    public int findLongestChain2(int[][] pairs) {
        Arrays.sort(pairs, (a,b) -> a[1] - b[1]); //按区间结尾升序排序
        int count = 0, i = 0, n = pairs.length;
        while (i < n) {
            count++;
            int curEnd = pairs[i][1];
            while (i < n && pairs[i][0] <= curEnd) i++;
        }
        return count;
    }


    //最优解
    //O(nlogn)
    //与findLongestChain3其实是相同的
    public int findLongestChain3(int[][] pairs) {
        if (pairs==null || pairs.length==0) return 0;
        Arrays.sort(pairs, (a,b) -> a[1] - b[1]);
        int count=1;
        int n = pairs.length;
        int preEnd = pairs[0][1];
        for (int i = 1; i < n; i++) {
            int[] cur = pairs[i];
            if (cur[0] > preEnd) {
                count++;
                preEnd = cur[1];
            }
        }
        return count;
    }

}
