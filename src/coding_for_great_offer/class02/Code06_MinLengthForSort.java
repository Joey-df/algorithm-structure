package coding_for_great_offer.class02;

/**
 * leetcode 581题
 * 给定一个数组arr，只能对arr中的一个子数组排序，
 * 但是想让arr整体都有序
 * 返回满足这一设定的子数组中，最短的是多长
 */
public class Code06_MinLengthForSort {

    // {1, 2, 6, 4, 3, 5, 8, 9}
    public static int findUnsortedSubarray(int[] arr) {
        if (arr == null || arr.length < 2) return 0;
        //找右边界
        int rBound = 0;
        int leftMax = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] < leftMax) rBound = i;
            leftMax = Math.max(leftMax, arr[i]);
        }

        //找左边届
        int lBound = arr.length - 1;
        int rightMin = arr[arr.length - 1];
        for (int i = arr.length - 2; i >= 0; i--) {
            if (arr[i] > rightMin) lBound = i;
            rightMin = Math.min(rightMin, arr[i]);
        }
        return lBound < rBound ? rBound - lBound + 1 : 0;
    }

    public static void main(String[] args) {
//        int[] arr = {1, 2, 6, 4, 3, 5, 8, 9};
        int[] arr = {2, 1};
        System.out.println(findUnsortedSubarray(arr));
    }
}
