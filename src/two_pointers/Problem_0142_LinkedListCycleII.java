package two_pointers;

import linked_list.ListNode;

/**
 * Given a linked list, return the node where the cycle begins. If there is no cycle, return null.
 */
public class Problem_0142_LinkedListCycleII {
    //返回链表第一个入环节点，如果没有环，返回null
    public ListNode detectCycle(ListNode head) {
        if (head==null || head.next==null) return null;
        ListNode s = head.next;
        ListNode f = head.next.next;
        while (s!=f) {
            if (f==null || f.next==null) {
                return null;
            }
            f = f.next.next;
            s = s.next;
        }
        //s、f相遇
        f = head;
        while (s!=f) {
            s = s.next;
            f = f.next;
        }
        return s;
    }

}
