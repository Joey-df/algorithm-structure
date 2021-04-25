package routine;

import java.util.HashMap;
import java.util.Map;

//每天一遍LRU
public class LRUCache {

    //链表中的节点
    static class Node {
        int key;
        int value;
        Node next;
        Node last;

        public Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    //双向链表
    static class DoubleLinkedList {
        Node head;
        Node tail;

        //addToTail
        public void addToTail(Node node) {
            if (node==null) return;
            if (head == null && tail == null) { //第一个时
                head = node;
                tail = node;
            } else {
                node.last = tail;
                tail.next = node;
                tail = node;
            }
        }

        //moveToTail
        //前提：node在链表中；get或set的时候需要把node移到尾部，表示最近使用过
        public void moveToTail(Node node) {
            if (tail == node) { //本来就是tail，不用动
                return;
            }
            //node不是tail
            //有可能是head 或 非head
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
                node.next = null;
                tail.next = node;
                tail = node;
            }
        }

        //删头：容量满的时候，再添加节点 需要删头；返回被删除的head，在map中同步删除
        public Node delHead() {
            if (head == null) {
                return null;
            }
            if (head==tail) { //链表中只有一个点
                Node res = head;
                head =null;
                tail = null;
                return res;
            }
            //head不空 & 超过一个节点
            Node res = head;
            head = head.next;
            head.last = null;
            res.next = null;
            return res;
        }
    }

    //LRU的属性
    Map<Integer, Node> map;
    DoubleLinkedList list;
    int capacity;

    public LRUCache(int c) {
        this.capacity = c;
        this.map = new HashMap<>();
        this.list = new DoubleLinkedList();
    }

    public int get(int key) {
        if (!map.containsKey(key)) {
            return -1;//not contain
        }
        Node node = map.get(key); //node的内存地址
        list.moveToTail(node);
        return node.value;
    }

    public void put(int key, int value){
        if (map.containsKey(key)) { //修改
            Node node = map.get(key);
            node.value = value;
            this.list.moveToTail(node);
        } else { //新增
            Node node = new Node(key, value);
            if (map.size()==capacity) { //容量满了
                Node oldHead = this.list.delHead();
                map.remove(oldHead.key);
            }
            this.list.addToTail(node);
            map.put(key, node); //维护map
        }
    }
}
