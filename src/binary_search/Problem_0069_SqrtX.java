package binary_search;

/**
 * Given a non-negative integer x, compute and return the square root of x.
 * <p>
 * Since the return type is an integer, the decimal digits are truncated, and only the integer part of the result is returned.
 * <p>
 * Example 1:
 * <p>
 * Input: x = 4
 * Output: 2
 * Example 2:
 * <p>
 * Input: x = 8
 * Output: 2
 * Explanation: The square root of 8 is 2.82842..., and since the decimal part is truncated, 2 is returned.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 0 <= x <= 231 - 1
 */
//求一个非负数x的平方根
public class Problem_0069_SqrtX {
    //104
    public int mySqrt(int x) {
        if (x < 0) {
            throw new RuntimeException("x must be a non-negative integer..");
        }
        int l = 0;
        int r = x;
        int ans = -1;
        while (l <= r) {
            int mid = (l + r) / 2;
            if (Math.pow(mid, 2) > x) {
                r = mid - 1;
            } else {
                ans = mid; //mid的平方<=x时记录一下，右边继续找
                l = mid + 1;
            }
        }
        return ans;
    }
}
