package data_structure.stack_queue;

/**
 * 641. 设计循环双端队列
 * 设计实现双端队列。
 * 你的实现需要支持以下操作：
 * MyCircularDeque(k)：构造函数,双端队列的大小为k。
 * insertFront()：将一个元素添加到双端队列头部。 如果操作成功返回 true。
 * insertLast()：将一个元素添加到双端队列尾部。如果操作成功返回 true。
 * deleteFront()：从双端队列头部删除一个元素。 如果操作成功返回 true。
 * deleteLast()：从双端队列尾部删除一个元素。如果操作成功返回 true。
 * getFront()：从双端队列头部获得一个元素。如果双端队列为空，返回 -1。
 * getRear()：获得双端队列的最后一个元素。 如果双端队列为空，返回 -1。
 * isEmpty()：检查双端队列是否为空。
 * isFull()：检查双端队列是否满了。
 */
public class Problem_0641_DesignCircularDeque {

    class MyCircularDeque {
        int size;
        int k;
        DoubleListNode head;
        DoubleListNode tail;
        /** Initialize your data structure here. Set the size of the deque to be k. */
        public MyCircularDeque(int k) {
            head = new DoubleListNode(-1);
            tail = new DoubleListNode(-1);
            head.last = tail;
            tail.next = head;
            this.k = k;
            this.size = 0;
        }

        /** Adds an item at the front of Deque. Return true if the operation is successful. */
        public boolean insertFront(int value) {
            if (size == k) {
                return false;
            }
            DoubleListNode node = new DoubleListNode(value);
            node.next = head;
            node.last = head.last;
            head.last.next = node;
            head.last = node;
            size++;
            return true;
        }

        /** Adds an item at the rear of Deque. Return true if the operation is successful. */
        public boolean insertLast(int value) {
            if (size == k) {
                return false;
            }
            DoubleListNode node = new DoubleListNode(value);
            node.next = tail.next;
            tail.next.last = node;
            tail.next = node;
            node.last = tail;
            size++;
            return true;
        }

        /** Deletes an item from the front of Deque. Return true if the operation is successful. */
        public boolean deleteFront() {
            if (size == 0) {
                return false;
            }
            head.last.last.next = head;
            head.last = head.last.last;
            size--;
            return true;
        }

        /** Deletes an item from the rear of Deque. Return true if the operation is successful. */
        public boolean deleteLast() {
            if (size == 0) {
                return false;
            }
            tail.next.next.last = tail;
            tail.next = tail.next.next;
            size--;
            return true;
        }

        /** Get the front item from the deque. */
        public int getFront() {
            return head.last.val;
        }

        /** Get the last item from the deque. */
        public int getRear() {
            return tail.next.val;
        }

        /** Checks whether the circular deque is empty or not. */
        public boolean isEmpty() {
            return size == 0;
        }

        /** Checks whether the circular deque is full or not. */
        public boolean isFull() {
            return size == k;
        }
    }

    class DoubleListNode {
        DoubleListNode last;
        DoubleListNode next;
        int val;
        public DoubleListNode(int val) {
            this.val = val;
        }
    }

    /**
     * Your MyCircularDeque object will be instantiated and called as such:
     * MyCircularDeque obj = new MyCircularDeque(k);
     * boolean param_1 = obj.insertFront(value);
     * boolean param_2 = obj.insertLast(value);
     * boolean param_3 = obj.deleteFront();
     * boolean param_4 = obj.deleteLast();
     * int param_5 = obj.getFront();
     * int param_6 = obj.getRear();
     * boolean param_7 = obj.isEmpty();
     * boolean param_8 = obj.isFull();
     */

}
