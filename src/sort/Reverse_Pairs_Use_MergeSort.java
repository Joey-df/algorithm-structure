package sort;

/**
 * 剑指 Offer 51. 数组中的逆序对
 * 给定一个数组，任意两数可组成一对，求一共有多少逆序对。逆序对的意思是（a, b）,a>b。
 * <p>
 * 本质问题：
 * 1、merge的过程中求一个数左边有多少个数比他大
 * 2、左边>右边时先拷贝右边，产生逆序对；否则(左边<=右边)先拷贝右边、不产生逆序对
 */
public class Reverse_Pairs_Use_MergeSort {

    //求arr[L,R]范围上有多少个逆序对，返回
    public static int reversePairs(int[] arr, int L, int R) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        if (L == R) {
            return 0;
        }
        //arr[L,R]上至少有两个数
        int mid = L + ((R - L) >> 1);
        int left = reversePairs(arr, L, mid);
        int right = reversePairs(arr, mid + 1, R);
        int merge = merge(arr, L, mid, R);
        return left + right + merge;
    }

    //arr[l,r] merge 的过程中产生的逆序对个数，返回
    //与此同时，arr[l,r]也整体变有序了
    private static int merge(int[] arr, int l, int mid, int r) {
        int p1 = l;
        int p2 = mid + 1;
        int[] help = new int[r - l + 1];
        int index = 0;
        int res = 0;
        while (p1 <= mid && p2 <= r) {
            if (arr[p1] > arr[p2]) {//右边的数严格小时，先拷贝右边 产生逆序对
                help[index++] = arr[p2++];
                res += (mid - p1 + 1);
            } else {
                help[index++] = arr[p1++];
            }
        }
        while (p1 <= mid) help[index++] = arr[p1++];
        while (p2 <= r) help[index++] = arr[p2++];
        for (int i = 0; i < help.length; i++) {
            arr[l + i] = help[i];
        }
        return res;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{4, 3, 3, 2, 1, 2, 2, 3, 9};
        System.out.println(reversePairs(arr, 0, arr.length - 1));
    }
}
