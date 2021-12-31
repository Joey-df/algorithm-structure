package array;

/**
 * 922. 奇数放奇数位偶数放偶数位
 * 给定一个非负整数数组 A， A 中一半整数是奇数，一半整数是偶数。
 * 对数组进行排序，以便当 A[i] 为奇数时，i 也是奇数；当 A[i] 为偶数时， i 也是偶数。
 * 你可以返回任何满足上述条件的数组作为答案。
 *
 * 示例：
 * 输入：[4,2,5,7]
 * 输出：[4,5,2,7]
 * 解释：[4,7,2,5]，[2,5,4,7]，[2,7,4,5] 也会被接受。
 */
// 原数组调整为 奇数在奇数下标，偶数在偶数下标
public class Problem_0922_SortArrayByParityII {

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
