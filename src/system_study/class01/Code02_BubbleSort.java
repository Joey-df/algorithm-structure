package system_study.class01;

import system_study.util.ArrayUtil;
import java.util.Arrays;

//冒泡排序
//过程：
//在arr[0～N-1]范围上：
//arr[0]和arr[1]，谁大谁来到1位置；arr[1]和arr[2]，谁大谁来到2位置…arr[N-2]和arr[N-1]，谁大谁来到N-1位置
//
//在arr[0～N-2]范围上，重复上面的过程，但最后一步是arr[N-3]和arr[N-2]，谁大谁来到N-2位置
//在arr[0～N-3]范围上，重复上面的过程，但最后一步是arr[N-4]和arr[N-3]，谁大谁来到N-3位置
//…
//最后在arr[0～1]范围上，重复上面的过程，但最后一步是arr[0]和arr[1]，谁大谁来到1位置
//
//
//估算：
//很明显，如果arr长度为N，每一步常数操作的数量，依然如等差数列一般
//所以，总的常数操作数量 = a*(N^2) + b*N + c (a、b、c都是常数)
//
//所以冒泡排序的时间复杂度为O(N^2)。
public class Code02_BubbleSort {
    public static void bubbleSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        // 0 ~ N-1
        // 0 ~ N-2
        // 0 ~ N-3
        for (int e = arr.length - 1; e > 0; e--) { // 0 ~ e
            for (int i = 0; i < e; i++) {
                if (arr[i] > arr[i + 1]) {
                    ArrayUtil.swap(arr, i, i + 1);
                }
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
        int maxSize = 100;
        int maxValue = 100;
        boolean succeed = true;
        for (int i = 0; i < testTime; i++) {
            int[] arr1 = ArrayUtil.generateRandomArray(maxSize, maxValue);
            int[] arr2 = ArrayUtil.copyArray(arr1);
            bubbleSort(arr1);
            comparator(arr2);
            if (!ArrayUtil.isEqual(arr1, arr2)) {
                succeed = false;
                break;
            }
        }
        System.out.println(succeed ? "Nice!" : "Fucking fucked!");

        int[] arr = ArrayUtil.generateRandomArray(maxSize, maxValue);
        ArrayUtil.printArray(arr);
        bubbleSort(arr);
        ArrayUtil.printArray(arr);
    }
}
