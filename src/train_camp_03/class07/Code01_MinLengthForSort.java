package train_camp_03.class07;

/**
 * 给定一个无序数组arr，如果只能再一个子数组上排序
 * 返回如果让arr整体有序，需要排序的最短子数组长度
 * <p>
 * https://leetcode-cn.com/problems/shortest-unsorted-continuous-subarray/
 */
public class Code01_MinLengthForSort {
    // [1,2,5,3,4,6,7]
    public static int process(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        //find right bound
        int leftMax = arr[0];
        int rightBound = 0;
        for (int i = 1; i < arr.length; i++) {
            leftMax = Math.max(leftMax, arr[i]);
            if (arr[i] < leftMax) {
                rightBound = i;
            }
        }

        //find left bound
        int rightMin = arr[arr.length - 1];
        int leftBound = arr.length - 1;
        for (int i = arr.length - 2; i >= 0; i--) {
            rightMin = Math.min(rightMin, arr[i]);
            if (arr[i] > rightMin) {
                leftBound = i;
            }
        }
        return (rightBound > leftBound) ? rightBound - leftBound + 1 : 0;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{1, 2, 5, 3, 4, 6, 7};
        System.out.println(process(arr));
    }
}
