package leetcode_top_interview_and_top100liked_questions;

/**
 * 238. 除自身以外数组的乘积
 * 给你一个长度为 n 的整数数组 nums，其中 n > 1，返回输出数组 output ，
 * 其中 output[i] 等于 nums 中除 nums[i] 之外其余各元素的乘积。
 * <p>
 * 示例:
 * 输入: [1,2,3,4]
 * 输出: [24,12,8,6]
 */
public class Problem_0238_ProductOfArrayExceptSelf {

    //分析
    //1、如果nums包含两个及以上的0，结果数组全0
    //2、如果nums包含一个0，则0的位置有值，其他位置全0
    //3、如果nums中无0，每个位置的值=所有元素累成积 / 当前位置元素
    public int[] productExceptSelf(int[] nums) {
        int zeros = 0;
        for (int n : nums) {
            if (n == 0) zeros++;
        }
        int[] ans = new int[nums.length];
        if (zeros > 1) {
            return ans;
        }
        if (zeros == 1) {
            int tmp = 1;
            int index = 0; //nums中0的下表
            for (int i = 0; i < nums.length; i++) {
                if (nums[i] != 0) {
                    tmp *= nums[i];
                } else {
                    index = i;
                }
            }
            ans[index] = tmp;
            return ans;
        }
        //无0
        int sum = 1;
        for (int i = 0; i < nums.length; i++) {
            sum *= nums[i];
        }
        for (int i = 0; i < ans.length; i++) {
            ans[i] = sum / nums[i];
        }
        return ans;
    }
}
