package binary_search;

/**
 * 367. 有效的完全平方数
 * 给定一个 正整数 num ，编写一个函数，如果 num 是一个完全平方数，则返回 true ，否则返回 false 。
 * 进阶：不要 使用任何内置的库函数，如  sqrt 。
 * 示例 1：
 * 输入：num = 16
 * 输出：true
 * 示例 2：
 * 输入：num = 14
 * 输出：false
 * 提示：
 * 1 <= num <= 2^31 - 1
 */
public class Problem_0367_ValidPerfectSquare {

    //在[1,num]之间二分，判断中点m的平方是否等于num
    public boolean isPerfectSquare(int num) {
        int l = 1;
        int r = num;
        while (l <= r) {
            int m = l + ((r - l) >> 1);
            if (power(m, 2) > num) {
                r = m - 1;
            } else if (power(m, 2) < num) {
                l = m + 1;
            } else {
                return true;
            }
        }
        return false;
    }

    public static long power(int num, int power) {
        long ans = 1;
        long base = num;
        for (; power != 0; power >>= 1) {
            if ((power & 1) != 0) {
                ans *= base;
            }
            base *= base;
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(power(3, 2));
    }
}
