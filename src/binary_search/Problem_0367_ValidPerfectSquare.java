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
            if (Math.pow(m, 2) > num) {
                r = m-1;
            } else if (Math.pow(m, 2) < num) {
                l = m+1;
            } else {
                return true;
            }
        }
        return false;
    }
}
