package hash_map;

import java.util.*;

/**
 * 349. 两个数组的交集
 * 给定两个数组，编写一个函数来计算它们的交集。
 * <p>
 * 示例 1：
 * 输入：nums1 = [1,2,2,1], nums2 = [2,2]
 * 输出：[2]
 * 示例 2：
 * 输入：nums1 = [4,9,5], nums2 = [9,4,9,8,4]
 * 输出：[9,4]
 * <p>
 * 说明：
 * 输出结果中的每个元素一定是唯一的。
 * 我们可以不考虑输出结果的顺序。
 */
public class Problem_0349_IntersectionOfTwoArrays {

    public int[] intersection(int[] arr1, int[] arr2) {
        Map<Integer, Integer> map1 = new HashMap<>();
        Map<Integer, Integer> map2 = new HashMap<>();
        for (int num : arr1) {
            map1.put(num, map1.getOrDefault(num, 0) + 1);
        }
        for (int n : arr2) {
            map2.put(n, map2.getOrDefault(n, 0) + 1);
        }
        LinkedList<Integer> q = new LinkedList<>();
        for (int n : map1.keySet()) {
            if (map2.containsKey(n)) {
                q.offerLast(n);
            }
        }
        int[] ans = new int[q.size()];
        int i = 0;
        while (!q.isEmpty()) {
            ans[i++] = q.pollLast();
        }
        return ans;
    }

}
