package system_study.class03;

import linked_list.ListNode;

//反转单链表
//leetcode 206
// https://leetcode.com/problems/reverse-linked-list/
public class Code01_ReverseList {

    public static ListNode reverse(ListNode head) {
        if (head == null) {
            return head;
        }
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
