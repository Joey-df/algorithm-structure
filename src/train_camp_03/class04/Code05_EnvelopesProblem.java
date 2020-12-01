package train_camp_03.class04;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 每个信封都有长和宽两个维度的数据，A信封如果想套在B信封里面，A信封必须在长和宽上都小于B信封才行。
 * 如果给你一批信封，返回最大的嵌套层数
 * <p>
 * What is the maximum number of envelopes can you Russian doll? (put one inside other)
 * <p>
 * Note:
 * Rotation is not allowed.
 * <p>
 * Example:
 * <p>
 * Input: [[5,4],[6,4],[6,7],[2,3]]
 * Output: 3
 * Explanation: The maximum number of envelopes you can Russian doll is 3 ([2,3] => [5,4] => [6,7]).
 * <p>
 * https://leetcode.com/problems/russian-doll-envelopes/
 */
public class Code05_EnvelopesProblem {

    private static class Node {
        int length;
        int width;

        public Node(int length, int width) {
            this.length = length;
            this.width = width;
        }
    }

    // Rotation is not allowed.
    public static int maxEnvelopes(int[][] nums) {
        if (nums == null || nums.length == 0 || nums[0].length != 2) {
            return 0;
        }
        //to Node[]
        int N = nums.length;
        Node[] nodes = new Node[N];
        for (int i = 0; i < nums.length; i++) {
            nodes[i] = new Node(nums[i][0], nums[i][1]);
        }
        Arrays.sort(nodes, new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {//长升序，长相等时，宽降序
                return (o1.length != o2.length) ? (o1.length - o2.length) : (o2.width - o1.width);
            }
        });
        //提出width
        int[] widths = new int[N];
        for (int i = 0; i < nodes.length; i++) {
            widths[i] = nodes[i].width;
        }
        int[] ends = new int[N];
        ends[0] = widths[0];
        int ans = 1;
        int right = 0;
        for (int i = 1; i < widths.length; i++) {
            int l = 0;
            int r = right;
            while (l <= r) {
                int m = l + ((r - l) >> 2);
                if (ends[m] >= widths[i]) {
                    r = m - 1;
                } else {
                    l = m + 1;
                }
            }
            right = Math.max(right, l);
            ends[l] = widths[i];
            ans = Math.max(ans, l + 1);
        }
        return ans;
    }
}
