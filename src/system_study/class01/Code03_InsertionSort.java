package system_study.class01;

//插入排序
//过程：
//想让arr[0~0]上有序，这个范围只有一个数，当然是有序的。
//想让arr[0~1]上有序，所以从arr[1]开始往前看，如果arr[1]<arr[0]，就交换。否则什么也不做。
//…
//想让arr[0~i]上有序，所以从arr[i]开始往前看，arr[i]这个数不停向左移动，一直移动到左边的数字不再比自己大，停止移动。
//最后一步，想让arr[0~N-1]上有序， arr[N-1]这个数不停向左移动，一直移动到左边的数字不再比自己大，停止移动。
//
//估算时发现这个算法流程的复杂程度，会因为数据状况的不同而不同。
//
//你发现了吗？

//如果某个算法流程的复杂程度会根据数据状况的不同而不同，那么你必须要按照最差情况来估计。
//很明显，在最差情况下，如果arr长度为N，插入排序的每一步常数操作的数量，还是如等差数列一般
//所以，总的常数操作数量 = a*(N^2) + b*N + c (a、b、c都是常数)
//所以插入排序排序的时间复杂度为O(N^2)。

import system_study.util.ArrayUtil;

import java.util.Arrays;

public class Code03_InsertionSort {
    public static void insertionSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        // 不只1个数
        for (int i = 1; i < arr.length; i++) { // 0 ~ i 做到有序
            for (int j = i - 1; j >= 0 && arr[j] > arr[j + 1]; j--) {
                ArrayUtil.swap(arr, j, j + 1);
            }
        }
    }

    // for test
    public static void comparator(int[] arr) {
        Arrays.sort(arr);
    }

    // for test
    public static void main(String[] args) {
        int testTime = 500000;
        int maxSize = 100; // 随机数组的长度0～100
        int maxValue = 100;// 值：-100～100
        boolean succeed = true;
        for (int i = 0; i < testTime; i++) {
            int[] arr = ArrayUtil.generateRandomArray(maxSize, maxValue);
            int[] arr1 = ArrayUtil.copyArray(arr);
            int[] arr2 = ArrayUtil.copyArray(arr);
            insertionSort(arr1);
            comparator(arr2);
            if (!ArrayUtil.isEqual(arr1, arr2)) {
                // 打印arr1
                // 打印arr2
                succeed = false;
                for (int j = 0; j < arr.length; j++) {
                    System.out.print(arr[j] + " ");
                }
                System.out.println();
                break;
            }
        }
        System.out.println(succeed ? "Nice!" : "Fucking fucked!");

        int[] arr = ArrayUtil.generateRandomArray(maxSize, maxValue);
        ArrayUtil.printArray(arr);
        insertionSort(arr);
        ArrayUtil.printArray(arr);
    }
}
