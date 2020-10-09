package math;

/**
 * Given a non-negative integer c, decide whether there're two integers a and b such that a2 + b2 = c.
 *
 *
 *
 * Example 1:
 *
 * Input: c = 5
 * Output: true
 * Explanation: 1 * 1 + 2 * 2 = 5
 * Example 2:
 *
 * Input: c = 3
 * Output: false
 * Example 3:
 *
 * Input: c = 4
 * Output: true
 * Example 4:
 *
 * Input: c = 2
 * Output: true
 * Example 5:
 *
 * Input: c = 1
 * Output: true
 *
 *
 * Constraints:
 *
 * 0 <= c <= 231 - 1
 */
public class Problem_633_Sum_of_Square_Numbers {

    public static boolean judgeSquareSum(int c) {
        return process(c);
    }

    private static boolean process(int n) {
        int left = 0;
        int right = (int) Math.sqrt(n);
        while (left <= right) {
            int a = (int) Math.pow(left, 2), b = (int) Math.pow(right, 2);
            if (a + b == n) return true;
            if (a + b < n) {
                left++;
            } else {
                right--;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(judgeSquareSum(1));
    }
}
