package system_study.class04;

import system_study.util.ArrayUtil;

/**
 * 在一个数组中，
 * 对于每个数num，求有多少个后面的数 * 2 依然<num，求总个数
 * 比如：[3,1,7,0,2]
 * 3的后面有：1，0
 * 1的后面有：0
 * 7的后面有：0，2
 * 0的后面没有
 * 2的后面没有
 * 所以总共有5个
 */
public class Code04_BiggerThanRightTwice {

    public static int biggerTwice(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        return process(arr, 0, arr.length - 1);
    }

    public static int process(int[] arr, int l, int r) {
        if (l == r) {
            return 0;
        }
        // l < r
        int mid = l + ((r - l) >> 1);
        return process(arr, l, mid) + process(arr, mid + 1, r) + merge(arr, l, mid, r);
    }

    public static int merge(int[] arr, int L, int m, int r) {
        // [L....M]   [M+1....R]

        int ans = 0;
        // 目前囊括进来的数，是从[M+1, windowR)
        int windowR = m + 1;
        for (int i = L; i <= m; i++) {
            while (windowR <= r && arr[i] > (arr[windowR] * 2)) {
                windowR++;
            }
            ans += windowR - m - 1;
        }


        int[] help = new int[r - L + 1];
        int i = 0;
        int p1 = L;
        int p2 = m + 1;
        while (p1 <= m && p2 <= r) {
            help[i++] = arr[p1] <= arr[p2] ? arr[p1++] : arr[p2++];
        }
        while (p1 <= m) {
            help[i++] = arr[p1++];
        }
        while (p2 <= r) {
            help[i++] = arr[p2++];
        }
        for (i = 0; i < help.length; i++) {
            arr[L + i] = help[i];
        }
        return ans;
    }

    // for test
    public static int comparator(int[] arr) {
        int ans = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] > (arr[j] << 1)) {
                    ans++;
                }
            }
        }
        return ans;
    }

    // for test
    public static void main(String[] args) {
        int testTime = 500000;
        int maxSize = 100;
        int maxValue = 100;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int[] arr1 = ArrayUtil.generateRandomArray(maxSize, maxValue);
            int[] arr2 = ArrayUtil.copyArray(arr1);
            if (biggerTwice(arr1) != comparator(arr2)) {
                System.out.println("Oops!");
                ArrayUtil.printArray(arr1);
                ArrayUtil.printArray(arr2);
                break;
            }
        }
        System.out.println("测试结束");
    }

}
