package linked_list;

//单链表反转
public class Reverse_LinkedList {

    public static ListNode reverse(ListNode head) {
        if (head == null) return head;
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
}
