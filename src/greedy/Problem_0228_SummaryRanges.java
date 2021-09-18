package greedy;

import java.util.ArrayList;
import java.util.List;

/**
 * 228. 汇总区间
 * 给定一个无重复元素的有序整数数组 nums 。
 * 返回 恰好覆盖数组中所有数字 的 最小有序 区间范围列表。
 * 也就是说，nums 的每个元素都恰好被某个区间范围所覆盖，并且不存在属于某个范围但不属于 nums 的数字 x 。
 * 列表中的每个区间范围 [a,b] 应该按如下格式输出：
 * "a->b" ，如果 a != b
 * "a" ，如果 a == b
 *
 * 示例 1：
 * 输入：nums = [0,1,2,4,5,7]
 * 输出：["0->2","4->5","7"]
 * 解释：区间范围是：
 * [0,2] --> "0->2"
 * [4,5] --> "4->5"
 * [7,7] --> "7"
 *
 * 示例 2：
 * 输入：nums = [0,2,3,4,6,8,9]
 * 输出：["0","2->4","6","8->9"]
 * 解释：区间范围是：
 * [0,0] --> "0"
 * [2,4] --> "2->4"
 * [6,6] --> "6"
 * [8,9] --> "8->9"
 *
 * 示例 3：
 * 输入：nums = []
 * 输出：[]
 *
 * 示例 4：
 * 输入：nums = [-1]
 * 输出：["-1"]
 *
 * 示例 5：
 * 输入：nums = [0]
 * 输出：["0"]
 *
 * 提示：
 * 0 <= nums.length <= 20
 * -2^31 <= nums[i] <= 2^31 - 1
 * nums 中的所有值都 互不相同
 * nums 按升序排列
 */
//类似于56题的合并区间问题
public class Problem_0228_SummaryRanges {

    //nums: 无重复元素的有序整数数组
    public static List<String> summaryRanges(int[] nums) {
        List<String> ans = new ArrayList<>();
        if (nums==null || nums.length==0) return ans;
        List<int[]> res = new ArrayList<>();
        int n = nums.length;
        int s=nums[0];
        int e=nums[0];
        //[0,2,3,4,6,8,9]
        for (int i = 1; i < n; i++) {
            if (nums[i]==e+1) {
                e=nums[i];
            } else {
                res.add(new int[]{s,e});
                s = nums[i];
                e = nums[i];
            }
        }
        res.add(new int[]{s,e});

        for (int[] cur: res) {
            StringBuilder sb = new StringBuilder();
            sb.append(cur[0]);
            if (cur[0]!=cur[1])
                sb.append("->").append(cur[1]);
            ans.add(sb.toString());
        }
        return ans;
    }

    public static void main(String[] args) {
        List<String> list = summaryRanges(new int[]{0, 2, 3, 4, 6, 8, 9});
        System.out.println(list);
    }
}
