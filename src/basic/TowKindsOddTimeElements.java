package basic;

/**
 * leetcode 260. Single Number III
 * 一个数组中有两种数出现了奇数次，其他数都出现了偶数次，怎么找到并打印这两种数
 * O(N)
 */
public class TowKindsOddTimeElements {

    public static int[] find(int[] nums) {
        //假设两种奇数次的数为a、b
        int eor = 0;
        for (int n : nums) {
            eor ^= n;
        }
        // eor = a^b
        int rightOne = eor & (-eor); //提取出最右侧的1 就可以将a、b两个数区分开
        int a = 0;
        for (int n : nums) {
            if ((rightOne & n) != 0) {
                a ^= n;
            }
        }
        System.out.println(a + "   " + (eor ^ a));
        return new int[]{a, eor ^ a};
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 3, 4, 1, 2, 3, 1, 2, 3, 4, 1, 2, 3, 4, 5};
        System.out.println(find(nums));
    }
}
