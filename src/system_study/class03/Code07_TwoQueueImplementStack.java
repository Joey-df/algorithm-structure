package system_study.class03;

import java.util.LinkedList;
import java.util.Queue;

//leetcode 225
//https://leetcode.com/problems/implement-stack-using-queues
//使用两个队列实现栈
public class Code07_TwoQueueImplementStack {

    public static class MyStack {

        private Queue<Integer> data;
        private Queue<Integer> help;

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
            if (data.isEmpty()) {
                throw new RuntimeException("no data");
            }
            int size = data.size();
            while (size-- > 1) {
                help.offer(data.poll());
            }
            int ans = data.poll();
            //data、help互换，每次都保证help为空
            Queue<Integer> tmp = data;
            data = help;
            help = tmp;
            return ans;
        }

        /**
         * Get the top element.
         */
        public int top() {
            int size = data.size();
            while (size-- > 1) {
                help.offer(data.poll());
            }
            int ans = data.poll();
            help.offer(ans);
            //data、help互换，每次都保证help为空
            Queue<Integer> tmp = data;
            data = help;
            help = tmp;
            return ans;
        }

        /**
         * Returns whether the stack is empty.
         */
        public boolean empty() {
            return data.isEmpty();
        }
    }

}
