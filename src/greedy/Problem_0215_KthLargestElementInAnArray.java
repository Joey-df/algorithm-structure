package greedy;

/**
 * 215. 数组中的第K个最大元素
 * 给定整数数组 nums 和整数 k，请返回数组中第 k 个最大的元素。
 * 请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。
 *
 * 示例 1:
 * 输入: [3,2,1,5,6,4] 和 k = 2
 * 输出: 5
 *
 * 示例 2:
 * 输入: [3,2,3,1,2,4,5,5,6] 和 k = 4
 * 输出: 4
 *
 * 提示：
 * 1 <= k <= nums.length <= 10^4
 * -10^4 <= nums[i] <= 10^4
 */
public class Problem_0215_KthLargestElementInAnArray {

    // 如果排完序的话
    // 第k大，就是，位于n-k位置的数
    public static int findKthLargest(int[] nums, int k) {
        int n = nums.length;
        return find(nums, 0,n-1,n-k);
    }

    //无序数组中，找到位于index位置的元素，返回
    //潜台词：index一定在[l,r]范围上
    public static int find(int[] arr, int l , int r, int index) {
        while (l<r) {
            swap(arr, (l+(int)(Math.random()*(r-l+1))), r);
            int[] range = partition(arr, l, r);
            if (index >= range[0] && index <= range[1]) {
                return arr[index];
            } else if (index < range[0]) {
                r=range[0]-1;
            } else {
                l=range[1]+1;
            }
        }
        return arr[l];
    }

    //荷兰国旗划分
    public static int[] partition(int[] arr, int l, int r) {
        int less = l-1;
        int more = r+1;
        int base = arr[r];
        int i=l;
        while (i<more) {
            if (arr[i]==base) {
                i++;
            } else if (arr[i]<base) {
                swap(arr, i++, ++less);
            } else {
                swap(arr, i, --more);
            }
        }
        return new int[]{less+1, more-1};
    }

    public static void swap(int[] arr, int i, int j) {
        int t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }

    public static void main(String[] args) {
        int[] arr = {3,2,3,1,2,4,5,5,6};
        int k = 4;
        System.out.println(findKthLargest(arr, k));
    }

}
