package sort;

public class QuickSort {

    //交换arr中下标为i、j的两个数
    public static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    //对数组arr在L...R范围上玩荷兰国旗切分
    //以arr[R]作为基准值做划分为 < = > 三个区
    //返回值：等于区的左右边界的下标
    public static int[] partition(int[] arr, int L, int R) {
        if (L>R) {
            return new int[]{-1,-1};
        }
        if (L==R) {
            return new int[]{L,R};
        }
        int less = L - 1;
        int more = R + 1;
        int i = L;
        int base = arr[R];
        while (i < more) { //i与more会师 结束
            if (arr[i] == base) {
                i++; //arr[i] == base, 直接i++
            } else if (arr[i] < base) {
                //arr[i] < base时，arr[i]与小于区的下一个位置交换，小于区右扩一位，然后i++
                swap(arr, i++, ++less);
            } else {
                //arr[i] > base时，arr[i]与大于区的前一个位置交换，大于区左扩一位，i不动
                swap(arr, i, --more);
            }
        }
        return new int[]{less+1, more-1};
    }

    //对arr在L...R范围上排序
    public static void quickSort(int arr[], int L, int R) {
        if (arr == null || arr.length < 2) {
            return;
        }
        if (L<R) {
            int[] parts = partition(arr, L, R);
            quickSort(arr, L, parts[0]-1);
            quickSort(arr, parts[1]+1, R);
        }
    }

    public static void main(String[] args) {
        int[] arr = {1,6,2,5,-1,0,2};
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
        System.out.println("--------------");
        quickSort(arr, 0, arr.length-1);
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
    }

}
