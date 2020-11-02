package binary_search;

/**
 * 在有序数组上，找满足 >=value 的最左位置
 */
public class FindLeftBound {

    // 在arr上，找满足>=value的最左位置
    // 如果没找到，L来到arr.length的位置
    // 例如：如果arr=[1,2,3,4]，value=5，最终结束时L会来到arr.length的位置。
    public static int nearestIndex(int[] arr, int value) {
        if (arr.length == 0) return -1;
        int L = 0;
        int R = arr.length - 1;
        int index = -1; //记录对号的位置
        while (L <= R) {
            int mid = L + ((R - L) >> 1);
            if (arr[mid] >= value) {//中点位置的数大于等于value的话，记录位置，然后去左侧去二分
                index = mid;
                R = mid - 1;
            } else {
                L = mid + 1;
            }
        }

        return index;
    }

    public static void main(String[] args) {
        int arr[] = {1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 3, 5, 7};
        int target = 20;
        System.out.println("arr.length = " + arr.length);
        System.out.println("nearestIndex(arr, " + target + ") = " + nearestIndex(arr, target));
    }
}
