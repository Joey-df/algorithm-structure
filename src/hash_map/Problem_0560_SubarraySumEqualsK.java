package hash_map;

import java.util.HashMap;
import java.util.Map;

/**
 * Given an array of integers nums and an integer k,
 * return the total number of continuous subarrays whose sum equals to k.
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [1,1,1], k = 2
 * Output: 2
 * Example 2:
 * <p>
 * Input: nums = [1,2,3], k = 3
 * Output: 2
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= nums.length <= 2 * 104
 * -1000 <= nums[i] <= 1000
 * -107 <= k <= 107
 * <p>
 * 给定数组arr，其值可正可负可0，给定正数K，求累加和为K的子数组个数
 * 算法原型：数组累加和问题三连的第二连
 */
public class Problem_0560_SubarraySumEqualsK {

    public static int findSumEqualsK(int[] arr, int K) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        Map<Integer, Integer> map = new HashMap<>(); //<累加和,出现的次数>
        int ans = 0;
        int sum = 0;
        map.put(0, 1);//一开始，就存在一个累加和为0的
        for (int i = 0; i < arr.length; i++) { // 求以每个i结尾的子数组的答案
            sum += arr[i];
            if (map.containsKey(sum - K)) { //sum=100  K=20
                ans += map.get(sum - K); //以i位置结尾的答案
            }
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(findSumEqualsK(new int[]{1, 1, 1}, 2));
    }
}
