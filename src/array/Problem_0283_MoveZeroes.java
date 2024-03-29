package array;

/**
 * 283. 移动零
 * 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
 * <p>
 * 示例:
 * 输入: [0,1,0,3,12]
 * 输出: [1,3,12,0,0]
 * <p>
 * 说明:
 * 必须在原数组上操作，不能拷贝额外的数组。
 * 尽量减少操作次数。
 */
public class Problem_0283_MoveZeroes {

    public void moveZeroes(int[] nums) {
        int p = 0;
        int i = 0;
        while (i < nums.length) {
            if (nums[i] != 0) {
                int t = nums[i];
                nums[i] = nums[p];
                nums[p++] = t;
            }
            i++;
        }
    }
}
