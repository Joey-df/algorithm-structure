package train_camp_03.class04;


import java.util.LinkedList;
import java.util.Queue;

/**
 * 双向链表节点结构和二叉树节点结构是一样的，如果你把last认为是left，next认为是next的话。
 * 给定一个搜索二叉树的头节点head，请转化成一条有序的双向链表，并返回链表的头节点。
 * <p>
 * //TODO convert1需要复习
 */
public class Code02_BSTtoDoubleLinkedList {

    private static class Node {
        int value;
        Node left;
        Node right;

        public Node(int val) {
            this.value = val;
        }
    }

    private static class Info {
        Node head;
        Node tail;

        public Info(Node head, Node tail) {
            this.head = head;
            this.tail = tail;
        }
    }

    //递归含义：
    //对每一个x都要求返回转化后的链表的head和tail
    public static Info process(Node x) {
        if (x == null) {
            return new Info(null, null);
        }
        Info leftInfo = process(x.left);
        Info rightInfo = process(x.right);
        if (leftInfo.tail != null) {
            leftInfo.tail.right = x;
        }
        x.left = leftInfo.tail;
        x.right = rightInfo.head;
        if (rightInfo.head != null) {
            rightInfo.head.left = x;
        }
        return new Info(leftInfo.head != null ? leftInfo.head : x,
                rightInfo.tail != null ? rightInfo.tail : x);
    }

    public static Node convert2(Node head) {
        if (head == null) {
            return null;
        }
        return process(head).head;
    }


    public static Node convert1(Node head) {
        Queue<Node> queue = new LinkedList<>();
        inOrderToQueue(head, queue);
        if (queue.isEmpty()) {
            return head;
        }
        //将队列中的元素串成一个双向链表
        head = queue.poll();
        Node pre = head;
        pre.left = null;
        Node cur = null;
        while (!queue.isEmpty()) {
            cur = queue.poll();
            pre.right = cur;
            cur.left = pre;
            pre = cur;
        }
        pre.right = null;
        return head;
    }

    public static void inOrderToQueue(Node head, Queue<Node> queue) {
        if (head == null) {
            return;
        }
        inOrderToQueue(head.left, queue);
        queue.offer(head);
        inOrderToQueue(head.right, queue);
    }

    public static void printBSTInOrder(Node head) {
        System.out.print("BST in-order: ");
        if (head != null) {
            inOrderPrint(head);
        }
        System.out.println();
    }

    public static void inOrderPrint(Node head) {
        if (head == null) {
            return;
        }
        inOrderPrint(head.left);
        System.out.print(head.value + " ");
        inOrderPrint(head.right);
    }

    public static void printDoubleLinkedList(Node head) {
        System.out.print("Double Linked List: ");
        Node end = null;
        while (head != null) {
            System.out.print(head.value + " ");
            end = head;
            head = head.right;
        }
        System.out.print("| ");
        while (end != null) {
            System.out.print(end.value + " ");
            end = end.left;
        }
        System.out.println();
    }


    //方法三
    private static Node pre = null;

    public static Node convert3(Node root) {
        pre = null;
        Node dummy = new Node(0);
        pre = dummy;
        helper(root);
        Node ans = dummy.right;
        ans.left=null;
        return ans;
    }

    private static void helper(Node x) {
        if (x == null) {
            return;
        }
        helper(x.left);
        pre.right = x;
        x.left = pre;
        pre = x;
        helper(x.right);
    }


    public static void main(String[] args) {
        Node head = new Node(5);
        head.left = new Node(2);
        head.right = new Node(9);
        head.left.left = new Node(1);
        head.left.right = new Node(3);
        head.left.right.right = new Node(4);
        head.right.left = new Node(7);
        head.right.right = new Node(10);
        head.left.left = new Node(1);
        head.right.left.left = new Node(6);
        head.right.left.right = new Node(8);

        printBSTInOrder(head);
        head = convert1(head);
        printDoubleLinkedList(head);

        head = new Node(5);
        head.left = new Node(2);
        head.right = new Node(9);
        head.left.left = new Node(1);
        head.left.right = new Node(3);
        head.left.right.right = new Node(4);
        head.right.left = new Node(7);
        head.right.right = new Node(10);
        head.left.left = new Node(1);
        head.right.left.left = new Node(6);
        head.right.left.right = new Node(8);

        printBSTInOrder(head);
        head = convert2(head);
        printDoubleLinkedList(head);

        head = new Node(5);
        head.left = new Node(2);
        head.right = new Node(9);
        head.left.left = new Node(1);
        head.left.right = new Node(3);
        head.left.right.right = new Node(4);
        head.right.left = new Node(7);
        head.right.right = new Node(10);
        head.left.left = new Node(1);
        head.right.left.left = new Node(6);
        head.right.left.right = new Node(8);

        printBSTInOrder(head);
        head = convert3(head);
        printDoubleLinkedList(head);
    }
}
