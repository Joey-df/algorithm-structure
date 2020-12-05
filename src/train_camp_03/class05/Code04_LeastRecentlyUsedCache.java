package train_camp_03.class05;

import java.util.HashMap;
import java.util.Map;

/**
 * LRU内存替换算法的实现
 */
public class Code04_LeastRecentlyUsedCache {

    private static class Node {
        int key;
        int value;
        Node next;
        Node last;

        public Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    private static class DoubleLinkedList {
        Node head;
        Node tail;

        public DoubleLinkedList() {
            this.head = null;
            this.tail = null;
        }

        //添加元素到链表尾部(每次添加元素都加到尾部)
        public void addToTail(Node node) {
            if (node == null) {
                return;
            }
            if (head == null) {//第一个加入的元素
                head = node;
                tail = node;
            }
            tail.next = node;
            node.last = tail;
            tail = node;
        }

        //移动元素到链表尾部(包括移动链表头到尾部)
        //前提：node一定在链表中
        public void moveToTail(Node node) {
            if (node == tail) {
                return;
            }
            if (node == head) { //换头
                head = head.next;
                head.last = null;
                node.next = null;
                node.last = tail;
                tail.next = node;
                tail = node;
            } else {
                node.last.next = node.next;
                node.next.last = node.last;
                node.next = null;
                node.last = tail;
                tail.next = node;
                tail = node;
            }
        }

        //删除链表头部(链表容量满了，再添加元素时调用)
        //返回被删除的头节点
        public Node removeHead() {
            if (head == null) {
                return null;
            }
            Node res = head;
            head = head.next;
            head.last = null;
            res.next = null;
            return res;
        }
    }

    int capacity;
    Map<Integer, Node> map;
    DoubleLinkedList linkedList;

    public Code04_LeastRecentlyUsedCache(int capacity) {
        this.capacity = capacity;
        this.map = new HashMap<>();
        this.linkedList = new DoubleLinkedList();
    }

    //新增或者修改
    public void set(int key, int value) {
        if (map.containsKey(key)) { //修改
            Node node = map.get(key);
            node.value = value;
            linkedList.moveToTail(node);
        } else { //新增
            if (capacity == map.size()) {
                Node head = linkedList.removeHead();
                map.remove(head.key);
            }
            Node newNode = new Node(key, value);
            linkedList.addToTail(newNode);
            map.put(key, newNode);
        }
    }

    //-1表示没找到
    public int get(int key) {
        if (map.containsKey(key)) {
            Node node = map.get(key);
            linkedList.moveToTail(node);
            return node.value;
        }
        return -1;
    }
}
