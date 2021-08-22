package linked_list;

/**
 * 61. 旋转链表
 * 给你一个链表的头节点 head ，旋转链表，将链表每个节点向右移动 k 个位置。
 * Example 1:
 * Input: head = [1,2,3,4,5], k = 2
 * Output: [4,5,1,2,3]
 *
 * Example 2:
 * Input: head = [0,1,2], k = 4
 * Output: [2,0,1]
 *
 * Constraints:
 * The number of nodes in the list is in the range [0, 500].
 * -100 <= Node.val <= 100
 * 0 <= k <= 2 * 10^9
 */
public class Problem_0061_RotateList {
    public ListNode rotateRight(ListNode head, int k) {
        if (head==null || head.next==null) {
            return head;
        }
        int len = length(head);
        k %= len;
        if (k == 0) {
            return head;
        }
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode cur = dummy;
        int n = len-k;
        while (n-- >0) {
            cur = cur.next;
        }
        ListNode res = cur.next;
        head = res;
        cur.next = null;
        while (head.next!=null) {
            head = head.next;
        }
        //head来到尾节点
        head.next = dummy.next;
        return res;
    }

    private int length(ListNode head) {
        int len = 0;
        while (head!=null) {
            len++;
            head = head.next;
        }
        return len;
    }
}
