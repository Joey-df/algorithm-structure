package system_study.class03;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

//双向链表实现栈和队列
public class Code03_DoubleEndsQueueToStackAndQueue {

    public static class Node<T> {
        public T value;
        public Node<T> last;
        public Node<T> next;

        public Node(T data) {
            value = data;
            last = null;
            next = null;
        }
    }

    // 双端队列
    public static class DoubleEndsQueue<T> {
        private Node<T> head;
        private Node<T> tail;
        private int size;
        
        public DoubleEndsQueue() {
            head = null;
            tail = null;
            size = 0;
        }

        public boolean isEmpty() {
            return head == null || size == 0;
        }

        public int size() {
            return size;
        }

        public void addFromHead(T value) {
            Node<T> cur = new Node<T>(value);
            if (head == null) {
                head = cur;
                tail = cur;
            } else {
                cur.next = head;
                head.last = cur;
                head = cur;
            }
            size++;
        }

        public void addFromTail(T value) {
            Node<T> cur = new Node<T>(value);
            if (head == null) {
                head = cur;
                tail = cur;
            } else {
                tail.next = cur;
                cur.last = tail;
                tail = cur;
            }
            size++;
        }

        public T popFromHead() {
            if (head == null) {
                return null;
            }
            size--;
            Node<T> cur = head;
            if (head == tail) {
                head = null;
                tail = null;
            } else {
                head = head.next;
                head.last = null;
                cur.next = null;
            }
            return cur.value;
        }

        public T popFromTail() {
            if (head == null) {
                return null;
            }
            size--;
            Node<T> cur = tail;
            if (head == tail) {
                head = null;
                tail = null;
            } else {
                tail = tail.last;
                tail.next = null;
                cur.last = null;
            }
            return cur.value;
        }

        public T peekHead() {
            if (head == null) {
                return null;
            }
            return head.value;
        }

        public T peekTail() {
            T ans = null;
            if (tail != null) {
                ans = tail.value;
            }
            return ans;
        }

    }

    public static class MyStack<T> {
        private DoubleEndsQueue<T> queue;

        public MyStack() {
            queue = new DoubleEndsQueue<T>();
        }

        public void push(T value) {
            queue.addFromHead(value);
        }

        public T pop() {
            return queue.popFromHead();
        }

        public boolean isEmpty() {
            return queue.isEmpty();
        }

        public int size() {
            return queue.size();
        }

    }

    public static class MyQueue<T> {
        private DoubleEndsQueue<T> queue;

        public MyQueue() {
            queue = new DoubleEndsQueue<T>();
        }

        public void push(T value) {
            queue.addFromHead(value);
        }

        public T poll() {
            return queue.popFromTail();
        }

        public boolean isEmpty() {
            return queue.isEmpty();
        }

        public int size() {
            return queue.size();
        }

    }

    public static boolean isEqual(Integer o1, Integer o2) {
        if (o1 == null && o2 == null) { // 都空
            return true;
        }
        if (o1 == null ^ o2 == null) { // 一个空，一个不空
            return false;
        }
        return o1.equals(o2);
    }

    public static void main(String[] args) {
        int testTime = 100;
        int value = 10000;
        int testTimes = 100000;
        for (int i = 0; i < testTimes; i++) {
            MyStack<Integer> myStack = new MyStack<>();
            MyQueue<Integer> myQueue = new MyQueue<>();
            Stack<Integer> stack = new Stack<>();
            Queue<Integer> queue = new LinkedList<>();
            for (int j = 0; j < testTime; j++) {
                int nums = (int) (Math.random() * value);
                if (stack.isEmpty()) {
                    myStack.push(nums);
                    stack.push(nums);
                } else {
                    if (Math.random() < 0.5) {
                        myStack.push(nums);
                        stack.push(nums);
                    } else {
                        if (!isEqual(myStack.pop(), stack.pop())) {
                            System.out.println("oops!");
                        }
                    }
                }
                int numq = (int) (Math.random() * value);
                if (stack.isEmpty()) {
                    myQueue.push(numq);
                    queue.offer(numq);
                } else {
                    if (Math.random() < 0.5) {
                        myQueue.push(numq);
                        queue.offer(numq);
                    } else {
                        if (!isEqual(myQueue.poll(), queue.poll())) {
                            System.out.println("oops!");
                        }
                    }
                }
            }
            if (myQueue.size() != queue.size()) {
                System.out.println("Oops!");
            }
            if (myStack.size() != stack.size()) {
                System.out.println("Oops!");
            }
        }
        System.out.println("finish!");
    }
}
