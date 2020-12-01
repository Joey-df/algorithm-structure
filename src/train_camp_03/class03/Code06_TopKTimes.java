package train_camp_03.class03;

import java.util.*;

/**
 * 给定一个由字符串组成的数组String[] strs，给定一个正数K
 * <p>
 * 返回词频最大的前K个字符串，假设结果是唯一的
 */
public class Code06_TopKTimes {

    private static class Node {
        int value;
        int freq;

        public Node(int value, int freq) {
            this.value = value;
            this.freq = freq;
        }
    }

    //为了测试方便，将String[]实现为int[]
    //前提：nums非空
    public static int[] getTopKUseHeap(int[] nums, int k) {
        Map<Integer, Node> map = new HashMap<>();
        // 1、统计词频
        for (int i = 0; i < nums.length; i++) {
            if (!map.containsKey(nums[i])) {
                map.put(nums[i], new Node(nums[i], 1));
            } else {
                map.get(nums[i]).freq++;
            }
        }
        //2、使用固定大小为k小根堆
        PriorityQueue<Node> heap = new PriorityQueue<>(new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                return o1.freq - o2.freq;
            }
        });
        Set<Map.Entry<Integer, Node>> entries = map.entrySet();
        Iterator<Map.Entry<Integer, Node>> iterator = entries.iterator();
        while (iterator.hasNext()) {
            Map.Entry<Integer, Node> entry = iterator.next();
            if (heap.size() < k) {
                heap.add(entry.getValue());
            } else {
                if (entry.getValue().freq > heap.peek().freq) {
                    heap.poll();
                    heap.add(entry.getValue());
                }
            }
        }
        int[] ans = new int[k];
        int i = 0;
        while (!heap.isEmpty()) {
            ans[i] = heap.poll().value;
            System.out.println(ans[i++]);
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 3, 4, 5, 6, 7, 1, 2, 3, 4, 5, 6, 1, 2, 3, 5, 5};
        int k = 4;
        getTopKUseHeap(nums, k);
    }
}
