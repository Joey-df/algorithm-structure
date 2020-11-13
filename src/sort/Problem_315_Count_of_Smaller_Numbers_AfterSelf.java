package sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * You are given an integer array nums and you have to return a new counts array.
 * The counts array has the property where counts[i] is the number of smaller elements to the right of nums[i].
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [5,2,6,1]
 * Output: [2,1,1,0]
 * Explanation:
 * To the right of 5 there are 2 smaller elements (2 and 1).
 * To the right of 2 there is only 1 smaller element (1).
 * To the right of 6 there is 1 smaller element (1).
 * To the right of 1 there is 0 smaller element.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 0 <= nums.length <= 10^5
 * -10^4 <= nums[i] <= 10^4
 * <p>
 * 这道题在leetcode高频题目全讲（二十三）讲的比较详细
 */
public class Problem_315_Count_of_Smaller_Numbers_AfterSelf {

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

    public static List<Integer> countSmaller(int[] nums) {
        Node[] wrapNums = new Node[nums.length];
        for (int i = 0; i < nums.length; i++) {
            wrapNums[i] = new Node(nums[i], i);
        }

        //接下来对wrapNums进行排序，排序过程中压榨出右边小于自己的个数
        sort(wrapNums, 0, wrapNums.length - 1);
        //至此 wrapNums中的原始是按照value排好序的，需要还原成原始nums中的顺序
        List<Integer> ans = new ArrayList<>();
        Arrays.sort(wrapNums, new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                return o1.index - o2.index;
            }
        });
        for (int i = 0; i < wrapNums.length; i++) {
            ans.add(wrapNums[i].countOfSmallerAfterSelf);
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
        System.out.println(countSmaller(new int[]{5, 2, 6, 1}));
    }
}
