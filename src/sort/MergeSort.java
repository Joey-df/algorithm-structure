package sort;

public class MergeSort {


    //递归解法
    //数组arr从 L...R 范围上进行归并排序
    public static void mergeSort(int arr[], int L, int R) {
        if (L == R) {//base case
            return;
        }
        int mid = L + ((R - L) >> 1); //注意：这里必须加括号
        mergeSort(arr, L, mid); //l...mid范围上进行归并排序
        mergeSort(arr, mid + 1, R);
        merge(arr, L, mid, R);
    }

    //arr[l,mid]有序，arr[mid+1,r]有序
    //将两段进行合并 让整体有序
    private static void merge(int[] arr, int l, int mid, int r) {
        int pL = l;//左半段初始指针
        int pR = mid + 1;//右半段初始指针
        int help[] = new int[r - l + 1];
        int i = 0;//专为help服务
        while (pL <= mid && pR <= r) {
            if (arr[pL] <= arr[pR]) {
                help[i++] = arr[pL++];//相等先拷贝左边的
            } else {
                help[i++] = arr[pR++];
            }
        }
        //有一边越界了
        while (pL <= mid) {
            help[i++] = arr[pL++];
        }
        while (pR <= r) {
            help[i++] = arr[pR++];
        }

        for (int j = 0; j < help.length; j++) { //将help数组刷回原数组arr[l...r]
            arr[l++] = help[j];
        }

    }

    //非递归方法
    public static void mergeSort2(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        int N = arr.length;
        int mergeSize = 1;// 当前有序的，左组长度
        while (mergeSize < N) { // log N
            int L = 0;
            // 0....
            while (L < N) {
                // L...M  左组（mergeSize）
                int M = L + mergeSize - 1;
                if (M >= N) {
                    break;
                }
                //  L...M   M+1...R(mergeSize)
                int R = Math.min(M + mergeSize, N - 1);
                merge(arr, L, M, R);
                L = R + 1;
            }
            if (mergeSize > N / 2) {//防止越界
                break;
            }
            mergeSize <<= 1;
        }
    }

    public static void main(String[] args) {
        int arr[] = {9, 3, 2, 0, -1};
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
        System.out.println("=================");
//        mergeSort(arr, 0, arr.length - 1);
        mergeSort2(arr);
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
    }
}
