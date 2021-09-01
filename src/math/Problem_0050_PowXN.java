package math;

/**
 * Implement pow(x, n), which calculates x raised to the power n (i.e. xn).
 * <p>
 * Example 1:
 * <p>
 * Input: x = 2.00000, n = 10
 * Output: 1024.00000
 * Example 2:
 * <p>
 * Input: x = 2.10000, n = 3
 * Output: 9.26100
 * Example 3:
 * <p>
 * Input: x = 2.00000, n = -2
 * Output: 0.25000
 * Explanation: 2-2 = 1/22 = 1/4 = 0.25
 * <p>
 * <p>
 * Constraints:
 * <p>
 * -100.0 < x < 100.0
 * -231 <= n <= 231-1
 * -104 <= xn <= 104
 */
//求double类型x的n次方
//快速幂
public class Problem_0050_PowXN {

    //求x的n次方
    public static double power(double x, int n) {
        if (n == 0) return 1D; //任何数的0次方都等于1
        boolean neg = ((n >> 31) & 1) == 1; //是否为负数
        //因为系统最小值 的绝对值 比系统最大 大一个
        //所以为了取绝对值，把系统最小+1，取绝对值后就是系统最大
        int pow = (n == Integer.MIN_VALUE) ? n + 1 : n;
        pow = Math.abs(pow);
        double ans = 1D;
        double t = x;
        while (pow != 0) {
            if ((pow & 1) == 1) {
                ans *= t;
            }
            t *= t;
            pow >>= 1; //pow >>>= 1也可以，反正都是正数了，带符号(>>>)与不带符号右移(>>)都一样
        }
        if (n == Integer.MIN_VALUE) {
            ans *= x;
        }
        return neg ? 1d / ans : ans;
    }

    public static void main(String[] args) {
        System.out.println(power(2.1, -3));
    }
}
