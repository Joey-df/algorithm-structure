package data_structure.heap;

import java.util.PriorityQueue;

public class KthLargest {

    PriorityQueue<Integer> heap;
    int k;
    //初始化一个小大为k的小根堆，堆中放最大的三个数，堆顶即为第k大的数
    public KthLargest(int k, int[] nums) {
        this.k = k;
        heap = new PriorityQueue<>();
        for (int i = 0; i < Math.min(k,nums.length); i++) {
            if (nums.length >0)
                heap.add(nums[i]);
        }
        for (int i = k; i < nums.length; i++) {
            if (heap.peek() < nums[i]) {
                heap.poll();
                heap.add(nums[i]);
            }
        }
    }

    public int add(int val) {
        if (heap.isEmpty() || heap.size()<this.k){
            heap.add(val);
        } else if (val > heap.peek()) {
            heap.poll();
            heap.add(val);
        }
        return heap.peek();
    }

    public static void main(String[] args) {
        KthLargest kl = new KthLargest(2, new int[]{0});
        System.out.println(kl.add(3));
        System.out.println(kl.add(8));
    }
}
