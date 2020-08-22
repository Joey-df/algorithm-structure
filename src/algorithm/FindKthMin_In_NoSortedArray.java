package algorithm;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 在无序数组中找出第k小的数
 */
public class FindKthMin_In_NoSortedArray {

    //方法一：改写快排，时间复杂度O(N)
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

    //方法二：使用大根堆  堆的大小为k  返回堆顶的元素，就是第k小的
    //在无序数组中找第k小的数
    //复杂度o(N*logN)
    public static int method2(int arr[], int k) {
        PriorityQueue<Integer> heap = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2-o1; //降序
            }
        });
        for (int i = 0; i < k; i++) {
            heap.add(arr[i]);
        }
        for (int i = k; i < arr.length; i++) {
            if (heap.peek() > arr[i]) { //如果新进来的元素 比 堆顶元素小的话 进行处理
                heap.poll(); //移除堆顶元素
                heap.add(arr[i]);
            }
        }
        return heap.peek();
    }

    public static void main(String[] args) {
        int arr[] = {7, 2, 3, 100, 5, 99, 0, 3, 4, 6, 5};
        int k = 5;
        System.out.println(findKth(arr, 0, arr.length - 1, k - 1));
        System.out.println(method2(arr, k));
    }
}
