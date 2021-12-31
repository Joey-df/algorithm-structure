package array;

/**
 * 169. 多数元素
 * 给定一个大小为 n 的数组，找到其中的多数元素。多数元素是指在数组中出现次数 大于 ⌊ n/2 ⌋ 的元素。
 * 你可以假设数组是非空的，并且给定的数组总是存在多数元素。
 *
 * 示例 1：
 * 输入：[3,2,3]
 * 输出：3
 * 示例 2：
 * 输入：[2,2,1,1,1,2,2]
 * 输出：2
 * 水王问题
 */
public class Problem_0169_MajorityElement {
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
