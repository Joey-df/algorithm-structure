package leetcode_top_interview_and_top100liked_questions;

/**
 * 191. 位1的个数
 * 编写一个函数，输入是一个无符号整数（以二进制串的形式），
 * 返回其二进制表达式中数字位数为 '1' 的个数（也被称为汉明重量）。
 */
public class Problem_0191_NumberOf1Bits {

    public static int hammingWeight(int n) {
        int ans = 0;
        while (n != 0) {
            int ro = n & (~n + 1);
            n ^= ro;
            ans++;
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(hammingWeight(31));
    }
}
