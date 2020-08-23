package algorithm;

import java.util.PriorityQueue;

/**
 * Find the kth largest element in an unsorted array.
 * Note that it is the kth largest element in the sorted order, not the kth distinct element.
 *
 * Example 1:
 *
 * Input: [3,2,1,5,6,4] and k = 2
 * Output: 5
 * Example 2:
 *
 * Input: [3,2,3,1,2,4,5,5,6] and k = 4
 * Output: 4
 */
public class Kth_Largest_Element_in_an_Array {

    /**在无序数组中找第k大的数
      等价与
      在无序数组中找第N-k+1小的数
    **/
    public static int findKthLargest(int[] nums, int k) {
        // return process1(nums, 0, nums.length-1, nums.length-k);
        return heapWay(nums, k);
    }

    //在无序数组nums上找第k小的数
    //方法一：改写快排
    private static int process1(int[] nums, int L, int R, int k){
        if(L==R) return nums[k];
        swap(nums, L+(int)Math.random()*(R-L+1), R);
        int area[] = partition(nums, L, R);
        if(k>=area[0] && k<=area[1]) {
            return nums[k];
        } else if(k < area[0]) {
            return process1(nums, L, area[0]-1, k);
        }
        return process1(nums, area[1]+1, R, k);
    }

    //在nums[L...R]上玩荷兰国旗问题
    private static int[] partition(int[] nums, int L, int R) {
        if(L>R) return new int[]{-1,-1};
        if(L==R) return new int[]{L,R};
        int less = L-1;
        int more = R+1;
        int i = L;
        int base = nums[R];
        while(i < more) { //i 与 more会师时停止
            if(nums[i]==base) {
                i++;
            } else if(nums[i] < base) {
                swap(nums, i++, ++less);
            } else {
                swap(nums, i, --more);
            }
        }
        return new int[]{less+1, more-1};
    }

    private static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }


    //方法二：使用小根堆
    /**小根堆大小固定为k，放着最大的k个数，堆顶即为第k大的数**/
    public static int heapWay(int nums[], int k) {
        PriorityQueue<Integer> heap = new PriorityQueue<>();

        int i=0;
        for(; i < k; i++){
            heap.add(nums[i]);
        }

        for(;i < nums.length; i++) {
            if (heap.peek() < nums[i]) { //新来的数比堆顶大
                heap.poll();
                heap.add(nums[i]);
            }
        }

        return heap.peek();
    }


    public static void main(String[] args) {
        int arr[] = {7, 2, 3, 100, 5, 99, 0, 9, 3, 4, 6, 5};
        int k = 3;
        System.out.println("heapWay(arr, k) = " + heapWay(arr, k));
        System.out.println("process1(arr, 0, arr.length-1, arr.length-k) = " + process1(arr, 0, arr.length-1, arr.length-k));
    }
}
