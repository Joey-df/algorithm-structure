package leetcode_top_interview_and_top100liked_questions;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.*;

/**
 * 454. 四数相加 II
 * 给定四个包含整数的数组列表 A , B , C , D ,
 * 计算有多少个元组 (i, j, k, l) ，使得 A[i] + B[j] + C[k] + D[l] = 0。
 * <p>
 * 为了使问题简单化，所有的 A, B, C, D 具有相同的长度 N，且 0 ≤ N ≤ 500 。
 * 所有整数的范围在 -2^28 到 2^28 - 1 之间，最终结果不会超过 2^31 - 1 。
 * <p>
 * 例如:
 * <p>
 * 输入:
 * A = [ 1, 2]
 * B = [-2,-1]
 * C = [-1, 2]
 * D = [ 0, 2]
 * <p>
 * 输出:
 * 2
 * <p>
 * 解释:
 * 两个元组如下:
 * 1. (0, 0, 0, 1) -> A[0] + B[0] + C[0] + D[1] = 1 + (-2) + (-1) + 2 = 0
 * 2. (1, 1, 0, 0) -> A[1] + B[1] + C[0] + D[0] = 2 + (-1) + (-1) + 0 = 0
 */
public class Problem_0454_4SumII {

    //四数相加等于target的元组个数，返回
    public static int func(int[] A, int[] B, int[] C, int[] D, int target) {
        // key: A、B中任意两个数的sum  value: 次数
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < B.length; j++) {
                int sum = A[i] + B[j];
                map.put(sum, map.getOrDefault(sum, 0) + 1);
            }
        }

        int ans = 0;
        for (int i = 0; i < C.length; i++) {
            for (int j = 0; j < D.length; j++) {
                int sum = C[i] + D[j];
                if (map.containsKey(target - sum)) {
                    ans += map.get(target - sum);
                }
            }
        }
        return ans;
    }

    public static int fourSumCount(int[] A, int[] B, int[] C, int[] D) {
        return func(A, B, C, D, 0);
    }

}
