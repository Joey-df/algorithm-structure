package monotonous_stack;

import java.util.HashMap;
import java.util.Stack;

/**
 * 496. 下一个更大元素 I
 * 给你两个 没有重复元素 的数组 nums1 和 nums2 ，其中nums1 是 nums2 的子集。
 * 请你找出 nums1 中每个元素在 nums2 中的下一个比其大的值。
 * nums1 中数字 x 的下一个更大元素是指 x 在 nums2 中对应位置的右边的第一个比 x 大的元素。如果不存在，对应位置输出 -1 。
 *
 * 示例 1:
 * 输入: nums1 = [4,1,2], nums2 = [1,3,4,2].
 * 输出: [-1,3,-1]
 * 解释:
 *     对于 num1 中的数字 4 ，你无法在第二个数组中找到下一个更大的数字，因此输出 -1 。
 *     对于 num1 中的数字 1 ，第二个数组中数字1右边的下一个较大数字是 3 。
 *     对于 num1 中的数字 2 ，第二个数组中没有下一个更大的数字，因此输出 -1 。
 * 示例 2:
 * 输入: nums1 = [2,4], nums2 = [1,2,3,4].
 * 输出: [3,-1]
 * 解释:
 *     对于 num1 中的数字 2 ，第二个数组中的下一个较大数字是 3 。
 *     对于 num1 中的数字 4 ，第二个数组中没有下一个更大的数字，因此输出 -1 。
 *
 * 提示：
 *
 * 1 <= nums1.length <= nums2.length <= 1000
 * 0 <= nums1[i], nums2[i] <= 10^4
 * nums1和nums2中所有整数 互不相同
 * nums1 中的所有整数同样出现在 nums2 中
 *
 * 进阶：你可以设计一个时间复杂度为 O(nums1.length + nums2.length) 的解决方案吗？
 */
public class Problem_0496_NextGreaterElementI {

    //使用单调栈，求出nums2中每个nums[i]右边比自己大的元素
    //因为nums1是nums2的子集，所以遍历一边nums2，即得到nums1的答案
    //前提：nums1和nums2中所有整数 互不相同（无重复元素）
    public static int[] nextGreaterElement(int[] nums1, int[] nums2) {
        int n = nums2.length;
        int[] nextGreaterIndex = new int[n]; //存放nums[i]右边比自己大的元素的index
        Stack<Integer> stack = new Stack<>(); //存的是下标
        HashMap<Integer,Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            map.put(nums2[i], i);
            while (!stack.isEmpty() && nums2[stack.peek()] < nums2[i]) {
                nextGreaterIndex[stack.pop()] = i;
            }
            stack.push(i);
        }
        while (!stack.isEmpty()) {
            nextGreaterIndex[stack.pop()] = -1;
        }
        int m = nums1.length;
        int[] ans = new int[m];
        for (int i = 0; i < m; i++) {
            int index = map.get(nums1[i]);
            ans[i] = nextGreaterIndex[index]==-1 ? -1 : nums2[nextGreaterIndex[index]];
            //System.out.print(ans[i] + " ");
        }
        return ans;
    }

    public static void main(String[] args) {
        //[1,3,5,2,4]
        //[6,5,4,3,2,1,7]
        int[] nums1 = {1,3,5,2,4}, nums2 = {6,5,4,3,2,1,7};
        nextGreaterElement(nums1,nums2);
    }
}
