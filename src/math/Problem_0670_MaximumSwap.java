package math;

import java.util.HashMap;

/**
 * 670. 最大交换
 * 给定一个非负整数，你至多可以交换一次数字中的任意两位。返回你能得到的最大值。
 * <p>
 * 示例 1 :
 * <p>
 * 输入: 2736
 * 输出: 7236
 * 解释: 交换数字2和数字7。
 * 示例 2 :
 * <p>
 * 输入: 9973
 * 输出: 9973
 * 解释: 不需要交换。
 * 注意:
 * <p>
 * 给定数字的范围是 [0, 10^8]
 */
public class Problem_0670_MaximumSwap {

    public static int maximumSwap(int num) {
        int[] arr = numToArr(num);
        int n = arr.length;
        //<0～9的数字，最后出现的位置>
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            map.put(arr[i], i);
        }

        for (int i = 0; i < n; i++) {
            for (int digit = 9; digit > 0; digit--) {
                if (map.containsKey(digit)
                        && digit > arr[i] // 当前数字 > arr[i] && 当前数字出现在i右边，此时直接交换 返回答案
                        && map.get(digit) > i) {
                    swap(arr, i, map.get(digit));
                    return arrToNum(arr);
                }
            }
        }
        return arrToNum(arr);
    }

    public static int getLenOfNum(int num) {
        int len = 0;
        while (num != 0) {
            len++;
            num /= 10;
        }
        return len;
    }

    public static void swap(int[] arr, int i, int j) {
        int t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }

    public static int[] numToArr(int num) {
        int len = getLenOfNum(num);
        int[] arr = new int[len];
        // 数字转成数组（存放每一位上的数字）
        // 9297 -> [9,2,9,7]
        int n = arr.length;
        for (int i = 0; i < n; i++) {
            arr[n - i - 1] = num % 10;
            num = num / 10;
        }
        return arr;
    }

    //比如[1,2,3,4] -> 1234
    public static int arrToNum(int[] arr) {
        int ans = 0;
        for (int i = 0; i < arr.length; i++) {
            ans = ans * 10 + arr[i];
        }
        return ans;
    }


    public static void print(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        System.out.println(maximumSwap(990973));
    }

}
