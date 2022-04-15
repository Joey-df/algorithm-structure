package math;

/**
 * 507. 完美数
 * 对于一个 正整数，如果它和除了它自身以外的所有 正因子 之和相等，我们称它为 「完美数」。
 * 给定一个 整数 n， 如果是完美数，返回 true，否则返回 false
 * <p>
 * 示例 1：
 * 输入：28
 * 输出：True
 * 解释：28 = 1 + 2 + 4 + 7 + 14
 * 1, 2, 4, 7, 和 14 是 28 的所有正因子。
 * <p>
 * 示例 2：
 * 输入：num = 6
 * 输出：true
 * <p>
 * 示例 3：
 * 输入：num = 496
 * 输出：true
 * <p>
 * 示例 4：
 * 输入：num = 8128
 * 输出：true
 * <p>
 * 示例 5：
 * 输入：num = 2
 * 输出：false
 * <p>
 * 提示：
 * 1 <= num <= 10^8
 */
// 结论：
// 求一个正数N的所有因子，只需要从 1～根号N 范围上试，即可
public class Problem_0507_PerfectNumber {

    public boolean checkPerfectNumber(int num) {
        if (num <= 1) return false;
        int sum = 1; // 1是一个因子
        int mid = (int) Math.sqrt(num);
        for (int x = 2; x <= mid; x++) {
            if (num % x == 0) {
                sum += x;
                sum += (x != num / x) ? num / x : 0;
            }
        }
        return sum == num;
    }

}
