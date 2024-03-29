package linked_list;

/**
 * Given the head of a linked list, remove the nth node from the end of the list and return its head.
 *
 * Follow up: Could you do this in one pass?
 *
 * Example 1:
 *
 *
 * Input: head = [1,2,3,4,5], n = 2
 * Output: [1,2,3,5]
 * Example 2:
 *
 * Input: head = [1], n = 1
 * Output: []
 * Example 3:
 *
 * Input: head = [1,2], n = 1
 * Output: [1]
 *
 *
 * Constraints:
 *
 * The number of nodes in the list is sz.
 * 1 <= sz <= 30
 * 0 <= Node.val <= 100
 * 1 <= n <= sz
 */

//Constraints:
//The number of nodes in the list is sz.
//1 <= sz <= 30
//0 <= Node.val <= 100
//1 <= n <= sz
public class Problem_0019_RemoveNthNodeFromEndOfList {
    //方法1：
    //快慢指针都停在dummy，快指针先走n+1步，
    //然后快慢指针同时走，快指针走向null时，慢指针刚好来到要删除节点的前一个
    public ListNode removeNthFromEnd(ListNode head, int n) {
        if (head==null) return head;
        ListNode dummy=new ListNode(-1);
        dummy.next=head;
        ListNode slow=dummy;
        ListNode fast=dummy;
        for (int i = 0; i <= n; i++) {
           fast=fast.next;
        }
        while (fast!=null) {
            slow=slow.next;
            fast=fast.next;
        }
        slow.next = slow.next.next;
        return dummy.next;
    }

    // 方法2：慢指针指向head的前一个节点，快指针指向head，
    // 快指针走n-1步，然后快慢指针同时走，快指针走到空时，慢指针刚好来到待删节点的前一个节点
    public ListNode removeNthFromEnd2(ListNode head, int n) {
        if (head==null) return head;
        ListNode dummy=new ListNode(-1);
        dummy.next=head;
        ListNode slow=dummy;
        ListNode fast=head;
        for (int i = 0; i < n; i++) {
            fast=fast.next;
        }
        while (fast!=null) {
            slow=slow.next;
            fast=fast.next;
        }
        slow.next = slow.next.next;
        return dummy.next;
    }
}
