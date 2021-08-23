package array;

/**
 * 31. 下一个排列
 * 实现获取 下一个排列 的函数，算法需要将给定数字序列重新排列成字典序中下一个更大的排列（即，组合出下一个更大的整数）。
 * 如果不存在下一个更大的排列，则将数字重新排列成最小的排列（即升序排列）。
 * 必须 原地 修改，只允许使用额外常数空间。
 *
 * 示例 1：
 * 输入：nums = [1,2,3]
 * 输出：[1,3,2]
 * 示例 2：
 * 输入：nums = [3,2,1]
 * 输出：[1,2,3]
 * 示例 3：
 * 输入：nums = [1,1,5]
 * 输出：[1,5,1]
 * 示例 4：
 * 输入：nums = [1]
 * 输出：[1]
 *
 * 提示：
 * 1 <= nums.length <= 100
 * 0 <= nums[i] <= 100
 */
public class Problem_0031_NextPermutation {

    //从右往左，找第一个升序的元素下标i
    //从右往左，找以第一个比nums[i]大的元素下标j
    //交换i,j对应元素
    //将nums[i+1...]整体反转
    public static void nextPermutation(int[] nums) {
        if (nums==null || nums.length<2) {
            return;
        }
        int i=nums.length-2;
        for (; i>=0; i--) {
            if (nums[i] < nums[i+1]) break;
        }
        if (i > -1) { //防止nums是完全降序的情况[5,4,3,2,1]
            int j=nums.length-1;
            for (; j>i ; j--) {
                if (nums[j] > nums[i]) break;
            }
            swap(nums, i, j);
        }
        reverse(nums, i+1, nums.length-1);
    }

    private static void swap(int[] arr, int i, int j) {
        arr[i] = arr[i] + arr[j];
        arr[j] = arr[i] - arr[j];
        arr[i] = arr[i] - arr[j];
    }

    private static void reverse(int[] arr, int L, int R) {
        while(L < R) {
            swap(arr, L++, R--);
        }
    }


    public static void main(String[] args) {
        int[] arr = {6,8,7,5,4,3,2,1};
        nextPermutation(arr);
        for (int n:arr) {
            System.out.print(n + " ");
        }
    }
}
