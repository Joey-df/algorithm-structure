package bit_manipulation;

import java.util.ArrayList;
import java.util.List;

/**
 * 89. Gray Code
 * An n-bit gray code sequence is a sequence of 2n integers where:
 *
 * Every integer is in the inclusive range [0, 2n - 1],
 * The first integer is 0,
 * An integer appears no more than once in the sequence,
 * The binary representation of every pair of adjacent integers differs by exactly one bit, and
 * The binary representation of the first and last integers differs by exactly one bit.
 * Given an integer n, return any valid n-bit gray code sequence.
 *
 * Example 1:
 * Input: n = 2
 * Output: [0,1,3,2]
 * Explanation:
 * The binary representation of [0,1,3,2] is [00,01,11,10].
 * - 00 and 01 differ by one bit
 * - 01 and 11 differ by one bit
 * - 11 and 10 differ by one bit
 * - 10 and 00 differ by one bit
 * [0,2,3,1] is also a valid gray code sequence, whose binary representation is [00,10,11,01].
 * - 00 and 10 differ by one bit
 * - 10 and 11 differ by one bit
 * - 11 and 01 differ by one bit
 * - 01 and 00 differ by one bit
 *
 * Example 2:
 * Input: n = 1
 * Output: [0,1]
 *
 * Constraints:
 * 1 <= n <= 16
 */
public class Problem_0089_GrayCode {

    public static List<Integer> grayCode1(int n) {
        List<Integer> ans = new ArrayList<>();
        ans.add(0);
        for (int i = 0; i < n; i++) {
            int size = ans.size();
            for (int j = size-1; j >= 0; j--) {
                int t = ans.get(j) | (1 << i);
                ans.add(t);
            }
        }
        return ans;
    }

    public static List<Integer> grayCode2(int n) {
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < (1<<n); i++) { //0~2^n-1
            ans.add(i ^ (i>>1));
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(grayCode1(2));
        System.out.println(grayCode2(2));
    }
}
