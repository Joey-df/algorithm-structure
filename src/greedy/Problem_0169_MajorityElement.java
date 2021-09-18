package greedy;

/**
 * 169. 多数元素
 * 给定一个大小为 n 的数组，找到其中的多数元素。多数元素是指在数组中出现次数 大于 ⌊ n/2 ⌋ 的元素。
 * 你可以假设数组是非空的，并且给定的数组总是存在多数元素。
 *
 * 示例 1：
 * 输入：[3,2,3]
 * 输出：3
 *
 * 示例 2：
 * 输入：[2,2,1,1,1,2,2]
 * 输出：2
 *
 * 进阶：
 * 尝试设计时间复杂度为 O(n)、空间复杂度为 O(1) 的算法解决此问题。
 */
public class Problem_0169_MajorityElement {

    //超级水王问题
    //前提：数组非空，并且一定存在水王数
    public int majorityElement(int[] nums) {
        int cand=0;
        int HP=0; //HP=0代表没立靶子
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            if (HP==0) {
                cand = nums[i];
                HP=1;
            } else if (cand==nums[i]) {
                HP++;
            } else {
                HP--;
            }
        }
        return cand;
    }

}
