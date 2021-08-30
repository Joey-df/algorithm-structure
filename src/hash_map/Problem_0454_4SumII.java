package hash_map;

import java.util.HashMap;
import java.util.Map;

/**
 * Given four lists A, B, C, D of integer values, compute how many tuples (i, j, k, l) there are such that A[i] + B[j] + C[k] + D[l] is zero.
 * <p>
 * To make problem a bit easier, all A, B, C, D have same length of N where 0 ≤ N ≤ 500. All integers are in the range of -228 to 228 - 1 and the result is guaranteed to be at most 231 - 1.
 * <p>
 * Example:
 * <p>
 * Input:
 * A = [ 1, 2]
 * B = [-2,-1]
 * C = [-1, 2]
 * D = [ 0, 2]
 * <p>
 * Output:
 * 2
 * <p>
 * Explanation:
 * The two tuples are:
 * 1. (0, 0, 0, 1) -> A[0] + B[0] + C[0] + D[1] = 1 + (-2) + (-1) + 2 = 0
 * 2. (1, 1, 0, 0) -> A[1] + B[1] + C[0] + D[0] = 2 + (-1) + (-1) + 0 = 0
 */
// 思路：
// 1、创建一个hahmap<AB两两元素之和, 数量>
// 2、遍历A、B填充hashmap
// 3、遍历C、D计算两两之和，利用hashMap计算结果
public class Problem_0454_4SumII {

    public int fourSumCount(int[] A, int[] B, int[] C, int[] D) {
        HashMap<Integer, Integer> map1 = new HashMap<>();
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < C.length; j++) {
                int sum = A[i] + C[j];
                map1.put(sum, map1.getOrDefault(sum, 0) + 1);
            }
        }
        int ans = 0;
        for (int i = 0; i < B.length; i++) {
            for (int j = 0; j < D.length; j++) {
                int sum = B[i] + D[j];
                if (map1.containsKey(~sum + 1)) {
                    ans += map1.get(-sum);
                }
            }
        }
        return ans;
    }

}
