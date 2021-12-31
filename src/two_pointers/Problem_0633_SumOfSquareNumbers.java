package two_pointers;

/**
 * 633. 判断一个数是否为两个数的平方和
 * 给定一个非负整数 c ，你要判断是否存在两个整数 a 和 b，使得 a^2 + b^2 = c 。
 * 示例 1：
 * 输入：c = 5
 * 输出：true
 * 解释：1 * 1 + 2 * 2 = 5
 * 示例 2：
 * 输入：c = 3
 * 输出：false
 * 示例 3：
 * 输入：c = 4
 * 输出：true
 */
public class Problem_0633_SumOfSquareNumbers {

    public static boolean judgeSquareSum(int c) {
        int l = 0;
        int r = (int) Math.sqrt(c);
        while (l <= r) {
            int sum = (int) (Math.pow(l, 2) + Math.pow(r, 2));
            if (sum == c) {
                return true;
            } else if (sum < c) {
                l++;
            } else {
                r--;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(judgeSquareSum(4));
    }

}
