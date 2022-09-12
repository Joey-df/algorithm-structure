package coding_for_great_offer.class29;

public class Problem_0062_UniquePaths {

    // m 行
    // n 列
    // 下：m-1
    // 右：n-1
    // c(all, right)的结果就是答案
    public static int uniquePaths(int m, int n) {
        int right = n - 1;
        int all = m + n - 2;
        return (int) c(all, right);
    }

    //c(a,b)
    public static long c(long a, long b) {
        if (a == b) {
            return 1;
        }
        long x = 1; //分子
        long y = 1; //分母
        // x乘进去的个数 一定等于 y乘进去的个数
        for (long i = b + 1, j = 1; i <= a; i++, j++) {
            x *= i;
            y *= j;
            long gcd = gcd(x, y);
            x /= gcd;
            y /= gcd;
        }
        return x / y;
    }

    // 调用的时候，请保证初次调用时，m和n都不为0
    public static long gcd(long m, long n) {
        return n == 0 ? m : gcd(n, m % n);
    }

}
