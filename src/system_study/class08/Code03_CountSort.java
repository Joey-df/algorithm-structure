package system_study.class08;


import system_study.util.ArrayUtil;

import java.util.Arrays;

/**
 * 不基于比较的排序
 * <p>
 * 桶排序思想下的排序：计数排序 & 基数排序
 * <p>
 * 1)桶排序思想下的排序都是不基于比较的排序
 * 2)时间复杂度为O(N)，额外空间负载度O(M)
 * 3)应用范围有限，需要样本的数据状况满足桶的划分
 * <p>
 * 总结
 * 1）一般来讲，计数排序要求，样本是整数，且范围比较窄
 * 2）一般来讲，基数排序要求，样本是10进制的正整数
 * 一旦要求稍有升级，改写代价增加是显而易见的
 */
public class Code03_CountSort {

    // only for 0~200 value
    public static void countSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            max = Math.max(max, arr[i]);
        }
        int[] bucket = new int[max + 1];
        for (int i = 0; i < arr.length; i++) {
            bucket[arr[i]]++;
        }
        int i = 0;
        for (int j = 0; j < bucket.length; j++) {
            while (bucket[j]-- > 0) {
                arr[i++] = j;
            }
        }
    }

    // for test
    public static void comparator(int[] arr) {
        Arrays.sort(arr);
    }

    // for test
    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) ((maxValue + 1) * Math.random());
        }
        return arr;
    }


    // for test
    public static void main(String[] args) {
        int testTime = 500000;
        int maxSize = 100;
        int maxValue = 150;
        boolean succeed = true;
        for (int i = 0; i < testTime; i++) {
            int[] arr1 = generateRandomArray(maxSize, maxValue);
            int[] arr2 = ArrayUtil.copyArray(arr1);
            countSort(arr1);
            comparator(arr2);
            if (!ArrayUtil.isEqual(arr1, arr2)) {
                succeed = false;
                ArrayUtil.printArray(arr1);
                ArrayUtil.printArray(arr2);
                break;
            }
        }
        System.out.println(succeed ? "Nice!" : "Fucking fucked!");

        int[] arr = generateRandomArray(maxSize, maxValue);
        ArrayUtil.printArray(arr);
        countSort(arr);
        ArrayUtil.printArray(arr);

    }

}
