package basic;

import java.util.Stack;

public class TwoStacksImplementQueue {

    private static class MyQueue {

        private Stack<Integer> push; //加数据
        private Stack<Integer> pop; //拿数据

        /** Initialize your data structure here. */
        public MyQueue() {
            push = new Stack<>();
            pop = new Stack<>();
        }

        /** Push element x to the back of queue. */
        public void push(int x) {
            pushToPop();
            push.push(x);
        }

        /** Removes the element from in front of queue and returns that element. */
        public int pop() {
            pushToPop();
            if (pop.isEmpty()) {
                throw new RuntimeException("empty!!!!");
            }
            return pop.pop();
        }

        /** Get the front element. */
        public int peek() {
            pushToPop();
            if (pop.isEmpty()) {
                throw new RuntimeException("empty!!!!");
            }
            return pop.peek();
        }

        /** Returns whether the queue is empty. */
        public boolean empty() {
            pushToPop();
            return pop.isEmpty();
        }

        //条件：
        //1、pop必须为空
        //2、push to pop必须一次性倒完
        private void pushToPop() {
            if (!pop.isEmpty()) return;
            while (!push.isEmpty()) {
                pop.push(push.pop());
            }
        }
    }
}
