package hash_map;

//532. 数组中的 k-diff 数对
//https://leetcode-cn.com/problems/k-diff-pairs-in-an-array/

import java.util.TreeMap;

/**
 * 532. 数组中的 k-diff 数对
 * 给定一个整数数组和一个整数 k，你需要在数组里找到 不同的 k-diff 数对，并返回不同的 k-diff 数对 的数目。
 * 这里将 k-diff 数对定义为一个整数对 (nums[i], nums[j])，并满足下述全部条件：
 * <p>
 * 0 <= i < j < nums.length
 * |nums[i] - nums[j]| == k
 * 注意，|val| 表示 val 的绝对值。
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [3, 1, 4, 1, 5], k = 2
 * 输出：2
 * 解释：数组中有两个 2-diff 数对, (1, 3) 和 (3, 5)。
 * 尽管数组中有两个1，但我们只应返回不同的数对的数量。
 * 示例 2：
 * <p>
 * 输入：nums = [1, 2, 3, 4, 5], k = 1
 * 输出：4
 * 解释：数组中有四个 1-diff 数对, (1, 2), (2, 3), (3, 4) 和 (4, 5)。
 * 示例 3：
 * <p>
 * 输入：nums = [1, 3, 1, 5, 4], k = 0
 * 输出：1
 * 解释：数组中只有一个 0-diff 数对，(1, 1)。
 * 示例 4：
 * <p>
 * 输入：nums = [1,2,4,4,3,3,0,9,2,3], k = 3
 * 输出：2
 * 示例 5：
 * <p>
 * 输入：nums = [-1,-2,-3], k = 1
 * 输出：2
 * <p>
 * 提示：
 * 1 <= nums.length <= 10^4
 * -10^7 <= nums[i] <= 10^7
 * 0 <= k <= 10^7
 */
public class Problem_0532_KdiffPairsInAnArray {

    public static int findPairs(int[] nums, int k) {
        //<nums中每一个数，出现的次数>
        TreeMap<Integer, Integer> map = new TreeMap<>();
        for (int n : nums) {
            map.put(n, map.getOrDefault(n, 0) + 1);
        }
        int ans = 0;
        for (int key : map.keySet()) {
            if (k == 0) {
                if (map.get(key) >= 2) { //自己和自己形成一对
                    ans++;
                }
            } else {
                if (map.containsKey(key + k)) ans++;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{3, 1, 4, 1, 5};
        int k = 2;
        System.out.println(findPairs(arr, k));
    }

}
