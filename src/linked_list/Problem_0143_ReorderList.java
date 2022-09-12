package linked_list;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 143. 重排链表
 * 给定一个单链表 L 的头节点 head ，单链表 L 表示为：
 * L0→L1→…→Ln-1→Ln,
 * 请将其重新排列后变为:
 * L0→Ln→L1→Ln-1→L2→Ln-2→…
 * <p>
 * 不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
 * <p>
 * Example 1:
 * <p>
 * Given 1->2->3->4, reorder it to 1->4->2->3.
 * Example 2:
 * <p>
 * Given 1->2->3->4->5, reorder it to 1->5->2->4->3.
 */
public class Problem_0143_ReorderList {

    public static void reorderList(ListNode head) {
        List<ListNode> nodes = new ArrayList<>();
        for (ListNode cur = head; cur != null; cur = cur.next) {
            nodes.add(cur); // 把每个node的内存地址放到list中
        }
        // 下面使用双指针重连
        int l = 0;
        int r = nodes.size() - 1;
        ListNode dummy = new ListNode(-1);
        ListNode cur = dummy;
        while (l <= r) {
            cur.next = nodes.get(l++);
            cur = cur.next;
            cur.next = nodes.get(r--);
            cur = cur.next;
        }
        cur.next = null;  // eliminate the cycle
    }


    //最优解
    public static void reorderList2(ListNode head) {
        if (head == null || head.next == null || head.next.next == null) return;
        //至少三个点
        ListNode slow = head.next;
        ListNode fast = head.next.next;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        // slow来到中点位置（奇数时正好时中点，偶数长度时来到上中点）
        ListNode newHead = slow.next; // 第二条小链表
        slow.next = null;
        newHead = reverse(newHead);
        ListNode cur1 = head;
        ListNode cur2 = newHead;
        //合并两个链表
        ListNode next1 = null, next2 = null;
        while (cur1 != null && cur2 != null) {
            next1 = cur1.next;
            next2 = cur2.next;
            cur1.next = cur2;
            cur2.next = next1;
            cur1 = next1;
            cur2 = next2;
        }
    }

    public static ListNode reverse(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode pre = null;
        ListNode next = null;
        while (head != null) {
            next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }
        return pre;
    }


    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);

        reorderList2(head);
    }
}
