package binary_search;

/**
 * 在有序数组上，找满足 >=value 的最左位置
 */
public class FindLeftBound {

    public static int leftBound(int[] nums, int target) {
        if (nums.length == 0) return -1;
        int left = 0;
        int right = nums.length; // 注意

        while (left < right) { // 注意
            int mid = (left + right) / 2;
            if (target == nums[mid]) { //相等时，别返回，锁定右边界，继续到左边找
                right = mid;
            } else if (target > nums[mid]) {
                left = mid + 1;
            } else { //target < nums[mid]
                right = mid;
            }
        }
        return left;
    }

    // 在arr上，找满足>=value的最左位置
    // 如果没找到，L来到R+1的位置
    public static int nearestIndex(int[] arr, int value) {
        if (arr.length == 0) return -1;
        int L = 0;
        int R = arr.length - 1;
        while (L <= R) {
            int mid = L + ((R - L) >> 1);
            if (value == arr[mid]) { //相等时，别返回，锁定右边界，继续到左边找
                R = mid - 1;
            } else if (value > arr[mid]) {
                L = mid + 1;
            } else { //value < arr[mid]
                R = mid - 1;
            }
        }
        // 最后要检查 left 越界的情况
        if (L >= arr.length || arr[L] != value)
            return -1;
        return L;
    }

    public static void main(String[] args) {
        int arr[] = {1, 2, 2, 2, 2, 2, 4, 4, 5, 6, 6, 6, 8, 10, 11};
//        int arr[] = {2, 3, 5, 7};
        int target = 60;
        System.out.println("arr.length = " + arr.length);
        System.out.println("leftBound(arr, " + target + ") = " + leftBound(arr, target));
        System.out.println("nearestIndex(arr, " + target + ") = " + nearestIndex(arr, target));
    }
}
