package array;

import java.util.HashMap;
import java.util.Map;

/**
 * 697. 数组的度
 * 给定一个非空且只包含非负数的整数数组 nums，数组的度的定义是指数组里任一元素出现频数的最大值。
 * 你的任务是在 nums 中找到与 nums 拥有相同大小的度的最短连续子数组，返回其长度。
 *
 * 示例 1：
 * 输入：[1, 2, 2, 3, 1]
 * 输出：2
 * 解释：
 * 输入数组的度是2，因为元素1和2的出现频数最大，均为2.
 * 连续子数组里面拥有相同度的有如下所示:
 * [1, 2, 2, 3, 1], [1, 2, 2, 3], [2, 2, 3, 1], [1, 2, 2], [2, 2, 3], [2, 2]
 * 最短连续子数组[2, 2]的长度为2，所以返回2.
 * 示例 2：
 * 输入：[1,2,2,3,1,4,2]
 * 输出：6
 *
 * 提示：
 *
 * nums.length 在1到 50,000 区间范围内。
 * nums[i] 是一个在 0 到 49,999 范围内的整数。
 */
public class Problem_0697_DegreeOfAnArray {

    private static class Info {
        int count;
        int start;
        int end;

        public Info(int count, int start, int end) {
            this.count = count;
            this.start = start;
            this.end = end;
        }
    }

    //思路
    //1、遍历数组统计每个元素出现的次数、首次出现的位置，最后一次出现的位置（使用map）
    //2、根据上一步的统计得到数组的度（出现最多的次数）
    //3、遍历map抓答案
    public int findShortestSubArray(int[] nums) {
        if (nums==null || nums.length==0) {
            return 0;
        }
        Map<Integer, Info> map = new HashMap<>();
        int degree = 0; //数组的度
        int N = nums.length;
        for (int i = 0; i < N; i++) {
            if (!map.containsKey(nums[i])) {
                map.put(nums[i], new Info(1, i, i));
            } else {
                Info info = map.get(nums[i]);
                info.count++;
                info.end = i;
            }
            degree = Math.max(degree, map.get(nums[i]).count);
        }

        int ans = Integer.MAX_VALUE;
        for(int key: map.keySet()) {
            Info info = map.get(key);
            if (info.count == degree) {
                ans = Math.min(ans, info.end-info.start+1);
            }
        }
        return ans;
    }

}
