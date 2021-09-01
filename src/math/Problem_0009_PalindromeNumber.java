package math;

import org.omg.CORBA.INTERNAL;

/**
 * 9. 回文数
 * 给你一个整数 x ，如果 x 是一个回文整数，返回 true ；否则，返回 false 。
 * 回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。例如，121 是回文，而 123 不是。
 * <p>
 * 示例 1：
 * 输入：x = 121
 * 输出：true
 * 示例 2：
 * 输入：x = -121
 * 输出：false
 * 解释：从左向右读, 为 -121 。 从右向左读, 为 121- 。因此它不是一个回文数。
 * 示例 3：
 * 输入：x = 10
 * 输出：false
 * 解释：从右向左读, 为 01 。因此它不是一个回文数。
 * 示例 4：
 * 输入：x = -101
 * 输出：false
 * <p>
 * 提示：
 * -2^31 <= x <= 2^31 - 1
 */
public class Problem_0009_PalindromeNumber {

    //算法原型：leetcode7 整数反转
    public static boolean isPalindrome(int x) {
        boolean neg = ((x >>> 31) & 1) == 1;
        if (neg) return false;
        //x是正数
        int num = x; //备份x
        x = -x; // 变成负数
        int m = Integer.MIN_VALUE / 10;
        int o = Integer.MIN_VALUE % 10;
        int res = 0;
        while (x != 0) {
            if (res < m || (res == m && x % 10 < o)) { //这些情况会溢出
                return false;
            }
            res = res * 10 + x % 10;
            x = x / 10;
        }
        return -res == num;
    }


    public static void main(String[] args) {
        boolean b = isPalindrome(121);
        System.out.println(b);
    }
}
