package sort;

import java.util.PriorityQueue;

/**
 * 已知一个几乎有序的数组。
 * 几乎有序是指，如果把数组排好顺序的话，每个元素移动的距离一定不超过k，并且k相对于数组长度来说是比较小的。
 * <p>
 * 请选择一个合适的排序策略，对这个数组进行排序。
 */
public class SortArrayDistanceLessK {

    public static void sort(int[] arr, int K) {
        if (K < 0) return;
        PriorityQueue<Integer> heap = new PriorityQueue<>();//小根堆
        int index = 0;
        for (; index <= Math.min(arr.length - 1, K - 1); index++) {
            heap.offer(arr[index]);
        }
        int i = 0;
        for (; index < arr.length; i++, index++) {
            heap.add(arr[index]);
            arr[i] = heap.poll();
        }
        while (!heap.isEmpty()) {
            arr[i++] = heap.poll();
        }
    }
}
