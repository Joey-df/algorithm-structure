package routine;

import linked_list.ListNode;

public class DeleteGivenDode {
    //删除单链表中指定val的节点
    public static ListNode deleteNode(ListNode head, int val) {
        if (head == null) return head;
        while (head.val == val) {
            head = head.next;
        }
        //head来到第一个不是val的位置
        ListNode pre = head;
        ListNode cur = head;
        while (cur != null) {
            if (cur.val != val) {
                pre = cur;
            } else {
                pre.next = cur.next;
            }
            cur = cur.next;
        }
        return head;
    }
}
