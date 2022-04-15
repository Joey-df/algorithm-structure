package slide_window;

import java.util.LinkedList;

/**
 * 239. 滑动窗口最大值
 * 给你一个整数数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。
 * 你只可以看到在滑动窗口内的 k 个数字。滑动窗口每次只向右移动一位。
 * 返回滑动窗口中的最大值。
 * 示例 1：
 * 输入：nums = [1,3,-1,-3,5,3,6,7], k = 3
 * 输出：[3,3,5,5,6,7]
 * 解释：
 * 滑动窗口的位置                最大值
 * ---------------               -----
 * [1  3  -1] -3  5  3  6  7       3
 *  1 [3  -1  -3] 5  3  6  7       3
 *  1  3 [-1  -3  5] 3  6  7       5
 *  1  3  -1 [-3  5  3] 6  7       5
 *  1  3  -1  -3 [5  3  6] 7       6
 *  1  3  -1  -3  5 [3  6  7]      7
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
public class Problem_0239_SlidingWindowMaximum {

    //nums = [1,3,-1,-3,5,3,6,7], k = 3
    public static int[] process(int[] nums, int k) {
        int L = 0, R = 0;
        LinkedList<Integer> dq = new LinkedList<>(); //严格大-->小
        int[] ans = new int[nums.length - k + 1];
        int index = 0;
        //[L,R) 初始为[0,0) 即窗口内一个元素也没有
        while (R < nums.length) {
            //元素从尾部进入双端队列
            while (!dq.isEmpty() && nums[dq.peekLast()] <= nums[R]) { //R位置的元素要进窗口
                dq.pollLast();
            }
            //while退出来的状态为 队列为空，或者队尾元素大于 > nums[R]
            dq.offerLast(R);
            if (R - L == k - 1) { //先让窗口涨到k的长度
                ans[index++] = nums[dq.peekFirst()];
                if (dq.peekFirst() == L) dq.pollFirst();
                L++; // 窗口形成之后，L才会和R同步往右动
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
