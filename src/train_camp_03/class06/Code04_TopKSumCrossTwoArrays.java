package train_camp_03.class06;

import java.util.*;

/**
 * 给定两个有序数组arr1和arr2，再给定一个正数K
 * <p>
 * 求两个数累加和最大的前K个，两个数必须分别来自arr1和arr2
 */
public class Code04_TopKSumCrossTwoArrays {

    private static class Node {
        int index1; //在数组1中的下标
        int index2; //在数组2中的下标
        int sum;//累加和

        public Node(int index1, int index2, int sum) {
            this.index1 = index1;
            this.index2 = index2;
            this.sum = sum;
        }
    }

    //前提：两个数组非空，并且都有序
    public static int[] getTopKMax(int[] arr1, int[] arr2, int K) {
        if (arr1 == null || arr2 == null || K < 1) {
            return null;
        }
        int N = arr1.length;
        int M = arr2.length;
        K = Math.min(K, N * M);
        int[] ans = new int[K];
        int ansIndex = 0;//专为ans服务
        PriorityQueue<Node> heap = new PriorityQueue<>(new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                return o2.sum - o1.sum; //按照sum排序的大根堆
            }
        });
        //建立一种不重复放的机制
        boolean[][] set = new boolean[N][M];
        heap.add(new Node(N - 1, M - 1, arr1[N - 1] + arr2[M - 1]));
        set[N - 1][M - 1] = true;
        while (ansIndex < K) {
            Node cur = heap.poll();
            ans[ansIndex++] = cur.sum;
            int index1 = cur.index1;
            int index2 = cur.index2;
            if (index1 > 0 && !set[index1 - 1][index2]) {
                heap.add(new Node(index1 - 1, index2, arr1[index1 - 1] + arr2[index2]));
                set[index1 - 1][index2] = true;
            }

            if (index2 > 0 && !set[index1][index2 - 1]) {
                heap.add(new Node(index1, index2 - 1, arr1[index1] + arr2[index2 - 1]));
                set[index1][index2 - 1] = true;
            }
        }
        return ans;
    }

    //验证的暴力方法
    public static int[] way2(int[] arr1, int[] arr2, int K) {
        if (arr1 == null || arr2 == null || K < 1) {
            return null;
        }
        int N = arr1.length;
        int M = arr2.length;
        K = Math.min(K, N * M);
        int[] all = new int[N * M];
        int index = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                all[index++] = arr1[i] + arr2[j]; //计算两两累加和
            }
        }
        Arrays.sort(all);
        int[] ans = new int[K];
        int allIndex = all.length - 1;
        for (int i = 0; i < K; i++) {
            ans[i] = all[allIndex--];
        }
        return ans;
    }

    //验证两个数组是否相等
    private static boolean isEqual(int[] arr1, int[] arr2) {
        if (arr1 == null && arr2 == null) {
            return true;
        }
        if (arr1 == null || arr2 == null || arr1.length != arr2.length) {
            return false;
        }
        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i] != arr2[i]) {
                return false;
            }
        }
        return true;
    }

    //随机产生数组
    private static int[] generateArr(int len, int maxVal) {
        int[] res = new int[len];
        for (int i = 0; i < len; i++) {
            res[i] = (int) (Math.random() * (maxVal)) - (int) (Math.random() * (maxVal));
        }
        Arrays.sort(res);
        return res;
    }

    private static void printArr(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            System.out.print(nums[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int testTimes = 100000;
        int len = 100;
        int maxVal = 50;
        int maxK = 100;
        System.out.println("test start");
        for (int i = 0; i < testTimes; i++) {
            int l = (int) (Math.random() * (len + 1));
            l = Math.max(l, 10);
            int[] nums1 = generateArr(l, maxVal);
            int[] nums2 = generateArr(l, maxVal);
            int K = (int) (Math.random() * maxK);

            int[] ans1 = getTopKMax(nums1, nums2, K);
            int[] ans2 = way2(nums1, nums2, K);
            if (!isEqual(ans1, ans2)) {
                printArr(nums1);
                printArr(nums2);
                System.out.println("fuck");
                break;
            }
        }
        System.out.println("test end");
    }
}
