package binary_search;


/**
 * 441. 排列硬币
 * 你总共有 n 枚硬币，你需要将它们摆成一个阶梯形状，第 k 行就必须正好有 k 枚硬币。
 * 给定一个数字 n，找出可形成完整阶梯行的总行数。
 * n 是一个非负整数，并且在32位有符号整型的范围内。
 * 示例 1：
 * 输入：n = 5
 * 输出：2
 * 解释：因为第三行不完整，所以返回 2 。
 * 示例 2：
 * 输入：n = 8
 * 输出：3
 * 解释：因为第四行不完整，所以返回 3 。
 * 提示：
 * 1 <= n <= 2^31 - 1
 */
public class Problem_0441_ArrangingCoins {

    //根据等差数列求和公式可知，前 k 个完整阶梯行所需的硬币数量为 K*(K+1)/2
    //Here we need to find largest K that satisfies this equation K*(K+1)/2 <= N.
    public int arrangeCoins(int n) {
        if (n < 0) return 0;
        if (n == 1 || n == 2) return 1;
        long l = 1;
        long r = n - 1;
        while (l <= r) {
            long mid = l + ((r - l) >> 1);
            long one_to_n = mid * (mid + 1) / 2; //1~n的累加和
            if (one_to_n < n) {
                l = mid + 1;
            } else if (one_to_n > n) {
                r = mid - 1;
            } else {
                return (int) mid;
            }
        }
        // When our "l" is greater than our "r",
        // our "l" will be set to a incomplete row and our "r" will be set to a complete row,
        // so we return "r"
        return (int) r;
    }

}
