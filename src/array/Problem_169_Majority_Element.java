package array;

/**
 * Given an array of size n, find the majority element. The majority element is the element that appears more than ⌊ n/2 ⌋ times.
 * <p>
 * You may assume that the array is non-empty and the majority element always exist in the array.
 * <p>
 * Example 1:
 * <p>
 * Input: [3,2,3]
 * Output: 3
 * Example 2:
 * <p>
 * Input: [2,2,1,1,1,2,2]
 * Output: 2
 * <p>
 * 水王问题
 */
public class Problem_169_Majority_Element {
    //建立一种一次删除两种不同数的机制
    //最后剩下来的数就是要找的数
    //前提：the array is non-empty and the majority element always exist in the array.
    public int majorityElement(int[] nums) {
        int candi = 0;
        int HP = 0;
        for (int i = 0; i < nums.length; i++) {
            if (HP == 0) { //血量为0时 建立靶子
                candi = nums[i];
                HP++;
            } else if (nums[i] == candi) {
                HP++;
            } else {
                HP--;
            }
        }
        return candi;
    }
}
