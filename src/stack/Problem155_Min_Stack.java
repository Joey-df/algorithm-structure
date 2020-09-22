package stack;

import java.util.Stack;

/**
 * Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.
 * <p>
 * push(x) -- Push element x onto stack.
 * pop() -- Removes the element on top of the stack.
 * top() -- Get the top element.
 * getMin() -- Retrieve the minimum element in the stack.
 */
public class Problem155_Min_Stack {
    Stack<Integer> normal;
    Stack<Integer> minStack;

    /**
     * initialize your data structure here.
     */
    public Problem155_Min_Stack() {
        normal = new Stack<>();
        minStack = new Stack<>();
    }

    // 添加的时候同步加，minStack每次都加当前的最小值
    public void push(int x) {
        normal.push(x);
        if (minStack.isEmpty()) {
            minStack.push(x);
        } else {
            minStack.push(Math.min(minStack.peek(), x));
        }
    }

    public void pop() {
        if (normal.isEmpty()) {
            return;
        }
        normal.pop();
        minStack.pop();
    }

    public int top() {
        return normal.peek();
    }

    public int getMin() {
        if (minStack.isEmpty()) {
            return Integer.MAX_VALUE;
        }
        return minStack.peek();
    }
}
