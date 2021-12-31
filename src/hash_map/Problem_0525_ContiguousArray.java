package hash_map;

import java.util.HashMap;

/**
 * 525. 含有相同数量0和1的最长子数组
 * 给定一个二进制数组 nums , 找到含有相同数量的 0 和 1 的最长连续子数组，并返回该子数组的长度。
 * <p>
 * 示例 1:
 * 输入: nums = [0,1]
 * 输出: 2
 * 说明: [0, 1] 是具有相同数量 0 和 1 的最长连续子数组。
 * 示例 2:
 * 输入: nums = [0,1,0]
 * 输出: 2
 * 说明: [0, 1] (或 [1, 0]) 是具有相同数量0和1的最长连续子数组。
 * <p>
 * 提示：
 * 1 <= nums.length <= 10^5
 * nums[i] 不是 0 就是 1
 */
//拓展附加题（与该题相同处理思想）
//给定数组arr，有正有负有0，包含1和2数量一样多称为子数组达标，求达标的子数组的最大长度是多少？
//解法：预处理，1变1，2变-1，其余全变成0，得到预处理之后的数组arr'，基于arr'求累加和为0的最长子数组，就是答案。
public class Problem_0525_ContiguousArray {

    //思路：
    //把0变-1，然后求累加和为0的最长子数组长度
    public int findMaxLength(int[] nums) {
        if (nums == null || nums.length < 2) return 0;
        for (int i = 0; i < nums.length; i++) {
            nums[i] = nums[i] == 0 ? -1 : nums[i];
        }
        //<累加和, 首次出现的位置>
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, -1); //important
        int sum = 0, ans = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];//以i位置结尾的累加和为sum，sum首次出现的位置到i位置的长度就是i位置的答案
            if (map.containsKey(sum)) {
                ans = Math.max(ans, i - map.get(sum));
            }
            if (!map.containsKey(sum)) {//只记录首次出现的位置
                map.put(sum, i);
            }
        }
        return ans;
    }

}
