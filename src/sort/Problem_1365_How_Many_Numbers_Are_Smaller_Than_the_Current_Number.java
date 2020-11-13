package sort;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Given the array nums, for each nums[i] find out how many numbers in the array are smaller than it.
 * That is, for each nums[i] you have to count the number of valid j's such that j != i and nums[j] < nums[i].
 * <p>
 * Return the answer in an array.
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [8,1,2,2,3]
 * Output: [4,0,1,1,3]
 * Explanation:
 * For nums[0]=8 there exist four smaller numbers than it (1, 2, 2 and 3).
 * For nums[1]=1 does not exist any smaller number than it.
 * For nums[2]=2 there exist one smaller number than it (1).
 * For nums[3]=2 there exist one smaller number than it (1).
 * For nums[4]=3 there exist three smaller numbers than it (1, 2 and 2).
 * Example 2:
 * <p>
 * Input: nums = [6,5,4,8]
 * Output: [2,1,0,3]
 * Example 3:
 * <p>
 * Input: nums = [7,7,7,7]
 * Output: [0,0,0,0]
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 2 <= nums.length <= 500
 * 0 <= nums[i] <= 100
 */
public class Problem_1365_How_Many_Numbers_Are_Smaller_Than_the_Current_Number {

    private static class Node {
        int value;
        int index;
        int countOfSmallerAfterSelf;

        public Node(int value, int index) {
            this.value = value;
            this.index = index;
            this.countOfSmallerAfterSelf = 0;
        }
    }

    public static int[] smallerNumbersThanCurrent(int[] nums) {
        if (nums == null || nums.length == 0) {
            return new int[]{};
        }

        Node[] wrapNums = new Node[nums.length];
        for (int i = 0; i < nums.length; i++) {
            wrapNums[i] = new Node(nums[i], i);
        }
        //先按value降序排序
        Arrays.sort(wrapNums, new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                return o2.value - o1.value;
            }
        });

        //接下来对wrapNums进行归并排序(小->大)，排序过程中压榨出右边小于自己的个数
        sort(wrapNums, 0, wrapNums.length - 1);
        //至此 wrapNums中的原始是按照value排好序的，需要还原成原始nums中的顺序
        Arrays.sort(wrapNums, new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                return o1.index - o2.index;
            }
        });
        int[] ans = new int[nums.length];
        for (int i = 0; i < wrapNums.length; i++) {
            ans[i] = wrapNums[i].countOfSmallerAfterSelf;
            //System.out.print(ans[i] + " ");
        }
        return ans;
    }

    //对wrapNums[L,R]范围上进行归并排序
    private static void sort(Node[] wrapNums, int L, int R) {
        if (L >= R) return;
        //L>R
        int mid = L + ((R - L) >> 1);
        sort(wrapNums, L, mid);
        sort(wrapNums, mid + 1, R);
        merge(wrapNums, L, mid, R);
    }

    //对wrapNums[L,R]范围上进行merge
    //wrapNums[L,mid]，wrapNums[mid+1,R]范围上均已经有序了
    private static void merge(Node[] wrapNums, int l, int mid, int r) {
        Node[] help = new Node[r - l + 1];
        int idx = r - l;//help专用 初始指向最后一个位置
        //从右往左遍历，左边>右边的时候产生答案
        int p1 = mid, p2 = r;
        while (p1 >= l && p2 >= mid + 1) {
            if (wrapNums[p1].value > wrapNums[p2].value) { //左边大先拷贝左边的
                help[idx--] = wrapNums[p1];
                wrapNums[p1--].countOfSmallerAfterSelf += (p2 - mid);
            } else { //右边>=左边
                help[idx--] = wrapNums[p2--];
            }
        }
        while (p1 >= l) {
            help[idx--] = wrapNums[p1--];
        }

        while (p2 >= mid + 1) {
            help[idx--] = wrapNums[p2--];
        }

        for (int i = 0; i < help.length; i++) { //刷回去
            wrapNums[i + l] = help[i];
        }
    }

    public static void main(String[] args) {
        System.out.println(smallerNumbersThanCurrent(new int[]{8, 1, 2, 2, 3}));
    }
}
