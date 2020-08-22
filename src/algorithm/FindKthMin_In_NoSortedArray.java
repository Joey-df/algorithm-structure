package algorithm;

/**
 * 在无序数组中找出第k小的数
 */
public class FindKthMin_In_NoSortedArray {

    //在arr[L...R]范围上找位于index位置的数
    //如果排序的话，不是真的排序
    //潜台词：index一定在L...R上
    public static int findKth(int[] arr, int L, int R, int index) {
        if (L == R) return arr[index];
        swap(arr, L + (int) Math.random() * (R - L + 1), R);//把最右边的基准数变成随机的
        int[] equals = partition(arr, L, R);
        if (index >= equals[0] && index <= equals[1]) {
            return arr[index];
        } else if (index < equals[0]) {
            return findKth(arr, L, equals[0] - 1, index);
        } else {
            return findKth(arr, equals[1] + 1, R, index);
        }
    }

    public static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }


    //在arr[L...R]范围上玩荷兰国旗问题 < = > 三个区域
    //以arr[R]作为基准值
    //返回 等于区的左右边界的下标
    public static int[] partition(int arr[], int L, int R) {
        int less = L - 1;
        int base = arr[R];
        int more = R + 1;
        int index = L;
        //index与more会师时停止
        while (index < more) {
            if (arr[index] == base) {
                index++;
            } else if (arr[index] < base) {
                swap(arr, index++, ++less);
            } else {
                swap(arr, index, --more);
            }
        }
        return new int[]{less + 1, more - 1};
    }

    public static void main(String[] args) {
        int arr[] = {7, 2, 3, 100, 5, 99, 0, 3, 4, 6, 5};
        int k = 9;
        System.out.println(findKth(arr, 0, arr.length - 1, k - 1));
    }
}
