package data_structure.stack_queue;

import java.util.LinkedList;

//225. Implement Stack using Queues
//只使用一个队列实现
public class Problem_0225_ImplementStackUsingQueues {
    private static class MyStack {
        private LinkedList<Integer> data;

        /**
         * Initialize your data structure here.
         */
        public MyStack() {
            data = new LinkedList<>();
        }

        /**
         * Push element x onto stack.
         */
        public void push(int x) {
            data.offerLast(x);
            for (int i = 0; i < data.size()-1; i++) {
                data.offerLast(data.pollFirst());
            }
        }

        /**
         * Removes the element on top of the stack and returns that element.
         */
        public int pop() {
            return data.pollFirst();
        }

        /**
         * Get the top element.
         */
        public int top() {
            if (empty()) {
                throw new RuntimeException("stack is empty!!!!");
            }
            return data.peekFirst();
        }

        /**
         * Returns whether the stack is empty.
         */
        public boolean empty() {
            return data.isEmpty();
        }
    }
}

