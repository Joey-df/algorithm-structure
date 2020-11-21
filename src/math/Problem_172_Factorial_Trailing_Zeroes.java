package math;

/**
 * Given an integer n, return the number of trailing zeroes in n!.
 * <p>
 * Follow up: Could you write a solution that works in logarithmic time complexity?
 * <p>
 * Example 1:
 * <p>
 * Input: n = 3
 * Output: 0
 * Explanation: 3! = 6, no trailing zero.
 * Example 2:
 * <p>
 * Input: n = 5
 * Output: 1
 * Explanation: 5! = 120, one trailing zero.
 * Example 3:
 * <p>
 * Input: n = 0
 * Output: 0
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= n <= 104
 */
public class Problem_172_Factorial_Trailing_Zeroes {
    // This question is pretty straightforward.
    // Because all trailing 0 is from factors 5 * 2.
    // But sometimes one number may have several 5 factors,
    // for example, 25 have two 5 factors, 125 have three 5 factors.
    // In the n! operation, factors 2 is always ample.
    // So we just count how many 5 factors in all number from 1 to n.
    public int trailingZeroes(int n) {
        int ans = 0;
        while (n != 0) {
            n /= 5;
            ans += n;
        }
        return ans;
    }
}
