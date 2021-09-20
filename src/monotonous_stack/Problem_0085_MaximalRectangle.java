package monotonous_stack;

import java.util.Stack;

//85. 矩阵中的最大矩形
//https://leetcode-cn.com/problems/maximal-rectangle/
public class Problem_0085_MaximalRectangle {

    public static int maximalRectangle(char[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return 0;
        int n = matrix.length;
        int m = matrix[0].length;
        int ans = 0;
        int[] cur = new int[m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                cur[j] = matrix[i][j] == '0' ? 0 : 1 + cur[j];
            }
            print(cur);
            ans = Math.max(ans, maxArea(cur));
        }
        return ans;
    }

    public static int maxArea(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int ans = 0;
        Stack<Integer> stack = new Stack<>();
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && nums[stack.peek()] >= nums[i]) {
                int curi = stack.pop();
                int rightLess = i;
                int leftLess = !stack.isEmpty() ? stack.peek() : -1;
                ans = Math.max(ans, (rightLess - leftLess - 1) * nums[curi]);
            }
            stack.push(i);
        }
        while (!stack.isEmpty()) {
            int curi = stack.pop();
            int r = n;
            int l = stack.isEmpty() ? -1 : stack.peek();
            ans = Math.max(ans, (r - l - 1) * nums[curi]);
        }
        return ans;
    }


    public static void print(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        char[][] matrix = new char[][]{
                {'1', '0', '1', '0', '0'},
                {'1', '0', '1', '1', '1'},
                {'1', '1', '1', '1', '1'},
                {'1', '0', '0', '1', '0'}
        };
        int max = maximalRectangle(matrix);
        System.out.println(max);
    }
}
