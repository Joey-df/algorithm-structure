package hash_map;

import java.util.HashMap;
import java.util.Map;

/**
 * Given a list of non-negative numbers and a target integer k,
 * write a function to check if the array has a continuous subarray of size at least 2 that sums up to a multiple of k,
 * that is, sums up to n*k where n is also an integer.
 * <p>
 * Example 1:
 * <p>
 * Input: [23, 2, 4, 6, 7],  k=6
 * Output: True
 * Explanation: Because [2, 4] is a continuous subarray of size 2 and sums up to 6.
 * Example 2:
 * <p>
 * Input: [23, 2, 6, 4, 7],  k=6
 * Output: True
 * Explanation: Because [23, 2, 6, 4, 7] is an continuous subarray of size 5 and sums up to 42.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * The length of the array won't exceed 10,000.
 * You may assume the sum of all the numbers is in the range of a signed 32-bit integer.
 * <p>
 * 算法原型：数组累加和问题三连的第二连
 * 题目要求，所求的子数组长度至少为2；子数组累加和为k的倍数
 */
public class Problem_0523_ContinuousSubarraySum {

    public static boolean process(int[] arr, int K) {
        if (arr == null || arr.length < 2) {
            return false;
        }
        Map<Integer, Integer> map = new HashMap<>(); //<累加和，出现的位置>
        map.put(0, -1); //一开始0这个累加和出现在-1位置
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
            if (K != 0) {
                sum = sum % K;
            }
            if (map.containsKey(sum)) {
                if (i - map.get(sum) >= 2) {
                    return true;
                }
            } else {
                map.put(sum, i);
            }
        }
        return false;
    }
}
