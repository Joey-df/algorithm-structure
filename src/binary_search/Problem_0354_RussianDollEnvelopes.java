package binary_search;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 354. 俄罗斯套娃信封问题
 * 给你一个二维整数数组 envelopes ，其中 envelopes[i] = [wi, hi] ，表示第 i 个信封的宽度和高度。
 * 当另一个信封的宽度和高度都比这个信封大的时候，这个信封就可以放进另一个信封里，如同俄罗斯套娃一样。
 * 请计算 最多能有多少个 信封能组成一组“俄罗斯套娃”信封（即可以把一个信封放到另一个信封里面）。
 *
 * 注意：不允许旋转信封。
 *
 * 示例 1：
 * 输入：envelopes = [[5,4],[6,4],[6,7],[2,3]]
 * 输出：3
 * 解释：最多信封的个数为 3, 组合为: [2,3] => [5,4] => [6,7]。
 * 示例 2：
 * 输入：envelopes = [[1,1],[1,1],[1,1]]
 * 输出：1
 */
public class Problem_0354_RussianDollEnvelopes {
    
    public static class Node {
        int length;
        int width;
    
        public Node(int l, int w) {
            length = l;
            width = w;
        }
    }

    //二位数组
    //每个元素表示长宽
    //Input: envelopes = [[5,4],[6,4],[6,7],[2,3]]
    //Output: 3
    public static int maxEnvelopes(int[][] envelopes) {
        if (envelopes == null || envelopes.length == 0 || envelopes[0].length == 0) {
            return 0;
        }
        int N = envelopes.length;
        Node[] arr = new Node[N];
        for (int i = 0; i < N; i++) {
            arr[i] = new Node(envelopes[i][0], envelopes[i][1]);
        }

        Arrays.sort(arr, (Node o1, Node o2) ->
                    //长度从小到大，长度相等的话，宽度从大到小
                    o1.length != o2.length ?
                    o1.length - o2.length :
                    o2.width - o1.width);
        int[] dp = new int[N];
        int[] ends = new int[N];
        dp[0] = 1;
        int ans = dp[0];
        ends[0] = arr[0].width;
        int right = 0;
        for (int i = 1; i < arr.length; i++) {
            int l = 0;
            int r = right;
            while (l <= r) {
                int m = (l + r) >> 1;
                if (ends[m] >= arr[i].width) {
                    r = m - 1;
                } else {
                    l = m + 1;
                }
            }
            right = Math.max(right, l);
            ends[l] = arr[i].width;
            dp[i] = l + 1;
            ans = Math.max(ans, dp[i]);
        }
        return ans;
    }
}
