package two_pointers;

/**
 * 27. 移除元素
 * 给你一个数组 nums 和一个值 val，你需要 原地 移除所有数值等于 val 的元素，并返回移除后数组的新长度。
 * 不要使用额外的数组空间，你必须仅使用 O(1) 额外空间并 原地 修改输入数组。
 * 元素的顺序可以改变。你不需要考虑数组中超出新长度后面的元素。
 *
 * Example 1:
 * Input: nums = [3,2,2,3], val = 3
 * Output: 2, nums = [2,2,_,_]
 * Explanation: Your function should return k = 2, with the first two elements of nums being 2.
 * It does not matter what you leave beyond the returned k (hence they are underscores).
 */
public class Problem_0027_RemoveElement {

    public int removeElement(int[] nums, int val) {
        if (nums==null || nums.length==0) return 0;
        int n=nums.length;
        int index=0;
        for (int i = 0; i < n; i++) {
            if (nums[i]!=val) {
                nums[index++] = nums[i];
            }
        }
        return index;
    }

}
