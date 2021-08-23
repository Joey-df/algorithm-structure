package math;

import java.util.HashSet;
import java.util.Set;

/**
 * Write an algorithm to determine if a number n is "happy".
 *
 * A happy number is a number defined by the following process: Starting with any positive integer, replace the number by the sum of the squares of its digits, and repeat the process until the number equals 1 (where it will stay), or it loops endlessly in a cycle which does not include 1. Those numbers for which this process ends in 1 are happy numbers.
 *
 * Return True if n is a happy number, and False if not.
 *
 * Example:
 *
 * Input: 19
 * Output: true
 * Explanation:
 * 12 + 92 = 82
 * 82 + 22 = 68
 * 62 + 82 = 100
 * 12 + 02 + 02 = 1
 */
public class Problem_0202_HappyNumber {

    public static boolean isHappy(int n) {
        return process(n);
    }

    //贪心: 拆解的平方和不会超过三位数
    private static boolean process(int n) {
        Set<Integer> set = new HashSet<>();
        while (n != 1) {
            int sum = 0;
            while (n != 0) {
                int t = n % 10;
                sum += Math.pow(t, 2);
                n = n / 10;
            }
            n = sum;
            if (set.contains(n)) break;
            set.add(n);
        }
        return n == 1; //正常结束while循环就等于1，break出来的就不等于1
    }

    public static void main(String[] args) {
        for (int i = 0; i < 1000; i++) {
            System.out.println(i + ": " + isHappy(i));
        }
    }
}
