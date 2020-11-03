package sort;

/**
 * 在一个数组中，一个数左边比它小的数的总和，叫数的小和，所有数的小和累加起来，叫数组小和。求数组小和。
 * 例子： [1,3,4,2,5]
 * 1左边比1小的数：没有
 * 3左边比3小的数：1
 * 4左边比4小的数：1、3
 * 2左边比2小的数：1
 * 5左边比5小的数：1、3、4、 2
 * 所以数组的小和为1+1+3+1+1+3+4+2=16
 * <p>
 * 本质问题：
 * 1、merge的过程中求一个数右边有多少个数比他大
 * 2、左边 < 右边时，先拷贝左边，产生小和；否则先拷贝右边不产生小和
 */
public class Small_Sum_Use_MergeSort {

    //递归含义：
    //求arr[L,R]范围上的小和
    private static int smallSum(int[] arr, int L, int R) {
        if (L == R) {
            return 0;
        }
        //arr[L,R]至少有两个数
        int mid = L + ((R - L) >> 1);
        int left = smallSum(arr, L, mid); //左边归并排序产生的小和
        int right = smallSum(arr, mid + 1, R); //右边归并产生的小和
        int merge = merge(arr, L, mid, R); //整体归并排序产生的小和
        return left + right + merge;
    }

    //求arr[l,r]merge时产生的小和，返回
    //前提arr[l,mid]  arr[mid+1,r]均已经有序
    private static int merge(int[] arr, int l, int mid, int r) {
        int p1 = l;
        int p2 = mid + 1;
        int[] help = new int[r - l + 1];
        int index = 0;//专为help使用
        int res = 0;
        while (p1 <= mid && p2 <= r) {//p1 p2均不越界
            if (arr[p1] < arr[p2]) {
                //此时先拷贝左边的，产生小和
                res += arr[p1] * (r - p2 + 1);
                help[index++] = arr[p1++];
            } else {
                //左边<=右边时先拷贝右边，不产生小和
                help[index++] = arr[p2++];
            }
        }
        //左右一边必有其一越界
        while (p1 <= mid) {
            help[index++] = arr[p1++];
        }
        while (p2 <= r) {
            help[index++] = arr[p2++];
        }
        for (int i = 0; i < help.length; i++) {//将help刷回arr[l,r]
            arr[l + i] = help[i];
        }
        return res;
    }

    public static void main(String[] args) {
        int[] arr = {1, 3, 4, 2, 5};
        System.out.println(smallSum(arr, 0, arr.length - 1));
    }
}
