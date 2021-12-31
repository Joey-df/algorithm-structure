package bit_manipulation;

/**
 * 201. 数字范围按位与
 * 给你两个整数 left 和 right ，表示区间 [left, right] ，返回此区间内所有数字 按位与 的结果（包含 left 、right 端点）。
 * 示例 1：
 * 输入：left = 5, right = 7
 * 输出：4
 * 示例 2：
 * 输入：left = 0, right = 0
 * 输出：0
 * 示例 3：
 * 输入：left = 1, right = 2147483647
 * 输出：0
 * 提示：
 * 0 <= left <= right <= 2^31 - 1
 */
public class Problem_0201_BitwiseANDOfNumbersRange {

    //The little trick is to return the left common parts of two numbers.
    //When not equal, move right for 1 bit, util equal, return the common parts.
    public int rangeBitwiseAnd(int m, int n) {
        int count = 0;
        //5与7，即101与111
        //右移2位两者相等，此时都变为1，count等于2，将1左移2位得到答案
        while(m != n){
            m >>= 1;
            n >>= 1;
            count++;
        }
        return m<<=count;
    }

}
