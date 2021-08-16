package linked_list;

/**
 * Given a linked list, return the node where the cycle begins. If there is no cycle, return null.
 */
public class Problem_0142_LinkedListCycleII {
    //返回链表第一个入环节点，如果没有环，返回null
    public ListNode detectCycle(ListNode head) {
        if (head == null || head.next == null || head.next.next == null) {
            return null;
        }
        //链表有三个点及以上
        ListNode slow = head.next;
        ListNode fast = head.next.next;

        while (slow != fast) {
            if (fast.next == null || fast.next.next == null) {
                return null; //链表无环
            }
            slow = slow.next;
            fast = fast.next.next;
        }

        //走到这里说明slow和fast相遇
        //slow和fast相遇时，让其中任何一个重新指向head，两个每次走一步，再次相遇的地方就是第一个入环节点
        fast = head;
        while (fast != slow) {
            fast = fast.next;
            slow = slow.next;
        }
        return slow;
    }

}
