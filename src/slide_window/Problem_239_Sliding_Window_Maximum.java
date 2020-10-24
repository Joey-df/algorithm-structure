package slide_window;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * You are given an array of integers nums,
 * there is a sliding window of size k which is moving from the very left of the array to the very right.
 * You can only see the k numbers in the window. Each time the sliding window moves right by one position.
 * <p>
 * Return the max sliding window.
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [1,3,-1,-3,5,3,6,7], k = 3
 * Output: [3,3,5,5,6,7]
 * Explanation:
 * Window position                Max
 * ---------------               -----
 * [1  3  -1] -3  5  3  6  7       3
 * 1 [3  -1  -3] 5  3  6  7       3
 * 1  3 [-1  -3  5] 3  6  7       5
 * 1  3  -1 [-3  5  3] 6  7       5
 * 1  3  -1  -3 [5  3  6] 7       6
 * 1  3  -1  -3  5 [3  6  7]      7
 * 窗口内最大值的更新结构：
 * 单调双端队列，从左往右依次从大到小，
 * R++时，元素从尾部进(如果尾部的元素 < 当前要进入的元素，则从尾部弹出)、
 * L++时，从头部出(如果头部元素正好为L位置的元素的话)
 * 队列头部元素即为窗口内最大值
 * <p>
 * 假设一个固定大小为W的窗口，依次划过arr，
 * 返回每一次窗口内的最大值
 * 例如，arr = [4,3,5,4,3,3,6,7], W = 3
 * 返回：[5,5,5,4,6,7]
 */
public class Problem_239_Sliding_Window_Maximum {

    //nums = [1,3,-1,-3,5,3,6,7], k = 3
    public static int[] process(int[] nums, int k) {
        int L = 0, R = 0;
        LinkedList<Integer> dQueue = new LinkedList<>(); //严格大-->小
        int[] ans = new int[nums.length - k + 1];
        int index = 0;
        //[L,R) 初始为[0,0) 即窗口内一个元素也没有
        while (R < nums.length) {
            //元素从尾部进入双端队列
            while (!dQueue.isEmpty() && nums[dQueue.peekLast()] <= nums[R]) { //R位置的元素要进窗口
                dQueue.pollLast();
            }
            //while退出来的状态为 队列为空，或者队尾元素大于 > nums[R]
            dQueue.offerLast(R);
            if (R - L == k - 1) { //先让窗口涨到k的长度
                ans[index++] = nums[dQueue.peekFirst()];
                if (dQueue.peekFirst() == L) dQueue.pollFirst();
                L++;
            }
            R++;
        }

        return ans;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{7, 2, 4};
        int k = 2;
        System.out.println(process(nums, k));
    }
}
