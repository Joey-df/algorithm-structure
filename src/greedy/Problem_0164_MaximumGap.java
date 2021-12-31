package greedy;

/**
 * 164. 最大间距
 * 给定一个无序数组arr，返回如果排序之后，相邻数之间的最大差值
 * {3,1,7,9}，如果排序后{1,3,7,9}，相邻数之间的最大差值来自3和7，返回4
 * 要求：不能真的进行排序，并且要求在时间复杂度O(N)内解决
 */
// https://leetcode-cn.com/problems/maximum-gap/
public class Problem_0164_MaximumGap {

    public static int maximumGap(int[] nums) {
        if (nums == null || nums.length < 2) {
            return 0;
        }
        int len = nums.length;
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < len; i++) {
            min = Math.min(min, nums[i]);
            max = Math.max(max, nums[i]);
        }
        if (min == max) {
            return 0;
        }
        // 不止一种数，min~max一定有range,  len个数据，准备len+1个桶
        boolean[] hasNum = new boolean[len + 1]; // hasNum[i] i号桶是否进来过数字
        int[] maxs = new int[len + 1];  // maxs[i] i号桶收集的所有数字的最大值
        int[] mins = new int[len + 1];  // mins[i] i号桶收集的所有数字的最小值
        int bid = 0; // 桶号
        for (int i = 0; i < len; i++) {
            bid = bucket(nums[i], len, min, max);
            mins[bid] = hasNum[bid] ? Math.min(mins[bid], nums[i]) : nums[i];
            maxs[bid] = hasNum[bid] ? Math.max(maxs[bid], nums[i]) : nums[i];
            hasNum[bid] = true;
        }
        int res = 0;
        int lastMax = maxs[0]; // 上一个非空桶的最大值
        for (int i = 1; i <= len; i++) {
            if (hasNum[i]) {
                res = Math.max(res, mins[i] - lastMax);
                lastMax = maxs[i];
            }
        }
        return res;
    }

    // 如果当前的数是num，整个范围是min~max，分成了len + 1份
    // 返回num该进第几号桶
    public static int bucket(long num, long len, long min, long max) {
        return (int) ((num - min) * len / (max - min));
    }

}
