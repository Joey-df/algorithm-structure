package system_study.class01;

import system_study.util.ArrayUtil;

import java.util.Arrays;

//选择排序
//过程：
//arr[0～N-1]范围上，找到最小值所在的位置，然后把最小值交换到0位置。
//arr[1～N-1]范围上，找到最小值所在的位置，然后把最小值交换到1位置。
//arr[2～N-1]范围上，找到最小值所在的位置，然后把最小值交换到2位置。
//…
//arr[N-1～N-1]范围上，找到最小值位置，然后把最小值交换到N-1位置。
//
//估算：
//很明显，如果arr长度为N，每一步常数操作的数量，如等差数列一般
//所以，总的常数操作数量 = a*(N^2) + b*N + c (a、b、c都是常数)
//
//所以选择排序的时间复杂度为O(N^2)。
public class Code01_SelectionSort {

    public static void selectionSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        // 0 ~ N-1  找到最小值，在哪，放到0位置上
        // 1 ~ n-1  找到最小值，在哪，放到1 位置上
        // 2 ~ n-1  找到最小值，在哪，放到2 位置上
        for (int i = 0; i < arr.length - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < arr.length; j++) { // i ~ N-1 上找最小值的下标
                minIndex = arr[j] < arr[minIndex] ? j : minIndex;
            }
            ArrayUtil.swap(arr, i, minIndex);
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
            selectionSort(arr1);
            comparator(arr2);
            if (!ArrayUtil.isEqual(arr1, arr2)) {
                succeed = false;
                ArrayUtil.printArray(arr1);
                ArrayUtil.printArray(arr2);
                break;
            }
        }
        System.out.println(succeed ? "Nice!" : "Fucking fucked!");

        int[] arr = ArrayUtil.generateRandomArray(maxSize, maxValue);
        ArrayUtil.printArray(arr);
        selectionSort(arr);
        ArrayUtil.printArray(arr);
    }
}
