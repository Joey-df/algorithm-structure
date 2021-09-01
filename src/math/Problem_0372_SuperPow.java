package math;

/**
 * 372. Super Pow
 * Your task is to calculate a^b mod 1337 where a is a positive integer
 * and b is an extremely large positive integer given in the form of an array.
 * <p>
 * Example 1:
 * Input: a = 2, b = [3]
 * Output: 8
 * <p>
 * Example 2:
 * Input: a = 2, b = [1,0]
 * Output: 1024
 * <p>
 * Example 3:
 * Input: a = 1, b = [4,3,3,8,5,2]
 * Output: 1
 * <p>
 * Example 4:
 * Input: a = 2147483647, b = [2,0,0]
 * Output: 1198
 * <p>
 * Constraints:
 * 1 <= a <= 231 - 1
 * 1 <= b.length <= 2000
 * 0 <= b[i] <= 9
 * b doesn't contain leading zeros.
 */
public class Problem_0372_SuperPow {

    //来自
    //https://leetcode.com/problems/super-pow/discuss/84485/8ms-JAVA-solution-using-fast-power
    private static final int M = 1337;

    //求x的n次方，结果对1337取模
    public int normalPow(int x, int n) {
        int result = 1;
        int t = x;
        for (; n != 0; n >>= 1) {
            if ((n & 1) != 0) {
                result = (result * t) % M;
            }
            t = (t * t) % M;
        }
        return result;
    }

    //a = 2, b = [5,2,8]
    public int superPow(int a, int[] b) {
        int result = 1;
        int t = a % M; //a=2
        for (int i = b.length - 1; i >= 0; i--) {
            if (b[i] != 0) {
                result = result * normalPow(t, b[i]) % M;  //1 * 2^8 *  (2^10)^2 * (2^100)^5
            }
            t = normalPow(t, 10); // 2^10  2^100
        }
        return result;
    }
}
