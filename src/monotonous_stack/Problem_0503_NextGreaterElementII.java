package monotonous_stack;

import java.util.Arrays;
import java.util.Stack;

/**
 * 503. 下一个更大元素II(环形数组)
 * 给定一个循环数组（最后一个元素的下一个元素是数组的第一个元素），输出每个元素的下一个更大元素。
 * 数字 x 的下一个更大的元素是按数组遍历顺序，这个数字之后的第一个比它更大的数，这意味着你应该循环地搜索它的下一个更大的数。
 * 如果不存在，则输出 -1。
 * <p>
 * 示例 1:
 * 输入: [1,2,1]
 * 输出: [2,-1,2]
 * 解释: 第一个 1 的下一个更大的数是 2；
 * 数字 2 找不到下一个更大的数；
 * 第二个 1 的下一个最大的数需要循环搜索，结果也是 2。
 * <p>
 * 注意: 输入数组的长度不会超过 10000。
 */
public class Problem_0503_NextGreaterElementII {

    //暴力解 O(n^2)
    public static int[] nextGreaterElements(int[] nums) {
        int n = nums.length;
        int[] ans = new int[n];
        Arrays.fill(ans, -1);
        for (int i = 0; i < n; i++) {
            for (int j = nextIndex(i, n); j != i; j = nextIndex(j, n)) {
                if (nums[j] > nums[i]) {
                    ans[i] = nums[j];
                    break;
                }
            }
        }
        return ans;
    }

    public static int nextIndex(int i, int size) {
        return i < size - 1 ? i + 1 : 0;
    }

    public static int lastIndex(int i, int size) {
        return i > 0 ? i - 1 : size-1;
    }


    //最优解 O(n)
    public static int[] nextGreaterElements2(int[] nums) {
        int n = nums.length;
        int[] ans = new int[n];
        Arrays.fill(ans, -1);
        //自底向上单调递减的栈
        Stack<Integer> stack = new Stack<>();
        //相等于，从左往右遍历两次数组
        for (int i = 0; i < (n<<1) ; i++) {
            int curi = i%n;
            //单调递减的栈突然遇到一个比栈顶大的元素，此时栈顶元素弹出+收集栈顶元素的答案，直到当前元素小于栈顶直接push进去
            while (!stack.isEmpty() && nums[stack.peek()] < nums[curi]) {
                ans[stack.pop()] = nums[curi];
            }
            stack.push(curi);
        }
        System.out.println("stack.size(): "+stack.size());
        //此时栈(从底到顶递减)中还剩余一些元素
        return ans;
    }





    public static void main(String[] args) {
        int[] arr = {5,14,3,12,1};
        int[] ans = nextGreaterElements2(arr);
        for (int i = 0; i < ans.length; i++) {
            System.out.print(ans[i] + " ");
        }
    }
}
