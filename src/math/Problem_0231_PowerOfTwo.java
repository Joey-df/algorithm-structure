package math;

/**
 * 231. 2 的幂
 * 给你一个整数 n，请你判断该整数是否是 2 的幂次方。如果是，返回 true ；否则，返回 false 。
 * 如果存在一个整数 x 使得 n == 2x ，则认为 n 是 2 的幂次方。
 * <p>
 * 示例 1：
 * 输入：n = 1
 * 输出：true
 * 解释：2^0 = 1
 * 示例 2：
 * 输入：n = 16
 * 输出：true
 * 解释：2^4 = 16
 * 示例 3：
 * 输入：n = 3
 * 输出：false
 * 示例 4：
 * 输入：n = 4
 * 输出：true
 * 示例 5：
 * 输入：n = 5
 * 输出：false
 * <p>
 * 提示：
 * -2^31 <= n <= 2^31 - 1
 */
public class Problem_0231_PowerOfTwo {

    public boolean isPowerOfTwo(int n) {
        if (n <= 0) return false;
        int rightOne = n & -n;
        return rightOne == n;
    }

    //方法2
    public boolean isPowerOfTwo2(int n) {
        if (n <= 0) return false;
        return (n & (n - 1)) == 0;
    }

}
