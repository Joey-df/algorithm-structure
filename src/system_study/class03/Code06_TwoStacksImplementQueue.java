package system_study.class03;

import java.util.Stack;

// 使用两个栈实现队列
// leetcode 232
// https://leetcode.com/problems/implement-queue-using-stacks
public class Code06_TwoStacksImplementQueue {

    public static class MyQueue {

        private final Stack<Integer> s1; //push
        private final Stack<Integer> s2; //pop

        /** Initialize your data structure here. */
        public MyQueue() {
            s1 = new Stack<>();
            s2 = new Stack<>();
        }

        //help函数
        //s2为空时才能倒，要倒务必一次性倒完
        private void pushToPop() {
            if (s2.isEmpty()) {
                while (!s1.isEmpty()) {
                    s2.push(s1.pop());
                }
            }
        }

        /** Push element x to the back of queue. */
        public void push(int x) {
            s1.push(x);
            pushToPop();
        }

        /** Removes the element from in front of queue and returns that element. */
        public int pop() {
            if (s1.isEmpty() && s2.isEmpty()) {
                throw new RuntimeException("no element");
            }
            pushToPop();
            return s2.pop();
        }

        /** Get the front element. */
        public int peek() {
            if (s1.isEmpty() && s2.isEmpty()) {
                throw new RuntimeException("no element");
            }
            pushToPop();
            return s2.peek();
        }

        /** Returns whether the queue is empty. */
        public boolean empty() {
            pushToPop();
            return s2.isEmpty();
        }
    }

}
