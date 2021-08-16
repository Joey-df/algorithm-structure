package system_study.class05;

//快速排序

import system_study.util.ArrayUtil;

/**
 * Partition过程
 * 给定一个数组arr，和一个整数num。请把小于等于num的数放在数组的左边，大于num的数放在数组的右边。
 * 要求额外空间复杂度O(1)，时间复杂度O(N)
 * <p>
 * <p>
 * 荷兰国旗问题
 * 给定一个数组arr，和一个整数num。请把小于num的数放在数组的左边，等于num的数放在中间，大于num的数放在数组的右边。
 * 要求额外空间复杂度O(1)，时间复杂度O(N)
 * <p>
 * 快速排序3.0(随机快排+荷兰国旗技巧优化)
 * <p>
 * 在arr[L..R]范围上，进行快速排序的过程：
 * 1）在这个范围上，随机选一个数记为num，
 * 1）用num对该范围做partition，< num的数在左部分，== num的数中间，>num的数在右部分。假设== num的数所在范围是[a,b]
 * 2）对arr[L..a-1]进行快速排序(递归)
 * 3）对arr[b+1..R]进行快速排序(递归)
 * 因为每一次partition都会搞定一批数的位置且不会再变动，所以排序能完成
 */
public class Code02_PartitionAndQuickSort {

    // arr[L..R]上，以arr[R]位置的数做划分值
    // <= X > X
    // <= X X
    public static int partition(int[] arr, int L, int R) {
        if (L > R) {
            return -1;
        }
        if (L == R) {
            return L;
        }
        int lessEqual = L - 1;
        int index = L;
        while (index < R) {
            if (arr[index] <= arr[R]) {
                ArrayUtil.swap(arr, index, ++lessEqual);
            }
            index++;
        }
        ArrayUtil.swap(arr, ++lessEqual, R);
        return lessEqual;
    }

    // arr[L...R] 玩荷兰国旗问题的划分，以arr[R]做划分值
    // <arr[R] ==arr[R] > arr[R]
    public static int[] netherlandsFlag(int[] arr, int L, int R) {
        if (L > R) { // L...R L>R
            return new int[]{-1, -1};
        }
        if (L == R) {
            return new int[]{L, R};
        }
        int less = L - 1; // < 区 的 右边界
        int more = R + 1; // > 区 的 左边界
        int i = L;
        int base = arr[R];
        while (i < more) { // i==more停
            if (arr[i] == base) {
                i++;
            } else if (arr[i] < base) {
				/*swap(arr, less + 1, i);
				less++;
				i++;*/
                ArrayUtil.swap(arr, i++, ++less);
            } else { // >
                ArrayUtil.swap(arr, i, --more);
            }
        }
        return new int[]{less + 1, more - 1};
    }

    public static void quickSort1(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        process1(arr, 0, arr.length - 1);
    }

    public static void process1(int[] arr, int L, int R) {
        if (L >= R) {
            return;
        }
        // L..R partition arr[R] [ <=arr[R] arr[R] >arr[R] ]
        int M = partition(arr, L, R);
        process1(arr, L, M - 1);
        process1(arr, M + 1, R);
    }


    public static void quickSort2(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        process2(arr, 0, arr.length - 1);
    }

    // arr[L...R] 排有序，快排2.0方式
    public static void process2(int[] arr, int L, int R) {
        if (L >= R) {
            return;
        }
        // [ equalArea[0]  ,  equalArea[0]]
        int[] equalArea = netherlandsFlag(arr, L, R);
        process2(arr, L, equalArea[0] - 1);
        process2(arr, equalArea[1] + 1, R);
    }

    public static void quickSort3(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        process3(arr, 0, arr.length - 1);
    }

    public static void process3(int[] arr, int L, int R) {
        if (L >= R) {
            return;
        }
        ArrayUtil.swap(arr, L + (int) (Math.random() * (R - L + 1)), R);
        int[] equalArea = netherlandsFlag(arr, L, R);
        process3(arr, L, equalArea[0] - 1);
        process3(arr, equalArea[1] + 1, R);
    }

    // for test
    public static void main(String[] args) {
        int testTime = 500000;
        int maxSize = 100;
        int maxValue = 100;
        boolean succeed = true;
        for (int i = 0; i < testTime; i++) {
            int[] arr1 = ArrayUtil.generateRandomArray(maxSize, maxValue);
            int[] arr2 = ArrayUtil.copyArray(arr1);
            int[] arr3 = ArrayUtil.copyArray(arr1);
            quickSort1(arr1);
            quickSort2(arr2);
            quickSort3(arr3);
            if (!ArrayUtil.isEqual(arr1, arr2) || !ArrayUtil.isEqual(arr2, arr3)) {
                succeed = false;
                ArrayUtil.printArray(arr1);
                ArrayUtil.printArray(arr2);
                ArrayUtil.printArray(arr3);
                break;
            }
        }
        System.out.println(succeed ? "Nice!" : "Oops!");

    }

}
