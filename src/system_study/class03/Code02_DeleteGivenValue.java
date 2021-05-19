package system_study.class03;

import linked_list.ListNode;

//删除链表中等于给定值 val 的所有节点
//leetcode 203
//https://leetcode.com/problems/remove-linked-list-elements/
public class Code02_DeleteGivenValue {

    public static ListNode removeElements(ListNode head, int val) {
        if (head == null) {
            return head;
        }
        while (head != null && head.val == val) {
            head = head.next;
        }
        //head来到第一个不是val的节点
        ListNode cur = head;
        ListNode pre = head;
        while (cur != null) {
            if (cur.val != val) {
                pre = cur;
            } else { // cur.val==val
                pre.next = cur.next;
            }
            cur = cur.next;
        }
        return head;
    }
}
