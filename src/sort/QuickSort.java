package sort;

/**
 * 随机快排+荷兰国旗技巧优化
 * 在arr[L..R]范围上，进行快速排序的过程：
 * 1）在这个范围上，随机选一个数记为num，
 * 1）用num对该范围做partition，< num的数在左部分，== num的数中间，>num的数在右部分。假设== num的数所在范围是[a,b]
 * 2）对arr[L..a-1]进行快速排序(递归)
 * 3）对arr[b+1..R]进行快速排序(递归)
 * 因为每一次partition都会搞定一批数的位置且不会再变动，所以排序能完成
 *
 * 随机快排的时间复杂度分析：
 * 1）通过分析知道，划分值越靠近中间，性能越好；越靠近两边，性能越差
 * 2）随机选一个数进行划分的目的就是让好情况和差情况都变成概率事件
 * 3）把每一种情况都列出来，会有每种情况下的时间复杂度，但概率都是1/N
 * 4）那么所有情况都考虑，时间复杂度就是这种概率模型下的长期期望！
 * 时间复杂度O(N*logN)，额外空间复杂度O(logN)都是这么来的。
 */
public class QuickSort {

    //交换arr中下标为i、j的两个数
    public static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    /**
     * 荷兰国旗问题
     * 给定一个数组arr，和一个整数num。请把小于num的数放在数组的左边，等于num的数放在中间，大于num的数放在数组的右边。
     * 要求额外空间复杂度O(1)，时间复杂度O(N)
     */
    //对数组arr在L...R范围上玩荷兰国旗切分
    //以arr[R]作为基准值做划分为 < = > 三个区
    //返回值：等于区的左右边界的下标
    public static int[] partition(int[] arr, int L, int R) {
        if (L > R) {
            return new int[]{-1, -1};
        }
        if (L == R) {
            return new int[]{L, R};
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
        return new int[]{less + 1, more - 1};
    }

    //对arr在L...R范围上排序
    public static void quickSort(int arr[], int L, int R) {
        if (arr == null || arr.length < 2) {
            return;
        }
        if (L < R) {
            swap(arr, L + (int) (Math.random() * (R - L + 1)), R); //随机快排：随机一个数与arr[R]交换
            int[] range = partition(arr, L, R);
            quickSort(arr, L, range[0] - 1);
            quickSort(arr, range[1] + 1, R);
        }
    }

    public static void main(String[] args) {
        int[] arr = {1, 6, 100, 2, 5, -1, 0, 2};
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
        System.out.println("--------------");
        quickSort(arr, 0, arr.length - 1);
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
    }

}
