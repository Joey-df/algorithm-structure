package data_structure.stack_queue;

import java.util.Stack;

/**
 * 155. 最小栈
 * 设计一个支持 push ，pop ，top 操作，并能在常数时间内检索到最小元素的栈。
 * push(x) —— 将元素 x 推入栈中。
 * pop() —— 删除栈顶的元素。
 * top() —— 获取栈顶元素。
 * getMin() —— 检索栈中的最小元素。
 */
//只用一个栈，使用Node封装每一步的value和min
public class Problem_0155_MinStack {

    public static class Node {
        int val;
        int min;
        public Node(int v, int m) {
            val = v;
            min = m;
        }
    }

    Stack<Node> stack;

    public Problem_0155_MinStack() {
        stack = new Stack<>();
    }

    public void push(int val) {
        if(stack.isEmpty()) {
            stack.push(new Node(val,val));
        } else {
            int min = Math.min(stack.peek().min, val);
            stack.push(new Node(val, min));
        }
    }

    public void pop() {
        if (!stack.isEmpty()) {
            stack.pop();
        }
    }

    public int top() {
        return stack.peek().val; //必须保证stack不为空
    }

    public int getMin() {
        return stack.peek().min;
    }
}
