package coding_for_great_offer.class18;

// 牛客的测试链接：
// https://www.nowcoder.com/practice/7201cacf73e7495aa5f88b223bbbf6d1

import java.util.HashSet;
import java.util.PriorityQueue;

/**
 * 两个有序数组间相加和的Topk问题
 * 描述
 * 给定两个有序数组arr1和arr2，再给定一个整数k，返回来自arr1和arr2的两个数相加和最大的前k个，两个数必须分别来自两个数组
 * 按照降序输出
 * [要求]
 * 时间复杂度为O(klogk)
 * 输入描述：
 * 第一行2个整数N, K分别表示数组arr1, arr2的大小，以及需要询问的数
 * 接下来一行N个整数，表示arr1内的元素
 * 再接下来一行N个整数，表示arr2内的元素
 * 输出描述：
 * 输出K个整数表示答案
 * 示例1
 * 输入：
 * 5 4
 * 1 2 3 4 5
 * 3 5 7 9 11
 * 复制
 * 输出：
 * 16 15 14 14
 */
public class Code04_TopKSumCrossTwoArrays {

    private static class Node {
        int index1;
        int index2;
        int sum;

        public Node(int i, int j, int s) {
            index1 = i;
            index2 = j;
            sum = s;
        }
    }

    //arr1 arr2分别有序，求前k大累加和
    public static int[] topK(int[] arr1, int[] arr2, int k) {
        if (arr1 == null || arr2 == null || k < 1) {
            return null;
        }
        PriorityQueue<Node> heap = new PriorityQueue<>((o1, o2) -> o2.sum - o1.sum);
        int N = arr1.length;
        int M = arr2.length;
        k = Math.min(k, N * M);
        //boolean[][] set = new boolean[N][M];
        HashSet<Long> set = new HashSet<>();
        heap.offer(new Node(N - 1, M - 1, arr1[N - 1] + arr2[M - 1]));
        //set[N - 1][M - 1] = true;
        set.add(help(N-1, M-1, M));
        int[] ans = new int[k];
        int idx = 0;
        while (!heap.isEmpty()) {
            Node cur = heap.poll();
            int index1 = cur.index1;
            int index2 = cur.index2;
            int val = cur.sum;
            ans[idx++] = val;
            if (idx == k) {
                break;
            }
            if (index1 - 1 >= 0 && !set.contains(help(index1-1, index2, M))) {
                heap.offer(new Node(index1 - 1, index2, arr1[index1 - 1] + arr2[index2]));
                //set[index1 - 1][index2] = true;
                set.add(help(index1-1, index2, M));
            }
            if (index2 - 1 >= 0 && !set.contains(help(index1, index2-1, M))) {
                heap.offer(new Node(index1, index2 - 1, arr1[index1] + arr2[index2 - 1]));
                //set[index1][index2 - 1] = true;
                set.add(help(index1, index2-1, M));
            }
        }
        return ans;
    }

    public static long help(int i1, int i2, int M) {
        return (long) i1 * (long) M + (long) i2;
    }

    public static void print(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[] arr1 = new int[]{1, 2, 3, 4, 5};
        int[] arr2 = new int[]{3, 5, 7, 9, 11};
        int k = 700;
        int[] ans = topK(arr1, arr2, k);
        print(ans);
    }
}
