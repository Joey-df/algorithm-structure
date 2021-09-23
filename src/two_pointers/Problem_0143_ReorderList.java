package two_pointers;

import linked_list.ListNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Given a singly linked list L: L0→L1→…→Ln-1→Ln,
 * reorder it to: L0→Ln→L1→Ln-1→L2→Ln-2→…
 * <p>
 * You may not modify the values in the list's nodes, only nodes itself may be changed.
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
}
