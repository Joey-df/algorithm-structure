package linked_list;

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
public class Problem_143_Reorder_List {

    public static void reorderList(ListNode head) {
        process(head);
    }

    //O(N^2)的方法
    //递归含义：reorder以head为头的链表,返回新的头节点
    private static ListNode process(ListNode head) {
        if (head == null || head.next == null || head.next.next == null) return head;
        //至少有三个节点
        ListNode preTail = head;
        do {
            preTail = preTail.next;
        } while (preTail.next != null && (preTail.next.next != null));

        ListNode tail = preTail.next;
        preTail.next = null;
        ListNode newHead = head.next;
        head.next = tail;
        tail.next = process(newHead);
        return head;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);

        reorderList(head);
    }
}
