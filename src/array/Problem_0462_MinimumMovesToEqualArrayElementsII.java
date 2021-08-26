package array;

/**
 * 462. 最少移动次数使数组元素相等 II
 * 给定一个非空整数数组，找到使所有数组元素相等所需的最小移动数，其中每次移动可将选定的一个元素加1或减1。
 * 您可以假设数组的长度最多为10000。
 * <p>
 * 例如:
 * 输入:
 * [1,2,3]
 * 输出:
 * 2
 * 说明：
 * 只有两个动作是必要的（记得每一步仅可使其中一个元素加1或减1）：
 * <p>
 * [1,2,3]  =>  [2,2,3]  =>  [2,2,2]
 */
//算法原型
//无序数组中找第k小的数
//好题
public class Problem_0462_MinimumMovesToEqualArrayElementsII {

    public static int minMoves2(int[] nums) {
        int n = nums.length;
        int l = 0;
        int r = n - 1;
        int m = l + ((r - l) >> 1); //找中位数
        int mid = find(nums, l, r, m);
        int ans = 0;
        for (int num : nums) {
            ans += Math.abs(num - mid);
        }
        return ans;
    }

    //无序数组arr[l,r]范围上
    //如果排序的话，位于index位置的元素，返回
    public static int find(int[] arr, int l, int r, int index) {
        while (l < r) {
            int base = arr[l + (int) (Math.random() * (r - l + 1))]; //用l=0，如r=9举例
            int[] range = partition(arr, l, r, base);
            if (index >= range[0] && index <= range[1]) {
                return arr[index];
            } else if (index < range[0]) {
                r = range[0] - 1;
            } else {
                //index > range[1]
                l = range[1] + 1;
            }
        }
        return arr[l];
    }

    //荷兰国旗划分问题
    public static int[] partition(int[] arr, int l, int r, int base) {
        int less = l - 1;
        int more = r + 1;
        int i = l;
        while (i < more) {
            if (arr[i] == base) {
                i++;
            } else if (arr[i] < base) {
                swap(arr, ++less, i++);
            } else {
                swap(arr, --more, i);
            }
        }
        return new int[]{less + 1, more - 1};
    }

    public static void swap(int[] arr, int i, int j) {
        int t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }

    public static void main(String[] args) {
        int[] nums = {1, 10, 2, 9};
        System.out.println(minMoves2(nums));
    }
}
