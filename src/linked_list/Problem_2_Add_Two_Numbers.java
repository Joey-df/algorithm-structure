package linked_list;

/**
 * ou are given two non-empty linked lists representing two non-negative integers.
 * The digits are stored in reverse order, and each of their nodes contains a single digit.
 * Add the two numbers and return the sum as a linked list.
 * <p>
 * You may assume the two numbers do not contain any leading zero, except the number 0 itself.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: l1 = [2,4,3], l2 = [5,6,4]
 * Output: [7,0,8]
 * Explanation: 342 + 465 = 807.
 * Example 2:
 * <p>
 * Input: l1 = [0], l2 = [0]
 * Output: [0]
 * Example 3:
 * <p>
 * Input: l1 = [9,9,9,9,9,9,9], l2 = [9,9,9,9]
 * Output: [8,9,9,9,0,0,0,1]
 * <p>
 * <p>
 * Constraints:
 * <p>
 * The number of nodes in each linked list is in the range [1, 100].
 * 0 <= Node.val <= 9
 * It is guaranteed that the list represents a number that does not have leading zeros.
 */
public class Problem_2_Add_Two_Numbers {

    public ListNode addTwoNumbers(ListNode head1, ListNode head2) {
        ListNode dummy = new ListNode(-1);
        int newHeadVal = (head1.val + head2.val) % 10;
        int jw = (head1.val + head2.val) / 10;
        ListNode cur = new ListNode(newHeadVal);
        dummy.next = cur;
        head1 = head1.next;
        head2 = head2.next;
        while (head1 != null || head2 != null) {
            int v1 = head1 != null ? head1.val : 0;
            int v2 = head2 != null ? head2.val : 0;
            int newVal = (v1 + v2 + jw) % 10;
            jw = (v1 + v2 + jw) / 10;
            cur.next = new ListNode(newVal);
            cur = cur.next;
            if (head1 != null) {
                head1 = head1.next;
            }
            if (head2 != null) {
                head2 = head2.next;
            }
        }
        if (jw == 1) {
            cur.next = new ListNode(1);
            cur = cur.next;
            cur.next = null;
        }
        return dummy.next;
    }
}
