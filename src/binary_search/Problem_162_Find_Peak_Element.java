package binary_search;

/**
 * A peak element is an element that is greater than its neighbors.
 * 
 * Given an input array nums, where nums[i] ≠ nums[i+1], find a peak element and return its index.
 * 
 * The array may contain multiple peaks, in that case return the index to any one of the peaks is fine.
 * 
 * You may imagine that nums[-1] = nums[n] = -∞.
 * 
 * Example 1:
 * 
 * Input: nums = [1,2,3,1]
 * Output: 2
 * Explanation: 3 is a peak element and your function should return the index number 2.
 * Example 2:
 * 
 * Input: nums = [1,2,1,3,5,6,4]
 * Output: 1 or 5
 * Explanation: Your function can return either index number 1 where the peak element is 2,
 * or index number 5 where the peak element is 6.
 * Follow up: Your solution should be in logarithmic complexity.
 * 二分查找找局部最大值问题
 */
public class Problem_162_Find_Peak_Element {

    public static int findPeakElement(int[] nums) {
        if (nums == null || nums.length == 0) return -1;
        return process(nums, 0, nums.length - 1);
    }

    //递归含义：在arr[L...R]范围上找局部最大值
    //返回局部最大值的位置
    public static int process(int[] arr, int L, int R) {
        if (L == R) return L;//base case
        //走到这里说明 L>R
        if (arr[L] > arr[L + 1]) return L;
        if (arr[R] > arr[R - 1]) return R;
        //走到这里说明L，R位置都不是局部最大,在[L+1, R-1]范围上判断
        int mid = L + ((R - L) >> 1);
        if (arr[mid] > arr[mid + 1] && arr[mid] > arr[mid - 1]) return mid;
        if (arr[mid] > arr[mid - 1]) {
            //在右半部分找
            return process(arr, mid + 1, R - 1);
        }
        //在左半部分找
        return process(arr, L + 1, mid - 1);
    }

    //使用迭代
    public static int process2(int[] arr) {
        if (arr == null || arr.length == 0) return -1;
        if (arr.length == 1) return 0;
        //走到这里说明nums长度大于1
        int L = 0;
        int R = arr.length - 1;
        if (arr[L] > arr[L + 1]) return L;
        if (arr[R] > arr[R - 1]) return R;
        //走到这里说明，L，R位置都不是局部最大,在[L+1, R-1]范围上判断
        int left = L + 1;
        int right = R - 1;
        while (left < right) { //left==right时退出
            int mid = (left + right) / 2;
            if (arr[mid] > arr[mid - 1] && arr[mid] > arr[mid + 1]) {
                return mid;
            }
            if (arr[mid] > arr[mid - 1]) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        //返回left right都一样
        return left;
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 0, 8, 4, 7, 3, 1};
        System.out.println(findPeakElement(nums));
        System.out.println(process2(nums));
    }
}
