package tree;

import linked_list.ListNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 109. 有序链表转换二叉搜索树
 * 给定一个单链表，其中的元素按升序排序，将其转换为高度平衡的二叉搜索树。
 *
 * 本题中，一个高度平衡二叉树是指一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过 1。
 *
 * 示例:
 *
 * 给定的有序链表： [-10, -3, 0, 5, 9],
 *
 * 一个可能的答案是：[0, -3, 9, -10, null, 5], 它可以表示下面这个高度平衡二叉搜索树：
 *
 *       0
 *      / \
 *    -3   9
 *    /   /
 *  -10  5
 */
public class Problem_0109_ConvertSortedListToBinarySearchTree {

    //方法1
    public TreeNode sortedListToBST(ListNode head) {
        int[] arr = toArray(head);
        if (arr==null || arr.length==0) return null;
        return build(arr,0,arr.length-1);
    }

    public int[] toArray(ListNode head) {
        if (head==null) return new int[0];
        List<Integer> list = new ArrayList<>();
        while (head!=null) {
            list.add(head.val);
            head = head.next;
        }
        return list.stream().mapToInt(a->a).toArray();
    }

    public TreeNode build(int[] arr, int l, int r) {
        if (l > r) return null;
        int m = l + ((r-l) >> 1);
        TreeNode root = new TreeNode(arr[m]);
        root.left = build(arr, l, m-1);
        root.right = build(arr, m+1, r);
        return root;
    }

    //方法2 (最优解)
    private TreeNode build(ListNode head) {
        if (head==null) return null;
        //先找中点 二分 确定root节点，然后分割出左右小链表 分别构造左右子树
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode slow = dummy;
        ListNode fast = head;
        while (fast!=null && fast.next!=null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode mid = slow.next;
        TreeNode root = new TreeNode(mid.val);
        ListNode r = mid.next;
        slow.next = null; //断开
        ListNode l = dummy.next;
        root.left = build(l);
        root.right = build(r);
        return root;
    }

}
