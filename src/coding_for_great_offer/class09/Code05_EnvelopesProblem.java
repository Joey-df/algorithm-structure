package coding_for_great_offer.class09;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 俄罗斯信封套娃问题
 * <p>
 * Leetcode 原题：
 * <p>
 * https://leetcode.com/problems/russian-doll-envelopes/
 */
public class Code05_EnvelopesProblem {

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

        Arrays.sort(arr, new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                //长度从小到大，长度相等的话，宽度从大到小
                return o1.length != o2.length ?
                        o1.length - o2.length :
                        o2.width - o1.width;
            }
        });
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
