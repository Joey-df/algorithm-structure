package basic;

/**
 * 一个数组中有一种数出现了奇数次，其他数都出现了偶数次，怎么找到并打印这种数
 * 要求时间复杂度O(N)
 * 知识点：
 * 1、异或即无进位相加
 * 2、异或满足交换律和结合律
 * 3、一大堆数异或起来的结果相等，与异或运算的顺序没有关系
 */
public class FindOddTimesElement {

    public static int find(int[] nums) {
        int ans = 0;
        for (int n : nums) {
            ans ^= n;
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 3, 4, 1, 2, 3, 1, 2, 3, 4, 1, 2, 3, 4};
        System.out.println(find(nums));
    }
}
