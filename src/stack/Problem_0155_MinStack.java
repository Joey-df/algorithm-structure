package stack;

import java.util.Stack;

/**
 * 155. 最小栈
 * 设计一个支持 push ，pop ，top 操作，并能在常数时间内检索到最小元素的栈。
 * push(x) —— 将元素 x 推入栈中。
 * pop() —— 删除栈顶的元素。
 * top() —— 获取栈顶元素。
 * getMin() —— 检索栈中的最小元素。
 */
public class Problem_0155_MinStack {
    Stack<Integer> normal;
    Stack<Integer> minStack;

    /**
     * initialize your data structure here.
     */
    public Problem_0155_MinStack() {
        normal = new Stack<>();
        minStack = new Stack<>();
    }

    // 添加的时候同步加，minStack每次都加当前的最小值
    public void push(int x) {
        normal.push(x);
        if (minStack.isEmpty()) {
            minStack.push(x);
        } else {
//            minStack.push(Math.min(minStack.peek(), x)); //不优化空间的写法
            //空间优化的写法
            if(x <= minStack.peek()) {   // 这里改成 >= 就变成最大栈
                minStack.push(x);
            }
        }
    }

    public void pop() {
        if (normal.isEmpty()) {
            return;
        }
//        normal.pop();         //不优化空间的写法
//        minStack.pop();       //不优化空间的写法
        //normal栈先弹出一个，用popVal保存
        //如果minStack的栈顶 == popVal时，minStack也弹出一个
        int popVal = normal.pop();
        if (minStack.peek()==popVal) {
            minStack.pop();
        }
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
