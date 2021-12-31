package array;

/**
 * 66. 加一
 * 给定一个由 整数 组成的 非空 数组所表示的非负整数，在该数的基础上加一。
 * 最高位数字存放在数组的首位， 数组中每个元素只存储单个数字。
 * 你可以假设除了整数 0 之外，这个整数不会以零开头。
 *
 * 示例 1：
 * 输入：digits = [1,2,3]
 * 输出：[1,2,4]
 * 解释：输入数组表示数字 123。
 * 示例 2：
 * 输入：digits = [4,3,2,1]
 * 输出：[4,3,2,2]
 * 解释：输入数组表示数字 4321。
 * 示例 3：
 * 输入：digits = [0]
 * 输出：[1]
 *
 * 1 <= digits.length <= 100
 * 0 <= digits[i] <= 9
 */
public class Problem_0066_PlusOne {

    //1 <= digits.length <= 100
    //0 <= digits[i] <= 9
    //the integer does not contain any leading zero, except the number 0 itself.
    public static int[] plusOne(int[] digits) {
        int N = digits.length;
        for (int i = N - 1; i >= 0; i--) {
            if (digits[i] < 9) {
                digits[i]++;
                return digits;
            } else { // ==9
                digits[i] = 0;
            }
        }
        //走到这说明digits每一位全是9
        int[] ans = new int[N + 1];
        ans[0] = 1;
        return ans;
    }
}
