package two_pointers;

import linked_list.ListNode;

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

    public void reorderList(ListNode head) {
        HashMap<Integer, ListNode> map = new HashMap<>();
        for (int i = 1; head != null; head = head.next, i++) {
            map.put(i, head);
        }
        ListNode dummy = new ListNode(0);
        ListNode curr = dummy;
        for (int i = 1, j = map.size(); i <= j; i++, j--) {   //1,2,3,4
            curr.next = map.get(i);                     //curr->1
            if (i != j) {
                map.get(i).next = map.get(j);           //1->4
            }
            map.get(j).next = null;                     //4->null
            curr = map.get(j);                          ///curr = 4,then 1->4
        }
    }


    public void reorderList2(ListNode head) {
        List<ListNode> nodes = new ArrayList<>();
        for (ListNode cur = head; cur != null; cur = cur.next) {
            nodes.add(cur);
        }
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

    //O(N^2)的方法
    //递归含义：reorder以head为头的链表,返回新的头节点
    private static ListNode reorderList3(ListNode head) {
        if (head == null || head.next == null || head.next.next == null) return head;
        //至少有三个节点
        ListNode cur = head;
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode preTail = dummy;
        while (cur.next!=null) {
            preTail = preTail.next;
            cur = cur.next;
        }
        //preTail: tail前一个节点
        ListNode tail = preTail.next;
        preTail.next = null;
        ListNode newHead = head.next;
        head.next = tail;
        tail.next = reorderList3(newHead);
        return head;
    }
}
