package monotonous_stack;


import java.util.Stack;

/**
 * 739. 每日温度
 * 请根据每日 气温 列表 temperatures ，请计算在每一天需要等几天才会有更高的温度。
 * 如果气温在这之后都不会升高，请在该位置用 0 来代替。
 *
 * 示例 1:
 * 输入: temperatures = [73,74,75,71,69,72,76,73]
 * 输出: [1,1,4,2,1,1,0,0]
 *
 * 示例 2:
 * 输入: temperatures = [30,40,50,60]
 * 输出: [1,1,1,0]
 *
 * 示例 3:
 * 输入: temperatures = [30,60,90]
 * 输出: [1,1,0]
 */
//单调栈
public class Problem_0739_DailyTemperatures {

    //找nums[i]右边离自己最近比自己大的元素下标k
    //ans[i] = k==n ? 0 : k-i
    public static int[] dailyTemperatures(int[] nums) {
        if (nums==null || nums.length==0) return new int[0];
        int n = nums.length;
        int[] ans = new int[n];
        Stack<Integer> stack = new Stack<>(); //自底向上单调递减
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && nums[stack.peek()] < nums[i]) {
                int curi = stack.pop();
                ans[curi] = i-curi;
            }
            stack.push(i);
        }
        while (!stack.isEmpty()) { //可以省略，因为数组初始化就是0
            ans[stack.pop()] = 0;
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] arr = {73,74,75,71,69,72,76,73};
        dailyTemperatures(arr);
    }

}
