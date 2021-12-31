package array;

/**
 * 905. 数组调整为左边偶数右边奇数
 * 给定一个非负整数数组 A，返回一个数组，在该数组中， A 的所有偶数元素之后跟着所有奇数元素。
 * 你可以返回满足此条件的任何数组作为答案。
 * 示例：
 * 输入：[3,1,2,4]
 * 输出：[2,4,3,1]
 * 输出 [4,2,3,1]，[2,4,1,3] 和 [4,2,1,3] 也会被接受。
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
