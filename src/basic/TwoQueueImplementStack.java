package basic;

import java.util.LinkedList;

public class TwoQueueImplementStack {
    private static class MyStack {
        private LinkedList<Integer> data;
        private LinkedList<Integer> help;

        /**
         * Initialize your data structure here.
         */
        public MyStack() {
            data = new LinkedList<>();
            help = new LinkedList<>();
        }

        /**
         * Push element x onto stack.
         */
        public void push(int x) {
            data.offer(x);
        }

        /**
         * Removes the element on top of the stack and returns that element.
         */
        public int pop() {
            if (empty()) {
                throw new RuntimeException("stack is empty!!!!");
            }
            while (data.size() > 1) {
                help.offerLast(data.pollFirst());
            }
            int ans = data.poll();
            LinkedList<Integer> tmp = data; //data、help互换，保证每次help都为空
            data = help;
            help = tmp;
            return ans;
        }

        /**
         * Get the top element.
         */
        public int top() {
            if (empty()) {
                throw new RuntimeException("stack is empty!!!!");
            }
            return data.peekLast();
        }

        /**
         * Returns whether the stack is empty.
         */
        public boolean empty() {
            return data.isEmpty();
        }
    }
}

