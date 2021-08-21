package leetcode_top_interview_and_top100liked_questions;

/**
 * 34. 在排序数组中查找元素的第一个和最后一个位置
 * 给定一个按照升序排列的整数数组 nums，和一个目标值 target。找出给定目标值在数组中的开始位置和结束位置。
 * 如果数组中不存在目标值 target，返回 [-1, -1]。
 * <p>
 * 示例 1：
 * 输入：nums = [5,7,7,8,8,10], target = 8
 * 输出：[3,4]
 * <p>
 * 示例 2：
 * 输入：nums = [5,7,7,8,8,10], target = 6
 * 输出：[-1,-1]
 * <p>
 * 示例 3：
 * 输入：nums = [], target = 0
 * 输出：[-1,-1]
 */
public class Problem_0034_FindFirstAndLastPositionOfElementInSortedArray {

    public static int[] searchRange(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return new int[]{-1, -1};
        }
        int l = findLeftBound(nums, target);
        int r = findRightBound(nums, target);
        return new int[]{l, r};
    }

    //在有序数组中找等于t最左的位置
    private static int findLeftBound(int[] arr, int t) {
        int index = -1;
        int l = 0;
        int r = arr.length - 1;
        while (l <= r) {
            int m = l + ((r - l) >> 1);
            if (arr[m] > t) {
                r = m - 1;
            } else if (arr[m] < t) {
                l = m + 1;
            } else {
                index = m; //等于的时候才记录位置
                r = m - 1;
            }
        }
        return index;
    }


    //在有序数组中找等于t最右侧的位置
    private static int findRightBound(int[] arr, int t) {
        int ans = -1;
        int l = 0, r = arr.length - 1;
        while (l <= r) {
            int m = l + ((r - l) >> 1);
            if (arr[m] < t) {
                l = m + 1;
            } else if (arr[m] > t) {
                r = m - 1;
            } else {
                ans = m; //等于的时候才记录位置
                l = m + 1;
            }
        }
        return ans;
    }
}
