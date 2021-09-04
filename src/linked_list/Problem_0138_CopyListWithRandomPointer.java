package linked_list;

import java.util.HashMap;

/**
 * 一种特殊的单链表节点类描述如下
 * class Node {
 * int value;
 * Node next;
 * Node random;
 * Node(int val) { value = val; }
 * }
 * rand指针是单链表节点结构中新增的指针，rand可能指向链表中的任意一个节点，也可能指向null。
 * 给定一个由Node节点类型组成的无环单链表的头节点 head，请实现一个函数完成这个链表的复制，并返回复制的新链表的头节点。
 * 【要求】
 * 时间复杂度O(N)，额外空间复杂度O(1)
 */
public class Problem_0138_CopyListWithRandomPointer {

    // Definition for a Node.
    private static class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }

    public Node copyRandomList(Node head) {
        if (head == null) return head;
        // a->b->c->d->null
        Node next = null;
        Node cur = head;
        // link new nodes
        while (cur != null) {
            next = cur.next; // b
            Node node = new Node(cur.val); // a'
            cur.next = node;
            node.next = next;
            cur = next;
        }

        // a->a'->b->b'->c->c'->d->d'->null
        // link random
        cur = head;
        Node copy = null;
        while (cur != null) {
            next = cur.next.next;
            copy = cur.next;
            copy.random = (cur.random != null) ? cur.random.next : null;
            cur = next;
        }

        // split
        Node res = head.next;
        cur = head; //a
        while (cur != null) {
            next = cur.next.next; //b
            copy = cur.next;//a'
            cur.next = next;
            copy.next = (next == null) ? null : next.next;
            cur = next;
        }

        return res;
    }

    //方法2 使用哈希表
    public Node copyRandomList1(Node head) {
        if (head==null) return null;
        HashMap<Node, Node> map = new HashMap<>();
        Node cur = head;
        while (cur!=null) {
            map.put(cur, new Node(cur.val));
            cur = cur.next;
        }
        cur = head;
        while (cur!=null) {
            map.get(cur).next = map.get(cur.next);
            map.get(cur).random = map.get(cur.random);
            cur = cur.next;
        }
        return map.get(head);
    }
}
