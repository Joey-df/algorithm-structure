package math;

/**
 * 172. 阶乘后的零
 * 给定一个整数 n，返回 n! 结果尾数中零的数量。
 *
 * 示例 1:
 *
 * 输入: 3
 * 输出: 0
 * 解释: 3! = 6, 尾数中没有零。
 * 示例 2:
 *
 * 输入: 5
 * 输出: 1
 * 解释: 5! = 120, 尾数中有 1 个零.
 * 说明: 你算法的时间复杂度应为 O(log n) 。
 */
public class Problem_0172_FactorialTrailingZeroes {

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
