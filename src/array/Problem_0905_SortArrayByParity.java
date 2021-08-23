package array;

/**
 * Given an array A of non-negative integers,
 * return an array consisting of all the even elements of A, followed by all the odd elements of A.
 * <p>
 * You may return any answer array that satisfies this condition.
 * <p>
 * Example 1:
 * <p>
 * Input: [3,1,2,4]
 * Output: [2,4,3,1]
 * The outputs [4,2,3,1], [2,4,1,3], and [4,2,1,3] would also be accepted.
 * <p>
 * <p>
 * Note:
 * <p>
 * 1 <= A.length <= 5000
 * 0 <= A[i] <= 5000
 */

//原数组调为左边全是偶数，右边全是奇数
public class Problem_0905_SortArrayByParity {
    public int[] sortArrayByParity(int[] nums) {
        int l = 0;
        int r = nums.length - 1;
        while (l < r) {
            while (l < nums.length && (nums[l] & 1) == 0) l++; //l来到第一个为奇数的位置
            while (r >= 0 && (nums[r] & 1) == 1) r--; //r来到第一个为偶数的位置
            if (l > r) break;
            swap(nums, l++, r--);
        }
        return nums;
    }

    private static void swap(int[] nums, int i, int j) {
        int t = nums[i];
        nums[i] = nums[j];
        nums[j] = t;
    }
}
