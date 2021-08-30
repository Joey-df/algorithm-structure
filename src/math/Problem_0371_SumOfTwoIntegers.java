package math;

/**
 * Calculate the sum of two integers a and b, but you are not allowed to use the operator + and -.
 * <p>
 * Example 1:
 * <p>
 * Input: a = 1, b = 2
 * Output: 3
 * Example 2:
 * <p>
 * Input: a = -2, b = 3
 * Output: 1
 */
public class Problem_0371_SumOfTwoIntegers {
    public static int getSum(int a, int b) {
        int sum = 0;
        while (b != 0) { //b充当进位信息, 当进位信息为0时，sum就是答案
            sum = a ^ b;
            b = (a & b) << 1;
            a = sum;
        }
        return sum;
    }

    public static void main(String[] args) {
        System.out.println(getSum(12, 23));
    }
}
