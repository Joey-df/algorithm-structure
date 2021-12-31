package binary_search;

/**
 * 34. 在排序数组中查找元素的第一个和最后一个位置
 * 给定一个按照升序排列的整数数组 nums，和一个目标值 target。找出给定目标值在数组中的开始位置和结束位置。
 * 如果数组中不存在目标值 target，返回 [-1, -1]。
 * 进阶：
 * 你可以设计并实现时间复杂度为 O(log n) 的算法解决此问题吗？
 * 示例 1：
 * 输入：nums = [5,7,7,8,8,10], target = 8
 * 输出：[3,4]
 * 示例 2：
 * 输入：nums = [5,7,7,8,8,10], target = 6
 * 输出：[-1,-1]
 * 示例 3：
 * 输入：nums = [], target = 0
 * 输出：[-1,-1]
 */
public class Problem_0034_FindFirstAndLastPositionOfElementInSortedArray {
    public static int[] searchRange(int[] nums, int target) {
        int leftBound = findLeftBound(nums, target);
        int rightBound = findRightBound(nums, target);
        return new int[]{leftBound, rightBound};
    }

    private static int findLeftBound(int[] nums, int target) {
        int l = 0;
        int r = nums.length - 1;
        int ans = -1;
        while (l <= r) {
            int mid = l + ((r - l) >> 1);
            if (nums[mid] > target) {
                r = mid - 1;
            } else if (nums[mid] == target) {
                r = mid - 1;
                ans = mid;
            } else {
                l = mid + 1;
            }
        }
        return ans;
    }

    private static int findRightBound(int[] nums, int target) {
        int l = 0;
        int r = nums.length - 1;
        int ans = -1;
        while (l <= r) {
            int mid = l + ((r - l) >> 1);
            if (nums[mid] < target) {
                l = mid + 1;
            } else if (nums[mid] == target) {
                l = mid + 1;
                ans = mid;
            } else {
                r = mid - 1;
            }
        }
        return ans;
    }
}
