package data_structure.heap;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * 373. 查找和最小的K对数字
 * 给定两个以升序排列的整数数组 nums1 和 nums2 , 以及一个整数 k 。
 * 定义一对值 (u,v)，其中第一个元素来自 nums1，第二个元素来自 nums2 。
 * 请找到和最小的 k 个数对 (u1,v1),  (u2,v2)  ...  (uk,vk) 。
 * 示例 1:
 * 输入: nums1 = [1,7,11], nums2 = [2,4,6], k = 3
 * 输出: [1,2],[1,4],[1,6]
 * 解释: 返回序列中的前 3 对数：
 * [1,2],[1,4],[1,6],[7,2],[7,4],[11,2],[7,6],[11,4],[11,6]
 * 示例 2:
 * 输入: nums1 = [1,1,2], nums2 = [1,2,3], k = 2
 * 输出: [1,1],[1,1]
 * 解释: 返回序列中的前 2 对数：
 * [1,1],[1,1],[1,2],[2,1],[1,2],[2,2],[1,3],[1,3],[2,3]
 * 示例 3:
 * 输入: nums1 = [1,2], nums2 = [3], k = 3
 * 输出: [1,3],[2,3]
 * 解释: 也可能序列中所有的数对都被返回:[1,3],[2,3]
 * 提示:
 * 1 <= nums1.length, nums2.length <= 10^4
 * -10^9 <= nums1[i], nums2[i] <= 10^9
 * nums1, nums2 均为升序排列
 * 1 <= k <= 1000
 */
public class Problem_0373_FindKPairsWithSmallestSums {

    public List<int[]> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        List<int[]> res = new ArrayList();
        if (nums1.length == 0 || nums2.length == 0 || k == 0) return res;
        // Heap -- n[0] x, n[1] y
        PriorityQueue<int[]> minIndexHeap = new PriorityQueue<>((a, b) -> nums1[a[0]] + nums2[a[1]] - nums1[b[0]] - nums2[b[1]]);
        minIndexHeap.offer(new int[]{0, 0});
        int len1 = nums1.length, len2 = nums2.length;
        for (int i = 0; i < k && !minIndexHeap.isEmpty(); i++) {
            int[] min = minIndexHeap.poll();
            res.add(new int[]{nums1[min[0]], nums2[min[1]]});

            if (min[1] != len2 - 1) minIndexHeap.offer(new int[]{min[0], min[1] + 1});

            if (min[1] == 0 && min[0] != len1 - 1) minIndexHeap.offer(new int[]{min[0] + 1, 0});
        }
        return res;
    }

}
