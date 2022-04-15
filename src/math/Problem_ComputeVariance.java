package math;

import java.text.DecimalFormat;

// 求给定数组的方差
public class Problem_ComputeVariance {

    public static double variance(int[] arr) {
        if (arr == null || arr.length == 0) return 0d;
        int n = arr.length;
        double sum = 0;
        for (int num : arr) sum += num;
        double avg = sum / n;
        double child = func(arr, avg, 0d, 0);
        return Math.sqrt(child / n);
    }

    // 固定参数 arr
    // arr的平均数，avg
    // [0....index-1]分子的累加和 -> pre
    // 当前来到index位置
    // 返回：[index...n-1]部分的分子累加和
    public static double func(int[] arr, double avg, double pre, int index) {
        if (index == arr.length) {
            return 0d;
        }
        return Math.pow(arr[index] - avg, 2) // 当前index位置的分子部分
                + func(arr, avg, pre + Math.pow(arr[index] - avg, 2), index + 1);
    }


    public static double variance2(int[] arr) {
        if (arr == null || arr.length == 0) return 0d;
        int n = arr.length;
        double sum = 0d;
        double powerSum = 0d; // 所有元素的平方和
        for (int num : arr) {
            sum += num;
            powerSum += Math.pow(num, 2);
        }
        double avg = sum / n;
        double child = powerSum
                + Math.pow(avg, 2) * n
                - 2 * avg * sum;
        return Math.sqrt(child / n);
    }

    // 为了测试
    public static int[] genArr(int maxVal, int maxLen) {
        int len = (int) (Math.random() * maxLen);
        if (len == 0) return new int[0];
        int[] ans = new int[len];
        for (int i = 0; i < len; i++) {
            ans[i] = (int) (Math.random() * maxVal);
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
        int maxVal = 10000;
        int maxLen = 100;
        int testTimes = 100000;
        DecimalFormat df = new DecimalFormat("#.##");
        System.out.println("测试开始");
        for (int i = 0; i < testTimes; i++) {
            int[] arr = genArr(maxVal, maxLen);
            double ans1 = variance(arr);
            double ans2 = variance2(arr);
            if (!df.format(ans1).equals(df.format(ans2))) {
                System.out.println("出错了");
                System.out.println("ans1: " + ans1);
                System.out.println("ans2: " + ans2);
                print(arr);
                break;
            }
        }
        System.out.println("测试结束");
    }
}
