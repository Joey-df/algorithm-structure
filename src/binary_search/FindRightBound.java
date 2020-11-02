package binary_search;

/**
 * 在有序数组上，找满足 <=value 的最右位置
 */
public class FindRightBound {

    // 在arr上，找满足<=value的最右位置
    // 如果没找到，R来到-1位置
    // 例如：如果arr=[1,2,3,4]，value=0，最终结束时R会来到-1的位置。
    public static int nearestIndex(int[] arr, int value) {
        if (arr.length == 0) return -1;
        int L = 0;
        int R = arr.length - 1;
        int index = -1;
        while (L <= R) {
            int mid = L + ((R - L) >> 1);
            if (arr[mid] <= value) {//中点位置的数<=value的话，记录位置，然后去右边二分
                index = mid;
                L = mid + 1;
            } else {
                R = mid - 1;
            }
        }

        return index;
    }

    public static void main(String[] args) {
        int arr[] = {1, 2, 2, 2, 2, 2, 4, 4, 5, 6, 6, 6, 8, 10, 11};
//        int arr[] = {1, 2, 3, 4};
        int target = -100;
        System.out.println("arr.length = " + arr.length);
        System.out.println("nearestIndex(arr, " + target + ") = " + nearestIndex(arr, target));
    }
}
