package monotonous_stack;

import java.util.Stack;

//84. 柱状图中最大的矩形
//https://leetcode-cn.com/problems/largest-rectangle-in-histogram/
public class Problem_0084_LargestRectangleInHistogram {

    //方法1
    //思路
    //遍历求的数组中每个height[i]左边离自己最近比自己小的位置left，右边离自己最近比自己小的位置right
    //height[i]的答案为height[i]*(right-left-1)
    public static int largestRectangleArea(int[] heights) {
        if (heights == null || heights.length == 0) return 0;
        //自底向上单调递增的栈
        Stack<Integer> stack = new Stack<>(); //存下标
        int n = heights.length;
        // arr[i][0]: height[i]左边离自己最近比自己小的位置left，
        // arr[i][1]: 右边离自己最近比自己小的位置right
        int[][] arr = new int[n][2];
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && heights[stack.peek()] >= heights[i]) { //相等时候前面会算错一些答案，但是最后那个元素一定会算对
                int curi = stack.pop();
                arr[curi][1] = i;
                arr[curi][0] = !stack.isEmpty() ? stack.peek() : -1;
            }
            stack.push(i);
        }
        while (!stack.isEmpty()) {
            int curi = stack.pop();
            arr[curi][1] = n;
            arr[curi][0] = !stack.isEmpty() ? stack.peek() : -1;
        }
        int ans = 0;
        for (int i = 0; i < n; i++) {
            int l = arr[i][0];
            int r = arr[i][1];
            System.out.println(r + " " + heights[i] + " " + r + "    " + (r - l - 1) * heights[i]);
            ans = Math.max(ans, (r - l - 1) * heights[i]);
        }
        return ans;
    }


    //方法2：对方法1做了优化
    public static int largestRectangleArea2(int[] heights) {
        if (heights == null || heights.length == 0) return 0;
        //自底向上单调递增的栈
        Stack<Integer> stack = new Stack<>(); //存下标
        int n = heights.length;
        // l: height[i]左边离自己最近比自己小的位置l，
        // r: 右边离自己最近比自己小的位置r
        int ans = 0;
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && heights[stack.peek()] >= heights[i]) {
                int curi = stack.pop();
                int r = i;
                int l = !stack.isEmpty() ? stack.peek() : -1;
                ans = Math.max(ans, heights[curi] * (r-l-1));
            }
            stack.push(i);
        }
        while (!stack.isEmpty()) {
            int curi = stack.pop();
            int r = n;
            int l = !stack.isEmpty() ? stack.peek() : -1;
            ans = Math.max(ans, heights[curi] * (r-l-1));
        }
        return ans;
    }


    public static void main(String[] args) {
        int[] heights = {1,2,2,2,2,2,1};
        System.out.println(largestRectangleArea(heights));
        System.out.println(largestRectangleArea2(heights));
    }

}


//85题也是单调栈解