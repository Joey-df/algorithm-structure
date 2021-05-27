package coding_for_great_offer.class10;


import tree.TreeNode;

/**
 * Leetcode原题：
 * <p>
 * 给定一棵搜索二叉树头节点，转化成首尾相接的有序双向链表
 * <p>
 * https://leetcode.com/problems/convert-binary-search-tree-to-sorted-doubly-linked-list/
 */
public class Code04_BSTtoDoubleLinkedList {

    //思路：二叉树递归套路
    public TreeNode convert(TreeNode root) {
        if (root == null) return null;
        Info all = process(root);
        all.head.left = all.tail;
        all.tail.right = all.head;
        return all.head;
    }

    //表示二叉树转成的双向链表的头节点和尾节点信息
    private static class Info {
        TreeNode head;
        TreeNode tail;

        public Info(TreeNode h, TreeNode t) {
            head = h;
            tail = t;
        }
    }

    //递归含义：
    //给定二叉树头节点x，返回转化后的有序双向链表信息
    public Info process(TreeNode x) {
        if (x == null) {
            return new Info(null, null);
        }
        Info l = process(x.left);
        Info r = process(x.right);
        if (l.tail != null) {
            l.tail.right = x;
        }
        x.left = l.tail;
        x.right = r.head;
        if (r.head != null) {
            r.head.left = x;
        }
        TreeNode head = l.head != null ? l.head : x;
        TreeNode tail = r.tail != null ? r.tail : x;
        return new Info(head, tail);
    }

}
