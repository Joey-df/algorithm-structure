package two_pointers;

/**
 * Given a non-negative integer c, decide whether there're two integers a and b such that a^2 + b^2 = c.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: c = 5
 * Output: true
 * Explanation: 1 * 1 + 2 * 2 = 5
 * Example 2:
 * <p>
 * Input: c = 3
 * Output: false
 * Example 3:
 * <p>
 * Input: c = 4
 * Output: true
 * Example 4:
 * <p>
 * Input: c = 2
 * Output: true
 * Example 5:
 * <p>
 * Input: c = 1
 * Output: true
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 0 <= c <= 2^31 - 1
 */
public class Problem_0633_SumOfSquareNumbers {

    public static boolean judgeSquareSum(int c) {
        int l = 0;
        int r = (int) Math.sqrt(c);
        while (l <= r) {
            int sum = (int) (Math.pow(l, 2) + Math.pow(r, 2));
            if (sum == c) {
                return true;
            } else if (sum < c) {
                l++;
            } else {
                r--;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(judgeSquareSum(4));
    }

}
