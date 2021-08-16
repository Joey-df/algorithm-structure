package linked_list;

/**
 * Reverse a singly linked list.
 * <p>
 * Example:
 * <p>
 * Input: 1->2->3->4->5->NULL
 * Output: 5->4->3->2->1->NULL
 * Follow up:
 * <p>
 * A linked list can be reversed either iteratively or recursively. Could you implement both?
 */
//leetcode206
//反转单链表
public class Problem_206_Reverse_LinkedList {

    public static ListNode reverse(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        //两个以上节点
        ListNode pre = null, next = null;
        while (head != null) {
            next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }
        return pre;
    }
}
