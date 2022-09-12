package data_structure.stack_queue;

import java.util.Stack;

//232. Implement Queue using Stacks
public class Problem_0232_ImplementQueueUsingStacks {

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
            if (push.isEmpty() && pop.isEmpty()) {
                throw new RuntimeException("empty!!!!");
            }
            pushToPop();
            return pop.pop();
        }

        /** Get the front element. */
        public int peek() {
            if (push.isEmpty() && pop.isEmpty()) {
                throw new RuntimeException("empty!!!!");
            }
            pushToPop();
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
