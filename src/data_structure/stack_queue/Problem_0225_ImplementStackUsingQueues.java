package data_structure.stack_queue;

import java.util.LinkedList;
import java.util.Queue;

//225. Implement Stack using Queues
//只使用一个队列实现
public class Problem_0225_ImplementStackUsingQueues {
    private static class MyStack1 {
        private LinkedList<Integer> data;

        public MyStack1() {
            data = new LinkedList<>();
        }

        public void push(int x) {
            data.offerLast(x);
        }

        public int pop() {
            if (empty()) {
                throw new RuntimeException("stack is empty!!!!");
            }
            for (int i = 0; i < data.size()-1; i++) {
                data.offerLast(data.pollFirst());
            }
            return data.pollFirst();
        }

        public int top() {
            if (empty()) {
                throw new RuntimeException("stack is empty!!!!");
            }
            return data.peekLast();
        }

        public boolean empty() {
            return data.size() == 0;
        }
    }


    //方法2
    private static class MyStack2 {
        private Queue<Integer> data;

        public MyStack2() {
            data = new LinkedList<>();
        }

        public void push(int x) {
            data.offer(x); // addLast
            for (int i = 0; i < data.size()-1; i++) {
                data.offer(data.poll()); //从头弹出加到尾部
            }
        }

        public int pop() {
            if (empty()) {
                throw new RuntimeException("stack is empty!!!!");
            }
            return data.poll(); //从头部弹出
        }

        public int top() {
            if (empty()) {
                throw new RuntimeException("stack is empty!!!!");
            }
            return data.peek(); //查看头部元素
        }

        public boolean empty() {
            return data.size() == 0;
        }
    }
}

