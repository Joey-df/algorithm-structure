package math;

/**
 * 342. 4的幂
 * 给定一个整数，写一个函数来判断它是否是 4 的幂次方。如果是，返回 true ；否则，返回 false 。
 * 整数 n 是 4 的幂次方，需满足：存在整数 x 使得 n == 4^x
 * <p>
 * 示例 1：
 * 输入：n = 16
 * 输出：true
 * 示例 2：
 * 输入：n = 5
 * 输出：false
 * 示例 3：
 * 输入：n = 1
 * 输出：true
 * 提示：
 * -2^31 <= n <= 2^31 - 1
 */
public class Problem_0342_PowerOfFour {
    //1、必须是正数
    //2、二进制只有一个1
    //3、1后面偶数个0
    public boolean isPowerOfFour(int n) {
        if (n <= 0) return false;
        if (n == 1) return true;
        return ((n & (~n + 1)) == n)  //二进制只有一个1
                && ((n & (0x55555555)) != 0);//1都在奇数位(也就是说1后面有偶数个0)
    }

}
