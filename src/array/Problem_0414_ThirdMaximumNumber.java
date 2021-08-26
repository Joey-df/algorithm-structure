package array;


import java.util.*;

/**
 * 414. 第三大的数
 * 给你一个非空数组，返回此数组中 第三大的数 。如果不存在，则返回数组中最大的数。
 *
 * 示例 1：
 * 输入：[3, 2, 1]
 * 输出：1
 * 解释：第三大的数是 1 。
 * 示例 2：
 * 输入：[1, 2]
 * 输出：2
 * 解释：第三大的数不存在, 所以返回最大的数 2 。
 * 示例 3：
 * 输入：[2, 2, 3, 1]
 * 输出：1
 * 解释：注意，要求返回第三大的数，是指在所有不同数字中排第三大的数。
 * 此例中存在两个值为 2 的数，它们都排第二。在所有不同数字中排第三大的数为 1 。
 *
 * 提示：
 * 1 <= nums.length <= 10^4
 * -2^31 <= nums[i] <= 2^31 - 1
 *
 * 进阶：你能设计一个时间复杂度 O(n) 的解决方案吗？
 */
//414. 第三大的数
//https://leetcode-cn.com/problems/third-maximum-number/
public class Problem_0414_ThirdMaximumNumber {

    //方法1：使用TreeSet
    public static int thirdMax(int[] nums) {
        TreeSet<Integer> set = new TreeSet<>(); //默认升序
        for (int num : nums) set.add(num);
        if (set.size()<3) return set.last(); //返回最大的
        for (int i = 0; i < 2; i++) {
            set.pollLast();
        }
        return set.last();
    }


    //方法2；使用小根堆
    //门槛堆：求第k大用小根堆，求第k小用大根堆
    public static int thirdMax2(int[] nums) {
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            if (!set.contains(nums[i])) {
                if (heap.isEmpty() || heap.size() < 3) {
                    set.add(nums[i]);
                    heap.offer(nums[i]);
                } else { //==3
                    if (nums[i] > heap.peek()) {
                        heap.poll();
                        heap.offer(nums[i]);
                        set.add(nums[i]);
                    }
                }
            }
        }

        if (heap.size() == 3) return heap.peek();
        //size < 3
        System.out.println(heap);
        int max = heap.peek();
        while (!heap.isEmpty()) {
            if (heap.size()>1) {
                heap.poll();
            }
            if (heap.size()==1) {
                max = heap.poll();
            }
        }
        return max;
    }


    public static void main(String[] args) {
        System.out.println(thirdMax2(new int[]{5,2,4,1,3,6,0}));
    }

}
