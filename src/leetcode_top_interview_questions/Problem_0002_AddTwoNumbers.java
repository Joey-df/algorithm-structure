package leetcode_top_interview_questions;

import linked_list.ListNode;

/**
 * 给你两个 非空 的链表，表示两个非负的整数。
 * 它们每位数字都是按照 逆序 的方式存储的，并且每个节点只能存储 一位 数字。
 * <p>
 * 请你将两个数相加，并以相同形式返回一个表示和的链表。
 * <p>
 * 你可以假设除了数字 0 之外，这两个数都不会以 0 开头。
 *  
 * 示例 1：
 * 输入：l1 = [2,4,3], l2 = [5,6,4]
 * 输出：[7,0,8]
 * 解释：342 + 465 = 807.
 */
public class Problem_0002_AddTwoNumbers {

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        assert (l1 != null && l2 != null);
        int jinwei = 0;
        ListNode head1 = l1;
        ListNode head2 = l2;
        int headVal = head1.val + head2.val;
        if (headVal >= 10) {
            jinwei = 1;
            headVal %= 10;
        }
        ListNode ans = new ListNode(headVal);
        ListNode cur = ans;
        head1 = head1.next;
        head2 = head2.next;
        while (head1 != null && head2 != null) {
            int curval = head1.val + head2.val + jinwei;
            if (curval >= 10) {
                curval %= 10;
                jinwei = 1;
            } else {
                jinwei = 0;
            }
            cur.next = new ListNode(curval);
            cur = cur.next;
            head1 = head1.next;
            head2 = head2.next;
        }

        ListNode curHead = head1 != null ? head1 : head2;
        while (curHead != null) {
            int curVal = curHead.val + jinwei;
            if (curVal >= 10) {
                curVal = curVal % 10;
                jinwei = 1;
            } else {
                jinwei = 0;
            }
            cur.next = new ListNode(curVal);
            cur = cur.next;
            curHead = curHead.next;
        }
        if (jinwei == 1) {
            cur.next = new ListNode(1);
        }
        return ans;
    }
}
