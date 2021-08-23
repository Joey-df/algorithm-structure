package data_structure.heap;

import java.util.*;

/**
 * Given a non-empty array of integers, return the k most frequent elements.
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [1,1,1,2,2,3], k = 2
 * Output: [1,2]
 * Example 2:
 * <p>
 * Input: nums = [1], k = 1
 * Output: [1]
 * Note:
 * <p>
 * You may assume k is always valid, 1 ≤ k ≤ number of unique elements.
 * Your algorithm's time complexity must be better than O(n log n), where n is the array's size.
 * It's guaranteed that the answer is unique, in other words the set of the top k frequent elements is unique.
 * You can return the answer in any order.
 * <p>
 */
//门槛堆
//需要反复练习
public class Problem_0347_TopKFrequentElements {
    private static class Node {
        int value;
        int freq;

        public Node(int value, int freq) {
            this.value = value;
            this.freq = freq;
        }
    }

    //1、先遍历一遍统计词频
    //2、建立小根堆(门槛堆)固定其大小为k
    //3、利用固定大小为k的小根堆找出词频最大的前K个
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Node> map = new HashMap<>();
        for (int n : nums) {
            if (!map.containsKey(n)) {
                map.put(n, new Node(n, 1));
            } else {
                map.get(n).freq++;
            }
        }
        PriorityQueue<Node> heap = new PriorityQueue<>(new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                return o1.freq - o2.freq; //按照出现的频率升序
            }
        });

        for (Node cur : map.values()) { //堆中始终维持k个元素
            if (heap.size() < k || (heap.size() == k && (cur.freq > heap.peek().freq))) {
                heap.offer(cur);
            }
            if (heap.size() > k) {
                heap.poll();
            }
        }

        int[] ans = new int[k];
        int index = 0;
        while (!heap.isEmpty()) {
            ans[index++] = heap.poll().value;
        }
        return ans;
    }
}
