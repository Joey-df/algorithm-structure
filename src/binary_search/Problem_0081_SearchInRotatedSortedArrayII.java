package binary_search;

/**
 * 81. 搜索旋转排序数组II
 * 已知存在一个按非降序排列的整数数组 nums ，数组中的值不必互不相同。
 * 在传递给函数之前，nums 在预先未知的某个下标 k（0 <= k < nums.length）上进行了 旋转 ，
 * 使数组变为 [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]]（下标 从 0 开始 计数）。
 * 例如， [0,1,2,4,4,4,5,6,6,7] 在下标 5 处经旋转后可能变为 [4,5,6,6,7,0,1,2,4,4] 。
 * 给你 旋转后 的数组 nums 和一个整数 target ，请你编写一个函数来判断给定的目标值是否存在于数组中。
 * 如果 nums 中存在这个目标值 target ，则返回 true ，否则返回 false 。
 * 示例 1：
 * 输入：nums = [2,5,6,0,0,1,2], target = 0
 * 输出：true
 * 示例 2：
 * 输入：nums = [2,5,6,0,0,1,2], target = 3
 * 输出：false
 */
public class Problem_0081_SearchInRotatedSortedArrayII {

    public boolean search(int[] nums, int target) {
        int s = 0;
        int e = nums.length - 1;
        int mid = -1;
        while(s <= e) {
            mid = (s + e) / 2;
            if (nums[mid] == target) {
                return true;
            }
            //If we know for sure right side is sorted or left side is unsorted
            if (nums[mid] < nums[e] || nums[mid] < nums[s]) {
                if (target > nums[mid] && target <= nums[e]) {
                    s = mid + 1;
                } else {
                    e = mid - 1;
                }
                //If we know for sure left side is sorted or right side is unsorted
            } else if (nums[mid] > nums[s] || nums[mid] > nums[e]) {
                if (target < nums[mid] && target >= nums[s]) {
                    e = mid - 1;
                } else {
                    s = mid + 1;
                }
                //If we get here, that means nums[s] == nums[mid] == nums[e], then shifting out
                //any of the two sides won't change the result but can help remove duplicate from
                //consideration, here we just use e-- but left++ works too
            } else {
                e--;
            }
        }

        return false;
    }

}
