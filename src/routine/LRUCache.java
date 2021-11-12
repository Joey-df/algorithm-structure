package routine;

import java.util.HashMap;
import java.util.Map;

//每天一遍LRU
public class LRUCache {

    //双向链表节点
    private static class Node {
        int key;
        int value;
        Node next;
        Node last;

        public Node(int k, int v) {
            this.key = k;
            this.value = v;
        }
    }

    private static class DoubleLinkedList {
        Node head;
        Node tail;

        public DoubleLinkedList() {
            head = null;
            tail = null;
        }

        public void addToTail(Node node) {
            if (node==null) return;
            if (head == null && tail == null) {
                head = node;
                tail = node;
            } else {
                tail.next = node;
                node.last = tail;
                tail = node;
            }
        }

        //前提：node必须在链表中
        public void moveToTail(Node node) {
            if (node==null) return;
            if (node == tail) return;
            if (node == head) {
                head = head.next;
                head.last = null;
                node.next = null;
                node.last = tail;
                tail.next = node;
                tail = node;
            } else {
                node.last.next = node.next;
                node.next.last = node.last;
                node.last = tail;
                tail.next = node;
                tail = node;
            }
        }

        public Node removeHead() {
            if (head == null) return null;
            if (head==tail) {
                Node res = head;
                head=null;
                tail=null;
                return res;
            }
            Node res = head;
            head = head.next;
            head.last = null;
            res.next = null;
            return res;
        }
    }

    HashMap<Integer, Node> map;
    DoubleLinkedList list;
    int capacity;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.map = new HashMap<>();
        this.list = new DoubleLinkedList();
    }

    public int get(int key) {
        if (map.containsKey(key)) {
            Node node = map.get(key);
            list.moveToTail(node);
            return node.value;
        }
        return -1;
    }

    public void put(int k, int v) {
        if (!map.containsKey(k)) {
            if (map.size() == this.capacity) {
                Node node = list.removeHead();
                map.remove(node.key);
            }
            Node node = new Node(k, v);
            map.put(k, node);
            list.addToTail(node);
        } else {
            Node node = map.get(k);
            node.value = v;
            list.moveToTail(node);
        }
    }
}
