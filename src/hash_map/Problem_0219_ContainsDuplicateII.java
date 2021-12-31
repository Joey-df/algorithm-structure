package hash_map;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 219. 存在重复元素II
 * 给定一个整数数组和一个整数 k，判断数组中是否存在两个不同的索引 i 和 j，
 * 使得 nums [i] = nums [j]，并且 i 和 j 的差的 绝对值 至多为 k。
 * <p>
 * 示例 1:
 * <p>
 * 输入: nums = [1,2,3,1], k = 3
 * 输出: true
 * 示例 2:
 * <p>
 * 输入: nums = [1,0,1,1], k = 1
 * 输出: true
 * 示例 3:
 * <p>
 * 输入: nums = [1,2,3,1,2,3], k = 2
 * 输出: false
 */
public class Problem_0219_ContainsDuplicateII {

    public static boolean containsNearbyDuplicate(int[] nums, int k) {
        //<数组中每个元素nums[i], nums[i]出现的下标组成的集合>
        HashMap<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (!map.containsKey(nums[i])) {
                map.put(nums[i], new ArrayList<>());
            }
            map.get(nums[i]).add(i);
        }
        int min = Integer.MAX_VALUE;
        for (int key : map.keySet()) {
            if (map.get(key).size() > 1) {
                List<Integer> list = map.get(key);
                for (int i = 1; i < list.size(); i++) {
                    min = Math.min(min, list.get(i) - list.get(i - 1));
                }
            }
        }
        return min <= k;
    }

    public static boolean containsNearbyDuplicate2(int[] nums, int k) {
        //<nums[i], nums[i]上次出现的位置>
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i])) {
                int lastIndex = map.get(nums[i]);
                if (i - lastIndex <= k) return true;
            }
            map.put(nums[i], i);
        }
        return false;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{1, 2, 3, 1, 2, 3};
        int k = 2;
        System.out.println(containsNearbyDuplicate(arr, k));
    }
}
