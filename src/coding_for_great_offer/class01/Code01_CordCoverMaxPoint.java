package coding_for_great_offer.class01;

import java.util.Arrays;

/**
 * 给定一个有序数组arr，代表坐落在X轴上的点
 * 给定一个正数K，代表绳子的长度
 * 返回绳子最多压中几个点？
 * 即使绳子边缘处盖住点也算盖住
 */
public class Code01_CordCoverMaxPoint {

    //具像化例子分析
    //[1,3,6,8,9]
    //K=4
    //方法一：
    //枚举以每一个位置arr[i]结束，能盖住几个点，取max
    //每一趟：在[0,i]范围上找>=arr[i]-K最左的位置l，i-l+1即为盖住点数
    //时间复杂度O(N*logN)
    public static int right(int[] arr, int k) {
        if (arr == null || arr.length == 0 || k < 0) return 0;
        int ans = 1;
        for (int i = 1; i < arr.length; i++) {
            int l = 0, r = i;
            while (l <= r) {
                int m = l + ((r - l) >> 1);
                if (arr[m] >= arr[i] - k) {
                    r = m - 1;
                } else {
                    l = m + 1;
                }
            }
            ans = Math.max(ans, i - l + 1);
        }
        return ans;
    }

    // 方法二：
    // 滑动窗口，枚举每一个开始的位置l，看r能扩大哪里，窗口大小k
    // 求整体max
    public static int process2(int[] arr, int k) {
        if (arr == null || arr.length == 0 || k < 0) return 0;
        int ans = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            int l = i;
            int r = i;
            while (r < arr.length && arr[r] - arr[l] <= k) {
                r++;
            }
            ans = Math.max(ans, r - l);
        }
        return ans;
    }

    public static int[] genArr(int len, int max) {
        if (len == 0) return new int[]{};
        int[] ans = new int[len];
        for (int i = 0; i < len; i++) {
            ans[i] = (int) (Math.random() * max) - (int) (Math.random() * max);
        }
        Arrays.sort(ans);
        return ans;
    }

    public static void print(int[] arr) {
        for (int value : arr) {
            System.out.print(value + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int length = 100;
        int max = 100;
        int k = (int) (Math.random() * max);
        int times = 5000000;
        int len = (int) (Math.random() * length);
        System.out.println("测试开始");
        for (int i = 0; i < times; i++) {
            int[] arr = genArr(len, max);
            int ans1 = right(arr, k);
            int ans2 = process2(arr, k);
            if (ans1 != ans2) {
                print(arr);
                System.out.println("出错了! k=" + k + "\t" + ans1 + " " + ans2);
                break;
            }
        }
        System.out.println("测试结束");
    }
}
