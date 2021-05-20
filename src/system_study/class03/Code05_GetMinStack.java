package system_study.class03;

import java.util.Stack;

/**
 * 实现一个特殊的栈，在基本功能的基础上，再实现返回栈中最小元素的功能
 * 1）pop、push、getMin操作的时间复杂度都是 O(1)。
 * 2）设计的栈类型可以使用现成的栈结构。
 */
//leetcode 115题 https://leetcode.com/problems/min-stack/
public class Code05_GetMinStack {

    public static class MinStack {

        private Stack<Integer> normal;
        private Stack<Integer> min;

        /**
         * initialize your data structure here.
         */
        public MinStack() {
            normal = new Stack<>();
            min = new Stack<>();
        }

        public void push(int val) {
            normal.push(val);
            if (min.isEmpty()) {
                min.push(val);
                return;
            }
            int data = Math.min(min.peek(), val);
            min.push(data);
        }

        public void pop() {
            normal.pop();
            min.pop();
        }

        public int top() {
            return normal.peek();
        }

        public int getMin() {
            return min.peek();
        }
    }
}
