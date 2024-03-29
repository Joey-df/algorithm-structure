package math;

/**
 * 693. 交替位二进制数
 * 给定一个正整数，检查它的二进制表示是否总是 0、1 交替出现：换句话说，就是二进制表示中相邻两位的数字永不相同。
 * <p>
 * 示例 1：
 * 输入：n = 5
 * 输出：true
 * 解释：5 的二进制表示是：101
 * 示例 2：
 * 输入：n = 7
 * 输出：false
 * 解释：7 的二进制表示是：111.
 * 示例 3：
 * 输入：n = 11
 * 输出：false
 * 解释：11 的二进制表示是：1011.
 * 示例 4：
 * 输入：n = 10
 * 输出：true
 * 解释：10 的二进制表示是：1010.
 * 示例 5：
 * 输入：n = 3
 * 输出：false
 * <p>
 * 提示：
 * 1 <= n <= 2^31 - 1
 */
public class Problem_0693_BinaryNumberWithAlternatingBits {

    /**
     * 思路
     * n =         1 0 1 0 1 0 1 0
     * n >> 1      0 1 0 1 0 1 0 1
     * n ^ n>>1    1 1 1 1 1 1 1 1
     * n           1 1 1 1 1 1 1 1
     * n + 1     1 0 0 0 0 0 0 0 0
     * n & (n+1)   0 0 0 0 0 0 0 0
     */
    public static boolean hasAlternatingBits(int n) {
        n = n ^ (n >> 1);
        return (n & (n + 1)) == 0;
    }

    public static void main(String[] args) {
        System.out.println(hasAlternatingBits(5));
    }

}
