package greedy;

/**
 * 581. 最短不需要排序子数组长度
 * 给你一个整数数组 nums ，你需要找出一个 连续子数组 ，如果对这个子数组进行升序排序，那么整个数组都会变为升序排序。
 * 请你找出符合题意的 最短 子数组，并输出它的长度。
 *
 * 示例 1：
 * 输入：nums = [2,6,4,8,10,9,15]
 * 输出：5
 * 解释：你只需要对 [6, 4, 8, 10, 9] 进行升序排序，那么整个表都会变为升序排序。
 * 示例 2：
 * 输入：nums = [1,2,3,4]
 * 输出：0
 * 示例 3：
 * 输入：nums = [1]
 * 输出：0
 *
 * 提示：
 * 1 <= nums.length <= 10c4
 * -10^5 <= nums[i] <= 10^5
 *
 * 进阶：你可以设计一个时间复杂度为 O(n) 的解决方案吗？
 */
public class Problem_0581_ShortestUnsortedContinuousSubarray {

    //一趟找到右边界
    //一趟找到左边界
    public int findUnsortedSubarray(int[] nums) {
        if (nums==null || nums.length==0) return 0;
        int n = nums.length;
        int rightBound = -1;
        int leftBound = n-1;
        int leftMax = nums[0];
        //[2,6,4,8,10,9,15]
        for (int i = 1; i < n; i++) {
            if (nums[i] < leftMax) { //从左往右，出现下降趋势
                rightBound = i;
            } else {
                leftMax = nums[i];
            }
        }
        int rightMin = nums[n-1];
        for (int i = n-2; i >= 0; i--) {
            if (nums[i] > rightMin) { //从右往左，出现上升趋势
                leftBound = i;
            } else {
                rightMin = nums[i];
            }
        }

        return leftBound==n-1&&rightBound==-1? 0 : rightBound-leftBound+1;
    }

}
