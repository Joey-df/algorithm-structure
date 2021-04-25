package binary_search;

/**
 * 在有序数组中，搜索一个数，如果存在，返回其索引，否则返回 -1。
 */

public class FindTarget {

    public static int binarySearch(int nums[], int target) {
        int L = 0;
        int R = nums.length - 1;
        while (L <= R) {
            int mid = L + ((R - L) >> 1);
            if (target == nums[mid]) {
                return mid; //相等即找到，直接返回
            } else if (target > nums[mid]) {
                L = mid + 1;
            } else { //target<nums[mid]
                R = mid - 1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int nums[] = {1, 2, 3, 4, 6, 7};
        int target = 7;
        System.out.println("binarySearch(" + target + ") = " + binarySearch(nums, target));
    }
}
