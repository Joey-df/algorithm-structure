package data_structure.heap;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Given a n x n matrix where each of the rows and columns are sorted in ascending order, find the kth smallest element in the matrix.
 * <p>
 * Note that it is the kth smallest element in the sorted order, not the kth distinct element.
 * <p>
 * Example:
 * <p>
 * matrix = [
 * [ 1,  5,  9],
 * [10, 11, 13],
 * [12, 13, 15]
 * ],
 * k = 8,
 * <p>
 * return 13.
 * Note:
 * You may assume k is always valid, 1 ≤ k ≤ n2.
 */
public class Problem_0378_KthSmallestElementInASortedMatrix {

    private static class WrapNode {
        int row;
        int col;
        int val;

        public WrapNode(int row, int col, int val) {
            this.row = row;
            this.col = col;
            this.val = val;
        }
    }

    public int kthSmallest(int[][] matrix, int k) {
        //建立小根堆
        PriorityQueue<WrapNode> heap = new PriorityQueue<>(new Comparator<WrapNode>() {
            @Override
            public int compare(WrapNode o1, WrapNode o2) {
                return o1.val - o2.val;
            }
        });
        heap.offer(new WrapNode(0, 0, matrix[0][0]));//先讲左上角元素加入堆

        //添加不重复放的机制
        boolean[][] set = new boolean[matrix.length][matrix[0].length];
        set[0][0] = true;
        int count = 0;
        int ans = Integer.MAX_VALUE;
        while (!heap.isEmpty()) {
            WrapNode topNode = heap.poll();
            int val = topNode.val;
            int row = topNode.row;
            int col = topNode.col;
            if (count == k - 1) {
                ans = val;
                break;
            }
            count++;
            if (row + 1 < matrix.length && !set[row + 1][col]) {
                heap.offer(new WrapNode(row + 1, col, matrix[row + 1][col]));
                set[row + 1][col] = true;
            }
            if (col + 1 < matrix[0].length && !set[row][col + 1]) {
                heap.offer(new WrapNode(row, col + 1, matrix[row][col + 1]));
                set[row][col + 1] = true;
            }
        }
        return ans;
    }
}
