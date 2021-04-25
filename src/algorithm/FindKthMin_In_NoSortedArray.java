package algorithm;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 在无序数组中找出第k小的数
 * 或者
 * 在无序数组中找出最小的k个数
 */
public class FindKthMin_In_NoSortedArray {

    //方法一：改写快排，时间复杂度O(N)
    //在arr[L...R]范围上找位于index位置的数
    //如果排序的话，不是真的排序
    //潜台词：index一定在L...R上
    //递归方法
    //TODO 待补充迭代的方法
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

    //在无序数组中找出最小的k个数
    public static int[] findKthMinNums(int arr[], int k) {
        int kth = findKth(arr, 0, arr.length - 1, k - 1); //先找到第k小的数
        int[] ans = new int[k];
        int index = 0;
        //遍历一遍原数组，收集小于kth的数
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] < kth) {
                ans[i] = arr[i];
                index++;
            }
        }

        //遍历完了ans没填满的话，用kth补满
        if (index < ans.length) {
            for (int i = index; i < ans.length; i++) {
                ans[i] = kth;
            }
        }
        return ans;
    }

    //方法二：
    /**使用大根堆  堆的大小为k,放最小的k个数，堆顶的元素，就是第k小的数**/
    //在无序数组中找第k小的数
    //复杂度o(N*logN)
    public static int method2(int arr[], int k) {
        PriorityQueue<Integer> heap = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1; //降序
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
        int arr[] = {7, 2, 3, 100, 5, 99, 0, 9, 3, 4, 6, 5};
        // 12个数
        // 第1小  第12大
        // 第2小  第11大
        // 第12小 第1大
        int kthMax = 10; //第3大 同时也是第9小 //m
        int kthMin = arr.length+1-kthMax; //n  m+n=arr.length+1
        int k = kthMin;
        System.out.println(findKth(arr, 0, arr.length - 1, k - 1));
        int[] kthMinNums = findKthMinNums(arr, k);
        for (int i = 0; i < kthMinNums.length; i++) {
            System.out.print(kthMinNums[i] + " ");
        }
        System.out.println();
        System.out.println(method2(arr, k));
    }
}
