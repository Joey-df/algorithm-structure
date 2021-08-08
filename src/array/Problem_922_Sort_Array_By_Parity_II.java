package array;

/**
 * Given an array A of non-negative integers, half of the integers in A are odd, and half of the integers are even.
 * <p>
 * Sort the array so that whenever A[i] is odd, i is odd; and whenever A[i] is even, i is even.
 * <p>
 * You may return any answer array that satisfies this condition.
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: [4,2,5,7]
 * Output: [4,5,2,7]
 * Explanation: [4,7,2,5], [2,5,4,7], [2,7,4,5] would also have been accepted.
 * <p>
 * <p>
 * Note:
 * <p>
 * 2 <= A.length <= 20000
 * A.length % 2 == 0
 * 0 <= A[i] <= 1000
 */
// 原数组调整为 奇数在奇数下标，偶数在偶数下标
public class Problem_922_Sort_Array_By_Parity_II {

    public int[] sortArrayByParityII(int[] nums) {
        int even = 0; //初始偶数下标
        int odd = 1; //初始奇数下标
        int end = nums.length - 1;//最后一个数作为发射源
        while (even < nums.length && odd < nums.length) { //任何一个指针越界了 停
            if ((nums[end] & 1) == 1) {//奇数
                swap(nums, odd, end);
                odd += 2;
            } else {
                swap(nums, even, end);
                even += 2;
            }
        }
        return nums;
    }

    private static void swap(int[] nums, int i, int j) {
        int t = nums[i];
        nums[i] = nums[j];
        nums[j] = t;
    }
}
