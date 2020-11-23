package array;

/**
 * Given an array nums of n integers where n > 1,  return an array output such that output[i] is equal to the product of all the elements of nums except nums[i].
 * <p>
 * Example:
 * <p>
 * Input:  [1,2,3,4]
 * Output: [24,12,8,6]
 * Constraint: It's guaranteed that the product of the elements of any prefix or suffix of the array (including the whole array) fits in a 32 bit integer.
 * <p>
 * Note: Please solve it without division and in O(n).
 * <p>
 * Follow up:
 * Could you solve it with constant space complexity? (The output array does not count as extra space for the purpose of space complexity analysis.)
 */
//数组中的每个值变为 除自己之外的所有元素的累乘积
public class Problem_238_Product_of_Array_Except_Self {

    public int[] productExceptSelf(int[] nums) {
        int zeros = 0;
        int productExceptZero = 1;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                zeros++;
            } else {
                productExceptZero *= nums[i];
            }
        }
        int[] ans = new int[nums.length];
        if (zeros == 0) {
            for (int i = 0; i < nums.length; i++) {
                ans[i] = productExceptZero / nums[i];
            }
        } else { //有零
            if (zeros == 1) {
                for (int i = 0; i < nums.length; i++) {
                    ans[i] = nums[i] == 0 ? productExceptZero : 0;
                }
            } else { //zeros>1
                for (int i = 0; i < nums.length; i++) {
                    ans[i] = 0;
                }
            }
        }
        return ans;
    }
}
